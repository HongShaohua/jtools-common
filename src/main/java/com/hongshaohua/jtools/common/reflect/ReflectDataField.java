package com.hongshaohua.jtools.common.reflect;

import java.lang.reflect.Field;

/**
 * Created by Aska on 2018/2/2.
 */
public class ReflectDataField {

    private Field field;
    private ReflectDataClass clazz;
    private String name;

    public ReflectDataField(Field field, ReflectDataClass clazz, String name) {
        this.field = field;
        this.clazz = clazz;
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public ReflectDataClass getClazz() {
        return clazz;
    }

    public void setClazz(ReflectDataClass clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
