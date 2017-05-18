package com.hongshaohua.jtools.common.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaoh on 2017/4/26.
 */
public interface DataConverter<SOURCE, TARGET> {

    TARGET convert(SOURCE source);

    public static <SOURCE, TARGET> TARGET to(DataConverter<SOURCE, TARGET> converter, SOURCE source) {
        if(source == null) {
            return null;
        }
        return converter.convert(source);
    }

    public static <SOURCE, TARGET> List<TARGET> toList(DataConverter<SOURCE, TARGET> converter, List<SOURCE> sourceList) {
        if(sourceList == null) {
            return null;
        }
        List<TARGET> targetList = new ArrayList<>();
        for(SOURCE source : sourceList) {
            if(source != null) {
                TARGET target = converter.convert(source);
                if(target != null) {
                    targetList.add(target);
                }
            }
        }
        return targetList;
    }
}
