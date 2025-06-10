package br.com.lukas.screenmatch.utils;

public class FormatUtils {

    public static String toParamURL(String text) {
        return text.toLowerCase().trim().replaceAll(" ", "+");
    }
}
