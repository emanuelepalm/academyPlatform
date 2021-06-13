package com.palmieri.toolbox;

public class StringStylist {
    public static String polishMethodName(String before) {
        String after = "";
        for (int i = 0; i < before.length(); i++) {
            if (i == 0) after += Character.toUpperCase(before.charAt(0));
            else {
                if (Character.isUpperCase(before.charAt(i))) after += " ";
                after += before.charAt(i);
            }
        }
        return after;
    }
}
