package edu.school21.utils;

import java.util.Locale;

public class PreProcessorToUpperImpl implements PreProcessor{
    @Override
    public String process(String text) {
        return text.toUpperCase(Locale.ROOT);
    }
}
