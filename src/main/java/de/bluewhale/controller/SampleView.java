package de.bluewhale.controller;

import de.bluewhale.CDIBeans.LocaleBean;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
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

    public void businessCallViaViewAction() {
        log.info("Bussiness Logic of A called.");
    }

    /**
     * The purpose of this example is to show where to locate the static error page, so that the app can redirect to it
     * if something like this happen
     */
    public void simulateBackendError()  {
        log.error("Outch - backend got a severe problem.");
        // throw new HttpTimeoutException("Simulate Timeout");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"Backend problem");
    }

    public String simulate404Error()  {
        return "ThisOutComeDoesNotExists";
    }


}
