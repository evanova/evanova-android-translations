package com.tlabs.android.evanova.i18n.translation;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class GoogleTranslator implements Translator {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleTranslator.class);

    private static final Map<String, String> languages;

    static {
        languages = new HashMap<>();
        languages.put("zh", "zh-CN");
    }

    private final String languageFrom;
    private final String languageTo;
    private final Translate translator;

    public GoogleTranslator(final String languageFrom, final String languageTo) {
        this(defaultGoogleTranslate(), languageFrom, languageTo);
    }

    public GoogleTranslator(final File json, final String languageFrom, final String languageTo) {
        this(googeTranslate(json), languageFrom, languageTo);
    }

    private GoogleTranslator(final Translate translator, final String languageFrom, final String languageTo) {
        this.translator = translator;
        this.languageFrom = languages.getOrDefault(languageFrom, languageFrom);;
        this.languageTo = languages.getOrDefault(languageTo, languageTo);
    }

    @Override
    public String translate(final String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        try {
            Translation translation = this.translator.translate(
                    text,
                    Translate.TranslateOption.model("nmt"),
                    Translate.TranslateOption.sourceLanguage(this.languageFrom),
                    Translate.TranslateOption.targetLanguage(this.languageTo));
            return translation.getTranslatedText();
        } catch (Exception e) {
            LOG.error("Error translating '{}'%n{}", text, e.getLocalizedMessage());
            return text;
        }
    }

    private static Translate defaultGoogleTranslate() {
        return TranslateOptions.getDefaultInstance().getService();
    }

    private static Translate googeTranslate(final File googleServiceJSON) {
        try {
            return TranslateOptions
                    .newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(googleServiceJSON)))
                    .build()
                    .getService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
