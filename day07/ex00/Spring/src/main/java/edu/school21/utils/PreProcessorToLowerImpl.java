package edu.school21.utils;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor{
    @Override
    public String process(String text) {
        return text.toLowerCase(Locale.ROOT);
    }
}
