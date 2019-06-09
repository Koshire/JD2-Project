package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.ViewDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ViewService {

    public static ViewDto calculate(ViewDto view) {
        Long page = 1L;
        Long nums;
        if (view.getSize() % view.getLimit() > 0) {
            nums = view.getSize() / view.getLimit() + 1;
        } else {
            nums = view.getSize() / view.getLimit();
        }
        Long offset = 0L;
        if (view.getPage() <= 1) {
            offset = 0L;
        } else if (view.getPage() > 1) {
            offset = view.getLimit() * (view.getPage() - 1);
            page = view.getPage();
        }

        return ViewDto.builder()
                .page(page)
                .limit(view.getLimit())
                .nums(nums)
                .offset(offset)
                .build();
    }
}
