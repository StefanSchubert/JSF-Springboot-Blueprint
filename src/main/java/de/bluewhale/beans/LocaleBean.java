package de.bluewhale.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Locale;


@SessionScope
@Slf4j
@Component
public class LocaleBean implements Serializable {

    private Locale locale;

    public Locale getLocale() {
        Locale browsersLocale = LocaleContextHolder.getLocale();

        if (locale == null) {
            Locale supportedLocale = getEnsuredSupportedLocale(browsersLocale.getLanguage());
            locale = supportedLocale;
        }
        LocaleContextHolder.setLocale(locale);
        return locale;
    }

    public void setLocale(final Locale locale) {
        Locale newLocale = getEnsuredSupportedLocale(locale.getLanguage());
        LocaleContextHolder.setLocale(locale);
        this.locale = newLocale;
    }

    private Locale getEnsuredSupportedLocale(String language) {
        Locale requestedLocale = new Locale(language);
        Locale fallBackLocale = Locale.ENGLISH;
        boolean fallBack = true;
        for (SupportedLocales validLocale : SupportedLocales.values()) {
            if (validLocale.getLocale().getLanguage().equals(requestedLocale.getLanguage())) {
                fallBack = false;
                break;
            }
        }
        return (fallBack ? fallBackLocale : requestedLocale);
    }

    public enum SupportedLocales {
        German(Locale.GERMANY),
        English(Locale.ENGLISH);

        private Locale locale;

        SupportedLocales(Locale locale) {
            this.locale = locale;
        }

        public Locale getLocale() {
            return this.locale;
        }

        @Override
        public String toString() {
            return locale.getISO3Language();
        }
    }

}
