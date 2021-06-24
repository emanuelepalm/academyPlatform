package com.palmieri.toolbox;

import com.palmieri.interfaces.ISteps;

public class StringStylist {

    private static char by;

    public static char getBy(){
        return by;
    }

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
    public static String ByAndStep(String key) {
        String[] selectorSp = key.split("\\.");
        if(selectorSp[0].charAt(0) != 'c') by = selectorSp[0].charAt(0);
        else if(selectorSp[0].charAt(1) == 'l') by = selectorSp[0].charAt(0);
        else by = 's';
        return "Click on " + selectorSp[1] + " " + selectorSp[2];

    }
}
