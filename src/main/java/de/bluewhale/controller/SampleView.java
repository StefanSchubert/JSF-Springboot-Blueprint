package de.bluewhale.controller;

import de.bluewhale.beans.LocaleBean;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;

@RequestScope
@Component
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
