package de.bluewhale.beans;

import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.List;

/**
 * Session-scoped bean that manages the PrimeFaces UI theme for a user session.
 * The theme is loaded dynamically in masterLayout.xhtml via a plain HTML {@code <link>} tag,
 * so switching requires a full page reload.
 *
 * <p>Switch mechanism in header.xhtml:
 * p:selectOneMenu.onchange → clicks hidden non-AJAX p:commandButton →
 * full HTTP form-submit → switchThemeWithRedirect() → faces-redirect →
 * browser loads the page fresh with the new theme CSS in {@code <head>}.
 *
 * <p>To add more themes: extend AVAILABLE_THEMES. Theme names map to PrimeFaces
 * resource-library suffixes, e.g. "arya-blue" → JAR library "primefaces-arya-blue".
 */
@SessionScope
@Component
@Slf4j
public class ThemeBean implements Serializable {

    /** All themes offered in the selector. Extend freely.
     *
     * Theme availability in PrimeFaces 15:
     *  - The main primefaces JAR contains ONLY "saga-blue".
     *  - All other themes come from the "primefaces-themes" JAR, which is already
     *    pulled in transitively via primefaces-spring-boot-starter – no extra
     *    dependency required.
     *  - Theme names must match the resource-library suffix exactly, e.g.
     *    "arya-blue" → JAR entry META-INF/resources/primefaces-arya-blue/theme.css
     */
    private static final List<SelectItem> AVAILABLE_THEMES = List.of(
            // ── Light ───────────────────────────────────────────────────────────
            new SelectItem("saga-blue",                   "☀ Saga Blue (default)"),
            new SelectItem("nova-light",                  "☀ Nova Light"),
            new SelectItem("nova-colored",                "☀ Nova Colored"),
            new SelectItem("bootstrap4-blue-light",       "☀ Bootstrap4 Blue"),
            new SelectItem("bootstrap4-purple-light",     "☀ Bootstrap4 Purple"),
            new SelectItem("material-indigo-light",       "☀ Material Indigo"),
            new SelectItem("material-deeppurple-light",   "☀ Material DeepPurple"),
            // ── Semi-Dark ────────────────────────────────────────────────────────
            new SelectItem("vela-blue",                   "🌆 Vela Blue"),
            // ── Dark ─────────────────────────────────────────────────────────────
            new SelectItem("arya-blue",                   "🌙 Arya Blue"),
            new SelectItem("nova-dark",                   "🌙 Nova Dark"),
            new SelectItem("luna-blue",                   "🌙 Luna Blue"),
            new SelectItem("luna-amber",                  "🌙 Luna Amber"),
            new SelectItem("luna-green",                  "🌙 Luna Green"),
            new SelectItem("luna-pink",                   "🌙 Luna Pink"),
            new SelectItem("bootstrap4-blue-dark",        "🌙 Bootstrap4 Blue Dark"),
            new SelectItem("bootstrap4-purple-dark",      "🌙 Bootstrap4 Purple Dark"),
            new SelectItem("material-indigo-dark",        "🌙 Material Indigo Dark"),
            new SelectItem("material-deeppurple-dark",    "🌙 Material DeepPurple Dark")
    );

    @Getter @Setter
    private String theme = "saga-blue";

    /**
     * The list of selectable PrimeFaces themes shown in the dropdown.
     */
    public List<SelectItem> getAvailableThemes() {
        return AVAILABLE_THEMES;
    }

    /**
     * No longer used directly – kept for potential future use.
     * The actual theme switch is driven by {@link #switchThemeWithRedirect()}.
     */
    public void applyTheme() {
        log.info("Theme switched to '{}'", theme);
    }

    /**
     * Convenience method for cases where a full faces-redirect is preferred over JS reload.
     * Returns navigation to the current view with redirect=true.
     */
    public String switchThemeWithRedirect() {
        log.info("Theme switched to '{}' (redirect)", theme);
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }
}

