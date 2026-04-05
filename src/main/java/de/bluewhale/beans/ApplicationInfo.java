package de.bluewhale.beans;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Optional;

/**
 * Container for application specific static information.
 *
 * @author Stefan Schubert
 */
@ApplicationScope
@Slf4j
@Component
public class ApplicationInfo {

    public static String buildVersion = "N/A (dev-run – do 'mvn package' for version info)";

    // Optional: BuildProperties sind nur verfügbar, wenn zuvor 'mvn package' ausgeführt wurde.
    // Beim direkten Start über die IDE (ohne Maven-Build) fehlt die build-info.properties,
    // weshalb kein BuildProperties-Bean erzeugt wird. Required=false verhindert den Startup-Fehler.
    @Autowired(required = false)
    Optional<BuildProperties> buildProperties;

    @PostConstruct
    public void lazyInit() {
        buildProperties.ifPresentOrElse(
                props -> {
                    buildVersion = props.getVersion();
                    log.info("Application version: {}", buildVersion);
                },
                () -> log.warn("BuildProperties not available – application was not packaged via 'mvn package'. " +
                               "Version info will show placeholder value.")
        );
    }

    public String getVersion() {
        return buildVersion;
    }

}
