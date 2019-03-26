package com.tlabs.android.evanova.i18n;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tlabs.android.evanova.i18n.translation.Translator;
import com.tlabs.android.evanova.i18n.xml.StringXML;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {

    private static final ObjectMapper mapper;

    static {
        final JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);

        mapper = new XmlMapper(xmlModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void main(String... args) throws IOException {
        final File input = new File("D:\\dev\\eve\\evanova2\\i18n\\src\\main\\res\\values\\");
        if (!input.exists()) {
            throw new FileNotFoundException(input.getAbsolutePath());
        }

        final File output = new File("D:\\dev\\eve\\evanova2\\i18n\\build\\i18n\\");
        Stream.of("fr", "ru", "zh", "de")
            .parallel()
            .forEach(l -> {
                try {
                    translate(input, output, l);
                }
                catch (IOException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            });
    }

    private static void translate(final File input, final File output, final String language) throws IOException {
        final XMLTranslator translator = new XMLTranslator(language);

        final File lInput = new File(input.getAbsolutePath() + "-" + language);
        final File lOutput = new File(output, "values-" + language);
        lOutput.delete();
        lOutput.mkdirs();

        if (lInput.exists()) {
            FileUtils.copyDirectory(lInput, lOutput);
        }

        for (File f: input.listFiles((dir, name) -> name.endsWith(".xml"))) {
            try {
                translate(f, new File(lOutput, f.getName()), translator);
            }
            catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }

    private static void translate(final File from, final File to, final XMLTranslator translator) throws IOException {
        final StringXML original = mapper.readValue(from, StringXML.class);
        StringXML translated;

        if (to.exists()) {
            final StringXML existing = mapper.readValue(to, StringXML.class);
            translated = translator.update(original, existing);
        }
        else {
            translated = translator.translate(original);
        }

        to.delete();
        if (!translated.isEmpty()) {
            to.createNewFile();
            mapper.writeValue(to, translated);
        }
    }

}
