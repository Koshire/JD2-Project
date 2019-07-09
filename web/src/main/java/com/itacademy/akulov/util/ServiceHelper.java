package com.itacademy.akulov.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.io.IOException;

@UtilityClass
public class ServiceHelper {

    private static final String ATTACHMENT = "attachment;filename=%s";

    public static Boolean isPresent(Object object) {
        return object != null;
    }

    public void setMessage(Model model, boolean result, ResourceBundleMessageSource rs) {
        model.addAttribute("message",
                result
                        ? rs.getMessage("message.update.success", null, LocaleContextHolder.getLocale())
                        : rs.getMessage("message.update.error", null, LocaleContextHolder.getLocale()));
    }

    public ResponseEntity<byte[]> setResource(byte[] res, String fileName) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format(ATTACHMENT, fileName))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(res.length)
                .body(res);
    }
}
