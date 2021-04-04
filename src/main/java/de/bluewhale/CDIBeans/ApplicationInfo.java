package de.bluewhale.CDIBeans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Container for application specific static information.
 *
 * @author Stefan Schubert
 */
@Named
@ApplicationScoped
@Slf4j
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
