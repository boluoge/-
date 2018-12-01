package com.chenway.eshop.util;

import java.util.Collection;

public class ValidateUtil {
    public static boolean isVaild(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        return true;
    }
}
