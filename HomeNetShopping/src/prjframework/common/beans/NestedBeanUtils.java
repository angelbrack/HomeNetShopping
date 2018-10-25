package prjframework.common.beans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * PropertyUtil 등이 값을 set 할때 null 이 있으면 nested 를 처리 못하는 한계를 극복하기 위해 제작된 유틸
 * 
 * 예를들어 객체의 propertyA.sub1 에 값을 넣고자 할때 propertyA가 null이면 Apache BeanUtil등에서는 값을 넣을수 없다.
 * 
 * NestedBeanUtils에서는 propertyA가 null 이면 propertyA의 기본 생성자을 이용해 프로퍼티를 생성한후 값을 set 하여 한계를 극복했다c.
 * 
 * @author 안지성
 * @version 1.0
 * 
 */
public class NestedBeanUtils {
	public static void setProperty(final Object bean, final String propertyName, final Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, InstantiationException {
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);

		if (propertyName.contains(".")) {
			String[] tokens = propertyName.split("\\.");
			Object owner = bean;
			for (String subPropertyName : tokens) {
				Method getMethod = owner.getClass().getMethod(
						"get" + subPropertyName.substring(0, 1).toUpperCase() + subPropertyName.substring(1));

				Object property = getMethod.invoke(owner, (Object[]) null);
				if (property == null) {
					Class<?> clazz = getMethod.getReturnType();
					try {
						property = clazz.newInstance();
					} catch (Exception e) {
						// 생성 불가능한 클래드들일경우( Long, Interger 등) 셋업을 중지
						break;
					}
					Method setMethod = owner.getClass().getMethod(
							"set" + subPropertyName.substring(0, 1).toUpperCase() + subPropertyName.substring(1), clazz);
					setMethod.invoke(owner, property);
				}
				owner = property;
			}
		}

		BeanUtils.setProperty(bean, propertyName, value);

	}

	public static Object getProperty(Object bean, String fieldName) {
		try {
			return PropertyUtils.getProperty(bean, fieldName);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
