package com.tlabs.android.evanova.i18n.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.List;

public class StringXMLPlural {

    public static class Item {

        @JacksonXmlProperty(isAttribute = true)
        private String quantity;

        @JacksonXmlText
        private String value;

        public String getQuantity() {
            return quantity;
        }

        public Item setQuantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Item setValue(String value) {
            this.value = value;
            return this;
        }
    }

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "item")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public StringXMLPlural setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public String getName() {
        return name;
    }

    public StringXMLPlural setName(String name) {
        this.name = name;
        return this;
    }
}
