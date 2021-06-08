package com.xiaojiangtun.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jinhao.chen@miniso.com on 2020/3/24
 */
public class BeanConvertUtils {

	public static void convertBean(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}
	
	public static <T> List<T> convertBeanForList(List<?> sourceList, Class<T> targetClass) {
		if(sourceList != null) {
			return (List<T>) sourceList.stream().map((item)->{
				return convertBean(item, targetClass);
				}).collect(Collectors.toList());
		}
		return null;
	}
	
	public static <T> T convertBean(Object source, Class<T> target) {
		T newInstance = null;
		try {
			if(source != null) {
				newInstance = target.newInstance();
				BeanUtils.copyProperties(source, newInstance);
			}
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return newInstance;
	}
	
}
