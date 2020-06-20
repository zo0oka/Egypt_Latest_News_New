package com.zookanews.egyptlatestnews.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 17 Jun, 2020.
 * Have a nice day!
 */
public class StringFormatter {

    public static String decode(String value) {
        String unEncoded = null;
        try {
            unEncoded = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return unEncoded;
    }
}
