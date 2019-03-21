package com.tlabs.android.evanova.i18n.xml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@JacksonXmlRootElement(localName = "resources")
public class StringXML {

    @JacksonXmlProperty(localName = "string")
    private List<StringXMLEntry> strings = new ArrayList<>();

    @JacksonXmlProperty(localName = "string-array")
    private List<StringXMLArray> stringArray = new ArrayList<>();

    @JacksonXmlProperty(localName = "plurals")
    private List<StringXMLPlural> plurals = new ArrayList<>();

    public List<StringXMLEntry> getStrings() {
        return strings;
    }

    public StringXML setStrings(List<StringXMLEntry> strings) {
        this.strings = strings;
        return this;
    }

    public List<StringXMLArray> getStringArray() {
        return stringArray;
    }

    public StringXML setStringArray(List<StringXMLArray> stringArray) {
        this.stringArray = stringArray;
        return this;
    }

    public List<StringXMLPlural> getPlurals() {
        return plurals;
    }

    public StringXML setPlurals(List<StringXMLPlural> plurals) {
        this.plurals = plurals;
        return this;
    }

    public StringXMLEntry findEntry(final String byName) {
        return strings.stream()
                .filter(s -> s.getName().equals(byName))
                .findFirst()
                .orElse(null);
    }

    public StringXMLArray findArray(final String byName) {
        return stringArray.stream()
                .filter(s -> s.getName().equals(byName))
                .findFirst()
                .orElse(null);
    }

    public StringXMLPlural findPlural(final String byName) {
        return plurals.stream()
                .filter(s -> s.getName().equals(byName))
                .findFirst()
                .orElse(null);
    }

    @XmlTransient
    @JsonIgnore
    public boolean isEmpty() {
        return this.plurals.isEmpty() && this.stringArray.isEmpty() && this.strings.isEmpty();
    }
}
