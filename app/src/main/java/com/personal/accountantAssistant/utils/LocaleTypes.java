package com.personal.accountantAssistant.utils;

public enum LocaleTypes {
    EN("en");

    String language;

    LocaleTypes(final String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
