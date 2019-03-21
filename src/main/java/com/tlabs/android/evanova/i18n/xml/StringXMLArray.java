package com.tlabs.android.evanova.i18n.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

public class StringXMLArray {
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private boolean translatable;

    @JacksonXmlProperty(localName = "item")
    private List<String> items = new ArrayList<>();

    public boolean getTranslatable() {
        return translatable;
    }

    public StringXMLArray setTranslatable(boolean translatable) {
        this.translatable = translatable;
        return this;
    }

    public List<String> getItems() {
        return items;
    }

    public StringXMLArray setItems(List<String> items) {
        this.items = items;
        return this;
    }

    public String getName() {
        return name;
    }

    public StringXMLArray setName(String name) {
        this.name = name;
        return this;
    }
}
