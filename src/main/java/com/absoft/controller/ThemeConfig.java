package com.absoft.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Diego Arantes
 */
@Named
@SessionScoped
public class ThemeConfig implements Serializable {

    private Map<String, String> themeColors;

    private String theme = "indigo";

    private String menuClass = null;

    private String profileMode = "inline";

    private String menuLayout = "horizontal";

    private boolean compact = true;

    @PostConstruct
    public void init() {
        themeColors = new HashMap<>();
        themeColors.put("indigo", "#3F51B5");
        themeColors.put("blue", "#03A9F4");
        themeColors.put("blue-grey", "#607D8B");
        themeColors.put("brown", "#795548");
        themeColors.put("cyan", "#00bcd4");
        themeColors.put("green", "#4CAF50");
        themeColors.put("purple-amber", "#673AB7");
        themeColors.put("purple-cyan", "#673AB7");
        themeColors.put("teal", "#009688");
    }

    public String getMenuClass() {
        return this.menuClass;
    }

    public String getProfileMode() {
        return this.profileMode;
    }

    public String getTheme() {
        return theme;
    }

    public String getMenuLayout() {
        switch (this.menuLayout) {
            case "static":
                return "menu-layout-static";
            case "overlay":
                return "menu-layout-overlay";
            case "horizontal":
                return "menu-layout-static menu-layout-horizontal";
            default:
                return "menu-layout-static";
        }
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setLightMenu() {
        this.menuClass = null;
    }

    public void setDarkMenu() {
        this.menuClass = "layout-menu-dark";
    }

    public void setProfileMode(String profileMode) {
        this.profileMode = profileMode;
    }

    public void setMenuLayout(String menuLayout) {
        this.menuLayout = menuLayout;
    }

    public Map getThemeColors() {
        return this.themeColors;
    }

    public void setCompact(boolean value) {
        this.compact = value;
    }

    public boolean isCompact() {
        return this.compact;
    }
}
