package com.tlabs.android.evanova.i18n.translation;

public interface Translator {

    String translate(String text);

    default Translator andThen(final Translator other) {
        return text -> other.translate(this.translate(text));
    }
}
