package com.itacademy.akulov.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceHelper {

    public static Boolean isPresent(Object object) {
        return object != null;
    }
}
