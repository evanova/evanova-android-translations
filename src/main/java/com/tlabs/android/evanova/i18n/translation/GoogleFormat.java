package com.tlabs.android.evanova.i18n.translation;

import org.apache.commons.lang3.StringUtils;

public final class GoogleFormat implements Translator {

    @Override
    public String translate(final String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }

        String f = StringUtils.trim(text);
        f = StringUtils.replace(f, "&amp;#39;", "\\'");
        f = StringUtils.replace(f, "% d", "%d");
        f = StringUtils.replace(f, "% s", "%s");
        f = StringUtils.replace(f, "% f", "%f");

        f = StringUtils.replace(f, " \\ N ", "\\n");

        f = percentN(f);
        f = StringUtils.replace(f, "％1 $ .0f％2$s％3 $ s", "％1$.0f ％2$s ％3$s");
        f = StringUtils.replace(f, "-%", "- %");


        return f;
    }

    private static String percentN(final String s) {
        String f = s;

        for (int i = 0; i < 10; i++) {
            f = StringUtils.replace(f, "% " + i, "%" + i);
            f = StringUtils.replace(f, "$ " + i, "$" + i);
            f = StringUtils.replace(f, ":$ " + i, ": $" + i);
            f = StringUtils.replace(f, ":% " + i, ": %" + i);
            f = StringUtils.replace(f, "%" + i + " $ s", "%" + i + "$s");
            f = StringUtils.replace(f, "%" + i + " $ d", "%" + i + "$d");
            f = StringUtils.replace(f, "%" + i + " $ s", "%" + i + "$s");
            f = StringUtils.replace(f, "%" + i + " $ f", "%" + i + "$f");
            f = StringUtils.replace(f, "%" + i + " $ .0f", "%" + i + "$.0f");

            f = StringUtils.replace(f, "% " + i + " $ s", "%" + i + "$s");
            f = StringUtils.replace(f, "% " + i + " $ d", "%" + i + "$d");
            f = StringUtils.replace(f, "% " + i + " $ s", "%" + i + "$s");
            f = StringUtils.replace(f, "% " + i + " $ f", "%" + i + "$f");
            f = StringUtils.replace(f, "% " + i + " $ .0f", "%" + i + "$.0f");
        }
        return f;
    }
}
