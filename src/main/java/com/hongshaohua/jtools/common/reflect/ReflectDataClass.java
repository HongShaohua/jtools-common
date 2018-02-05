package com.hongshaohua.jtools.common.reflect;

import com.google.gson.internal.LinkedHashTreeMap;

import java.lang.reflect.*;
import java.util.*;

/**
 * Created by Aska on 2018/2/2.
 *
 * 只用于数据结构序列化
 * 只支持几个基本的数据类型
 */
public class ReflectDataClass {

    //基础类型跟String等不再进行解析
    private static Set<Class> ignoreClasses = null;
    private static void ignoreClasses(Set<Class> ignoreClasses) {
        ignoreClasses.add(Object.class);

        ignoreClasses.add(boolean.class);
        ignoreClasses.add(Boolean.class);

        ignoreClasses.add(byte.class);
        ignoreClasses.add(Byte.class);

        ignoreClasses.add(char.class);
        ignoreClasses.add(Character.class);

        ignoreClasses.add(short.class);
        ignoreClasses.add(Short.class);

        ignoreClasses.add(int.class);
        ignoreClasses.add(Integer.class);

        ignoreClasses.add(long.class);
        ignoreClasses.add(Long.class);

        ignoreClasses.add(float.class);
        ignoreClasses.add(Float.class);

        ignoreClasses.add(double.class);
        ignoreClasses.add(Double.class);

        ignoreClasses.add(String.class);

        ignoreClasses.add(List.class);
        ignoreClasses.add(ArrayList.class);
        ignoreClasses.add(LinkedList.class);

        ignoreClasses.add(Set.class);
        ignoreClasses.add(HashSet.class);
        ignoreClasses.add(TreeSet.class);
        ignoreClasses.add(LinkedHashSet.class);

        ignoreClasses.add(Map.class);
        ignoreClasses.add(HashMap.class);
        ignoreClasses.add(TreeMap.class);
        ignoreClasses.add(LinkedHashMap.class);
        ignoreClasses.add(LinkedHashTreeMap.class);

        ignoreClasses.add(ReflectDataClass.class);
        ignoreClasses.add(ReflectDataField.class);
    }
    private static Set<Class> getIgnoreClasses() {
        if(ignoreClasses == null) {
            ignoreClasses = new HashSet<>();
            ignoreClasses(ignoreClasses);
        }
        return ignoreClasses;
    }
    private static boolean ignore(Class clazz) {
        return getIgnoreClasses().contains(clazz);
    }

    //父类
    private ReflectDataClass parentClass;
    //本类
    private Class clazz;
    //数组类
    private ReflectDataClass componentClass;
    //模板类
    private List<ReflectDataClass> genericClasses = new ArrayList<>();
    //内部属性
    private List<ReflectDataField> fields = new ArrayList<>();

    /**
     * 不支持运行时模板类
     * 需要解析模板类时，需要传入继承目标模板类的无模板类
     * */
    public ReflectDataClass(Class clazz) {
        this.clazz = clazz;
        if(this.clazz.isArray()) {
            this.componentClass = parseClass(this.clazz.getComponentType());
            return;
        }
        if(ignore(this.clazz)) {
            return;
        }
        this.parentClass = parseParentClass(this.clazz);
        this.genericClasses = new ArrayList<>();
        this.fields = parseFields(this.clazz);
    }

    /**
     * 用于解析模板类，不开发外部使用
     * */
    private ReflectDataClass(ParameterizedType parameterizedType) {
        this.clazz = (Class) parameterizedType.getRawType();
        this.genericClasses = parseGenericClasses(parameterizedType);
        if(ignore(this.clazz)) {
            return;
        }
        this.parentClass = parseParentClass(this.clazz);
        this.fields = parseFields(this.clazz);
    }

    /**
     * 用于解析数组模板类，不开发外部使用
     * */
    private ReflectDataClass(GenericArrayType genericArrayType) {
        this.clazz = Object.class;
        this.componentClass = parseClass(genericArrayType.getGenericComponentType());
    }

    public static ReflectDataClass parseClass(Type type) {
        if(type instanceof Class) {
            //普通类
            Class clazz = (Class) type;
            if(clazz.equals(Object.class)) {
                return null;
            }
            return new ReflectDataClass(clazz);
        } else if(type instanceof ParameterizedType) {
            //有泛型类
            ParameterizedType parameterizedType = (ParameterizedType)type;
            return new ReflectDataClass(parameterizedType);
        } else if(type instanceof GenericArrayType) {
            //泛型数组类
            GenericArrayType genericArrayType = (GenericArrayType)type;
            return new ReflectDataClass(genericArrayType);
        }
        return null;
    }

    private static ReflectDataClass parseParentClass(Class clazz) {
        return parseClass(clazz.getGenericSuperclass());
    }

