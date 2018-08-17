package com.base.utils;

public class TagUtils {
    public static String makeLogTag(Class<?> cls) {
        String tag = cls.getSimpleName();
        return (tag.length() > 50) ? tag.substring(0, 50) : tag;
    }
}
