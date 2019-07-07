package com.itacademy.akulov.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.Model;

@UtilityClass
public class ServiceHelper {

    public static Boolean isPresent(Object object) {
        return object != null;
    }

    public void setMessage(Model model, boolean result, ResourceBundleMessageSource rs) {
        model.addAttribute("message",
                result
                        ? rs.getMessage("message.update.success", null, LocaleContextHolder.getLocale())
                        : rs.getMessage("message.update.error", null, LocaleContextHolder.getLocale()));
    }
}
