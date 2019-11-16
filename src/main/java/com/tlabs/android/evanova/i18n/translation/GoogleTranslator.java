package com.tlabs.android.evanova.i18n.translation;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public final class GoogleTranslator implements Translator {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleTranslator.class);
    private static final Translate google;

    private static final Map<String, String> languages;

    static {
        languages = new HashMap<>();
        languages.put("zh", "zh-CN");
        google = TranslateOptions.getDefaultInstance().getService();
    }

    private final String languageFrom;
    private final String languageTo;

    public GoogleTranslator(final String languageTo) {
        this("en", languageTo);
    }

    public GoogleTranslator(final String languageFrom, final String languageTo) {
        this.languageFrom = languageFrom;
        this.languageTo = languages.getOrDefault(languageTo, languageTo);
    }

    @Override
    public String translate(final String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        try {
            Translation translation = google.translate(
                    text,
                    Translate.TranslateOption.model("nmt"),
                    Translate.TranslateOption.sourceLanguage(this.languageFrom),
                    Translate.TranslateOption.targetLanguage(this.languageTo));
            return translation.getTranslatedText();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            LOG.error("Error translating '{}'\n{}", text, e.getLocalizedMessage());
            return text;
        }
    }

}
