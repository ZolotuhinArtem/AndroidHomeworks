package com.zlthnrtm.hw3_weathercontentprovider.utils;

/**
 * Created by arch on 4/12/17.
 */

public class StringUtils {

    public static boolean isEmptyOrSpace(String string) {
        if (string == null) {
            return true;
        }

        if (string.equals("")) {
            return true;
        }

        if (string.replace("\t", "").replace("\n", "").replace(" ", "").equals("")) {
            return true;
        }

        return false;
    }

    public static String deleteSpacesLeftAndRight(String string) {
        int start, end;
        start = 0;
        end = 0;

        for (int i = 0; i < string.length(); i++) {
            if ((string.charAt(i) == ' ') || (string.charAt(i) == '\t') || (string.charAt(i) == '\n')) {
                continue;
            }
            start = i;
            break;
        }

        for (int i = string.length() - 1; i >= 0; i--) {
            if ((string.charAt(i) == ' ') || (string.charAt(i) == '\t') || (string.charAt(i) == '\n')) {
                continue;
            }
            end = i;
            break;
        }

        return string.substring(start, end + 1);
    }
}
