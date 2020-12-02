package de.bluewhale.controller;

import de.bluewhale.CDIBeans.LocaleBean;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;

import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScope
@Slf4j
@Getter
public class SampleView implements Serializable {

    @Autowired
    LocaleBean localeBean;

    public String businessCallViaCommandWithOutcomeNavigation() {
        log.info("Bussiness Logic of B called.");
        return "pageB";
    }

    public void businessCallViaViewAction() {
        log.info("Bussiness Logic of A called.");
    }

}
