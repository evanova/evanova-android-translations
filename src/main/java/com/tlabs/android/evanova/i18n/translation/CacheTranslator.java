package com.tlabs.android.evanova.i18n.translation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public final class CacheTranslator implements Translator {
    private static final Logger LOG = LoggerFactory.getLogger(CacheTranslator.class);

    private final Map<String, String> cache = new HashMap<>();
    private final Translator delegate;

    public CacheTranslator(final Translator delegate) {
        this.delegate = delegate;
    }

    @Override
    public String translate(final String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }

        String cached = cache.get(text);
        if (null == cached) {
            cached = delegate.translate(text);
            cache.put(text, cached);
            LOG.info("'{}' -> '{}' [new]", text, cached);
        }
        else {
            LOG.info("'{}' -> '{}' [cached]", text, cached);
        }

        return cached;
    }

}
