package com.tlabs.android.evanova.i18n;

import com.tlabs.android.evanova.i18n.translation.Translator;
import com.tlabs.android.evanova.i18n.xml.StringXML;
import com.tlabs.android.evanova.i18n.xml.StringXMLArray;
import com.tlabs.android.evanova.i18n.xml.StringXMLEntry;
import com.tlabs.android.evanova.i18n.xml.StringXMLPlural;

import java.util.List;
import java.util.stream.Collectors;

final class XMLTranslator {

    private final Translator translator;

    public XMLTranslator(final Translator translator) {
        this.translator = translator;
    }

    public StringXML translate(final StringXML xml) {
        final StringXML out = new StringXML();
        out.setStrings(xml.getStrings()
                .stream()
                .map(x -> translate(x, translator))
                .collect(Collectors.toList()));

        out.setPlurals(xml.getPlurals()
                .stream()
                .map(x -> translate(x, translator))
                .collect(Collectors.toList()));

        out.setStringArray(xml.getStringArray()
                .stream()
                .filter(StringXMLArray::getTranslatable)
                .map(x -> translate(x, translator))
                .collect(Collectors.toList()));

        return out;
    }

    //translate the missing ones and remove obsoletes
    public StringXML update(final StringXML xml, final StringXML existing) {
        final StringXML out = new StringXML();

        out.setStrings(xml.getStrings().stream()
                .map(s -> {
                    final StringXMLEntry x = existing.findEntry(s.getName());
                    return (null == x) ? translate(s, translator) : x;
                })
                .collect(Collectors.toList()));

        out.setPlurals(xml.getPlurals().stream()
            .map(p -> {
                final StringXMLPlural x = existing.findPlural(p.getName());
                return (null == x) ? translate(p, translator) : x;
            })
            .collect(Collectors.toList()));

        out.setStringArray(xml.getStringArray().stream()
                .filter(StringXMLArray::getTranslatable)
                .map(a -> {
                    final StringXMLArray x = existing.findArray(a.getName());
                    return (null == x) ? translate(a, translator) : x;
                })
                .collect(Collectors.toList()));

        return out;
    }

    private static StringXMLArray translate(final StringXMLArray x, final Translator translator) {
        final List<String> items =
                x.getItems()
                .stream()
                .map(translator::translate)
                .collect(Collectors.toList());

        return new StringXMLArray()
                .setName(x.getName())
                .setTranslatable(x.getTranslatable())
                .setItems(items);
    }

    private static StringXMLEntry translate(final StringXMLEntry x, final Translator translator) {
        return new StringXMLEntry()
                .setName(x.getName())
                .setValue(translator.translate(x.getValue()));
    }

    private static StringXMLPlural translate(final StringXMLPlural x, final Translator translator) {
        final List<StringXMLPlural.Item> items =
                x.getItems().stream()
                        .map(i -> new StringXMLPlural.Item()
                                .setQuantity(i.getQuantity())
                                .setValue(translator.translate(i.getValue())))
                        .collect(Collectors.toList());

        return new StringXMLPlural()
                .setName(x.getName())
                .setItems(items);
    }
}
