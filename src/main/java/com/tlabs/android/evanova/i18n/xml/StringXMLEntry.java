package com.tlabs.android.evanova.i18n.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class StringXMLEntry {

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlText
    private String value;

    public String getName() {
        return name;
    }

    public StringXMLEntry setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public StringXMLEntry setValue(String value) {
        this.value = value;
        return this;
    }
}
