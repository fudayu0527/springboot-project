package com.xiaojiangtun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @project:
 * @description: Json/Map 与 Java bean的转换工具
 * @author: dengFuHua
 * @create: 2020-01-04 20:23
 **/
public class BeanUtil {

    private static final int BEAN_COPY_COUNT_LIMIT = 5;

    private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);

    private BeanUtil() {
    }

    /**
     * 批量转换map为目标类型
     * @param name2values
     * @param tClass
     * @param <T>
     * @return
     */
    public static<T> List<T> batchCopyMapByTheSameFieldName(List<Map<String, Object>> name2values, Class<T> tClass) {
        if (null == name2values || null == tClass) {
            return null;
        }
        ArrayList<T> res = new ArrayList<>();
        name2values.forEach(m -> res.add(copyMapByTheSameFieldName(m, tClass)));
        return res;
    }

    /**
     * 递归转换map为目标类型
     * @param name2value
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T copyMapByTheSameFieldName(Map<String, Object> name2value, Class<T> clazz, boolean isRecursive) {
        //todo
        return null;
    }

    /**
     * 转换map为目标类型
     * @param name2value
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T copyMapByTheSameFieldName(Map<String, Object> name2value, Class<T> clazz) {
        if (null == name2value || null == clazz) {
            return null;
        }
        return new JSONObject(name2value).toJavaObject(clazz);
    }

    //-------------------------- JSON -> Bean -----------------------------------------------
    public static<T> T copyBeanByTheSameFieldName(JSONObject jsonObject, Class<T> clazz) {
        if (null == clazz || null == jsonObject) {
            return null;
        }
        return jsonObject.toJavaObject(clazz);
    }

    public static <S,T> T copyBeanByTheSameFieldName(S srcObj, Class<T> clazz) {
        return copyBeanByTheSameFieldName(srcObj, clazz, null, null);
    }

    /**
     *
     * @param srcObj
     * @param clazz
     * @param parentClazz 指定srcObj的父类（直接基类及间接基类）
     * @param <T>
     * @return
     */
    public static <T, S> T copyBeanByTheSameFieldName(S srcObj, Class<T> clazz, Class<? super S> parentClazz, Class<? super T> parentTargetClass) {
        if (null == srcObj || null == clazz) {
            return null;
        }
        //为了使方法也能在有实例带副作用的场景中使用（例如对JPA非游离态下的entity操作），所以即便参数传入类型（clazz）与srcObj类型一样的bean复制，该方法也要复制出新的bean返回
//        if (clazz.equals(srcObj.getClass())) {
//            return (T)srcObj;
//        }
        Map<String, Object> jsonObject = reverseBeanByTheSameFieldName(srcObj, clazz, parentClazz, parentTargetClass);
        return copyBeanByTheSameFieldName((JSONObject) jsonObject, clazz);
    }

    public static <F, T> List<T> batchCopyBeanByTheSameFieldName(List<F> objs, Class<T> tClass) {
        return batchCopyBeanByTheSameFieldName(objs, tClass, null, null);
    }
    public static <F, T> List<T> batchCopyBeanByTheSameFieldName(List<F> objs, Class<T> tClass, Class<? super F> srcParentClass, Class<? super T> targetParentClass) {
        ArrayList<T> objects = new ArrayList<>();
        if (null == objs || null == tClass) {
            return objects;
        }
        for (F obj : objs) {
            objects.add(copyBeanByTheSameFieldName(obj, tClass, srcParentClass, targetParentClass));
        }
        return objects;
    }

    public static <T, F> List<Map<String, Object>> batchReverseBeanByTheSameFieldName(List<T> objs, Class<F> tClass) {
        if (null == objs) {
            return null;
        }
        return objs.stream().map(obj -> reverseBeanByTheSameFieldName(obj, tClass)).collect(Collectors.toList());
    }

    public static <T, F> Map<String, Object> reverseBeanByTheSameFieldName(T obj, Class<F> tClass) {
        return reverseBeanByTheSameFieldName(obj, tClass, null, null);
    }

    public static <T, F> Map<String, Object> reverseBeanByTheSameFieldName(T srcObj, Class<F> tClass, Class<? super T> parentSrcClass, Class<? super F> parentTargetClass) {
        if (BeanUtil.isExistsNullObject(srcObj, tClass)) {
            return null;
        }
        //获取目标类（根据参数控制是否额外遍历其直接基类及其间接基类）的(字段名,字段信息)映射表
        Map<String, ? extends Class<?>> name2type = genTargetClassOfName2Type(tClass, parentTargetClass);
        //根据目标类字段映射集name2type和来源类srcObj转化出目标json对象
        JSONObject jsonObject = new JSONObject();
        try {
            int i = 0;
            Class<?> clazz = srcObj.getClass();
            Class<?> targetClass = null == parentSrcClass ? clazz : parentSrcClass;
            while (targetClass != clazz && Object.class != clazz && i < BEAN_COPY_COUNT_LIMIT) { //防止死循环耗尽资源
                fillJsonObjFromSrcObj(srcObj, clazz, name2type, jsonObject);
                clazz = clazz.getSuperclass();
                i ++;
            }
            fillJsonObjFromSrcObj(srcObj, targetClass, name2type, jsonObject);
            if (i == BEAN_COPY_COUNT_LIMIT) {
                log.info("UTIL_CLASS_COPY_OVERFLOW "+ ":reverseBeanByTheSameFieldName");
            }
        }catch (Throwable e) {
            log.error("UTIL_CLASS_ERROR", e);
        }
        return jsonObject;
    }

    private static <F> Map<String, ? extends Class<?>> genTargetClassOfName2Type(Class<F> tClass, Class<?> targetParentClass) {
        ArrayList<Field> allFields = new ArrayList<>();
        Class<?> aClass = tClass;
        int i = 0;
        while (null != targetParentClass && aClass != targetParentClass && Object.class != aClass && i < BEAN_COPY_COUNT_LIMIT) {
            allFields.addAll(Arrays.stream(aClass.getDeclaredFields()).collect(Collectors.toList()));
            aClass = aClass.getSuperclass();
            i ++;
        }
        if (i == BEAN_COPY_COUNT_LIMIT) {
            log.info("UTIL_CLASS_COPY_OVERFLOW" + ":genTargetClassOfName2Type");
        }
        allFields.addAll(Arrays.stream(aClass.getDeclaredFields()).collect(Collectors.toList()));
        return allFields.stream().collect(Collectors.toMap(Field::getName, Field::getType, (oldObj, newObj) -> newObj));
    }

    private static <T> void fillJsonObjFromSrcObj(T srcObj, Class<?> srcClass, Map<String, ? extends Class<?>> name2type, JSONObject jsonObject) throws IllegalAccessException {
        Field[] declaredFields = srcClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String name = declaredField.getName();

            if (!name2type.containsKey(name)) {
                continue;
            }
            Class<?> type = name2type.get(name);
            if (null == type) {
                continue;
            }
            Class<?> declaredFieldType = declaredField.getType();
            if (
                    (null == declaredFieldType)
                    || (!type.isAssignableFrom(declaredFieldType) && !declaredFieldType.isAssignableFrom(type))
            ) {
                continue;
            }
            Object o = declaredField.get(srcObj);
            jsonObject.put(name, o);
        }
    }

    @SafeVarargs
    public static<T> T getFirstNoneNullObjectIfExists(T ... objects) {
        if (null == objects) {
            return null;
        }
        for (T o : objects) {
            if (null != o) {
                return o;
            }
        }
        return null;
    }

    public static boolean isExistsNullObject(Object ... objects) {
        for (Object object : objects) {
            if (null == object) {
                return true;
            }
        }
        return false;
    }

    public static<T> List<T> batchConvertMaps2TargetClass(List<Map<String, Object>> mapList, Class<T> tClass) {
        if (null == mapList || null == tClass) {
            return null;
        }
        return mapList.stream().map(map -> convertMap2targetClass(map, tClass)).collect(Collectors.toList());
    }

    public static<T> T convertMap2targetClass(Map<String, Object> name2value, Class<T> tClass) {
        if (null == name2value || null == tClass) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(name2value);
        return jsonObject.toJavaObject(tClass);
    }

    public static<T> T fillObjectFromSrcToDes(T src, T des) {
        Field[] declaredFields = des.getClass().getDeclaredFields();
        Field[] fields = src.getClass().getDeclaredFields();
        Map<String, Field> name2field = Arrays.stream(fields).collect(Collectors.toMap(Field::getName, f -> f));
        try {
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                declaredField.setAccessible(true);
                Field field = name2field.get(name);
                if (null == field) {
                    continue;
                }
                field.setAccessible(true);
                if (!field.getType().isAssignableFrom(declaredField.getType()) && !declaredField.getType().isAssignableFrom(field.getType())) {
                    continue;
                }
                Object o = declaredField.get(des);
                if (null == o) {
                    declaredField.set(des, field.get(src));
                }
            }
        }catch (Exception ex) {
            log.error("UTIL_CLASS_ERROR", ex);
        }
        return des;
    }

    public static<T> String toJSONArrayStringFromList(List<T> list) {
        if (null == list) {
            return null;
        }
        return JSONArray.toJSONString(list);
    }

    public static<T> List<T> toListFromJSONArrayString(String jsonArray, Class<T> tClass) {
        return JSONArray.parseArray(jsonArray, tClass);
    }

    public static<T> String toJSONObjectStringFromJavaObject(T object) {
        return null == object ? null : JSONObject.toJSONString(object);
    }

    public static<T> T toJavaObjectFromJSONObjectString(String jsonObj, Class<T> tClass) {
        return toJavaObjectFromJSONObjectString(JSONObject.parseObject(jsonObj), tClass);
    }

    private static<T> T toJavaObjectFromJSONObjectString(JSON jsonObj, Class<T> tClass) {
        return JSONObject.toJavaObject(jsonObj, tClass);
    }
}