    private static List<ReflectDataClass> parseGenericClasses(ParameterizedType parameterizedType) {
        List<ReflectDataClass> genericClasses = new ArrayList<>();
        Type[] genericTypes = parameterizedType.getActualTypeArguments();
        for(Type genericType : genericTypes) {
            genericClasses.add(parseClass(genericType));
        }
        return genericClasses;
    }

    private static List<ReflectDataField> parseFields(Class clazz) {
        List<ReflectDataField> fields = new ArrayList<>();
        //获得当前类的所有属性
        Field[] fs = clazz.getDeclaredFields();
        for(Field f : fs) {
            if(Modifier.isStatic(f.getModifiers())) {
                continue;
            }
            f.setAccessible(true);
            String name = f.getName();
            Type type = f.getGenericType();
            fields.add(new ReflectDataField(f, parseClass(type), name));
        }
        return fields;
    }

    public ReflectDataClass getParentClass() {
        return parentClass;
    }

    public Class getClazz() {
        return clazz;
    }

    public ReflectDataClass getComponentClass() {
        return componentClass;
    }

    public List<ReflectDataClass> getGenericClasses() {
        return genericClasses;
    }

    public List<ReflectDataField> getFields() {
        return fields;
    }

    public List<ReflectDataField> getAllFields() {
        List<ReflectDataField> allFields = new ArrayList<>();
        if(this.parentClass != null) {
            allFields.addAll(this.parentClass.getAllFields());
        }
        allFields.addAll(this.getFields());
        return allFields;
    }

    public boolean isObject() {
        if(this.clazz.equals(Object.class)) {
            return true;
        }
        return false;
    }

    public boolean isBoolean() {
        if(this.clazz.equals(boolean.class) || this.clazz.equals(Boolean.class)) {
            return true;
        }
        return false;
    }

    public boolean isByte() {
        if(this.clazz.equals(byte.class) || this.clazz.equals(Byte.class)) {
            return true;
        }
        return false;
    }

    public boolean isChar() {
        if(this.clazz.equals(char.class) || this.clazz.equals(Character.class)) {
            return true;
        }
        return false;
    }

    public boolean isShort() {
        if(this.clazz.equals(short.class) || this.clazz.equals(Short.class)) {
            return true;
        }
        return false;
    }

    public boolean isInt() {
        if(this.clazz.equals(int.class) || this.clazz.equals(Integer.class)) {
            return true;
        }
        return false;
    }

    public boolean isLong() {
        if(this.clazz.equals(long.class) || this.clazz.equals(Long.class)) {
            return true;
        }
        return false;
    }

    public boolean isFloat() {
        if(this.clazz.equals(float.class) || this.clazz.equals(Float.class)) {
            return true;
        }
        return false;
    }

    public boolean isDouble() {
        if(this.clazz.equals(double.class) || this.clazz.equals(Double.class)) {
            return true;
        }
        return false;
    }

    public boolean isString() {
        if(this.clazz.equals(String.class)) {
            return true;
        }
        return false;
    }

    public boolean isArray() {
        //普通数组类
        if(this.clazz.isArray()) {
            return true;
        }
        //模板数组类
        if(this.componentClass != null) {
            return true;
        }
        return false;
    }

    public boolean isList() {
        if(this.clazz.equals(List.class)) {
            return true;
        }
        return false;
    }

    public boolean isArrayList() {
        if(this.clazz.equals(ArrayList.class)) {
            return true;
        }
        return false;
    }

    public boolean isLinkedList() {
        if(this.clazz.equals(LinkedList.class)) {
            return true;
        }
        return false;
    }

    public boolean isSet() {
        if(this.clazz.equals(Set.class)) {
            return true;
        }
        return false;
    }

    public boolean isHashSet() {
        if(this.clazz.equals(HashSet.class)) {
            return true;
        }
        return false;
    }

    public boolean isTreeSet() {
        if(this.clazz.equals(TreeSet.class)) {
            return true;
        }
        return false;
    }

    public boolean isLinkedHashSet() {
        if(this.clazz.equals(LinkedHashSet.class)) {
            return true;
        }
        return false;
    }

    public boolean isMap() {
        if(this.clazz.equals(Map.class)
                || this.clazz.equals(HashMap.class)
                || this.clazz.equals(TreeMap.class)
                || this.clazz.equals(LinkedHashMap.class)
                || this.clazz.equals(LinkedHashTreeMap.class)) {
            return true;
        }
        return false;
    }

    public boolean isHashMap() {
        if(this.clazz.equals(HashMap.class)) {
            return true;
        }
        return false;
    }

    public boolean isTreeMap() {
        if(this.clazz.equals(TreeMap.class)) {
            return true;
        }
        return false;
    }

    public boolean isLinkedHashMap() {
        if(this.clazz.equals(LinkedHashMap.class)) {
            return true;
        }
        return false;
    }

    public boolean isLinkedHashTreeMap() {
        if(this.clazz.equals(LinkedHashTreeMap.class)) {
            return true;
        }
        return false;
    }
}
