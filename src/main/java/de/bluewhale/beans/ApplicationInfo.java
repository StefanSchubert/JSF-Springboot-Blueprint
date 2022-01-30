package de.bluewhale.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;

/**
 * Container for application specific static information.
 *
 * @author Stefan Schubert
 */
@ApplicationScope
@Slf4j
@Component
public class ApplicationInfo {

    public static String buildVersion = "N/A";

    @Autowired
    BuildProperties buildProperties;

    @PostConstruct
    public void lazyInit() {
        // Lazy Initialiser
        buildVersion = buildProperties.getVersion();
    }

    public String getVersion() {
        return buildVersion;
    }

}
