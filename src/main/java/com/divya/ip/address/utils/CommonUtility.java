package com.divya.ip.address.utils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by Divya.Gupta on 06-04-2018.
 */
public class CommonUtility {

    public static boolean chkNull(Object value) {
        if (value == null) {
            return true;
        }
        String strValue = null;
        if (value instanceof Integer) {
            strValue = value.toString();
        } else if (value instanceof Long) {
            strValue = value.toString();
        } else if (value instanceof Double) {
            strValue = value.toString();
        } else if (value instanceof String) {
            strValue = value.toString();
        } else if (value instanceof List) {
            List list = (List) value;
            return list.isEmpty();
        } else if (value instanceof Map) {
            Map map = (Map) value;
            return map.isEmpty();
        } else if (value instanceof Set) {
            Set set = (Set) value;
            return set.isEmpty();
        } else if (value instanceof Float) {
            strValue = value.toString();
        } else {
            strValue = value.toString();
        }
        //strValue will never null in or condition...removed null check
        if (strValue == null || "undefined".equals(strValue.trim()) || "null".equals(strValue.trim()) || "-1".equals(strValue.trim()) || strValue.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static int randomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static boolean ipHasBeenExpired(long refreshTime, long expiryTime) {
        return (System.currentTimeMillis() - refreshTime) > expiryTime;
    }
}
