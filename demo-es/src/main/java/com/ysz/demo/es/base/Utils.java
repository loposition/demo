package com.ysz.demo.es.base;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/30 <br/>
 * <B>版本：</B><br/>
 */
public class Utils {


  public static void copyPropertiesFromMap(Map<String, Object> src, Object dest) {
    copyPropertiesFromMap(src, dest, null);
  }


  public static void copyPropertiesFromMap(Map<String, Object> src, Object dest, Set<String> ignoreProperties) {
    if (src == null || dest == null) {
      return;
    }
    Class<?> destClass = dest.getClass();
    for (Map.Entry<String, Object> entry : src.entrySet()) {
      String key = entry.getKey();
      Object val = entry.getValue();
      PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(destClass, key);
      /*如果是要忽略的属性，就直接忽略*/
      if (!CollectionUtils.isEmpty(ignoreProperties) && ignoreProperties.contains(key)) {
        continue;
      }
      /*非空的时候才有必要设值*/
      if (val != null) {
        Method writeMethod = descriptor.getWriteMethod();
        try {
          if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
            writeMethod.setAccessible(true);
          }
          /*希望兼容性更好一点, 做一次类型的转换，因为源是map非常不可靠 ..*/
          Object writeVal = tryConvertType(val, descriptor.getPropertyType());
          writeMethod.invoke(dest, writeVal);
        } catch (Exception ex) {
          throw new RuntimeException("Utils copyPropertiesFromMap bean from map failed", ex);
        }
      }
    }
  }

  private static Object tryConvertType(Object val, Class<?> propertyType) {
    if (val == null) {
      return null;
    }
    if (ClassUtils.isAssignableValue(propertyType, val)) {
      return val;
    }
    if (propertyType == Long.class) {
      return Long.valueOf(Objects.toString(val));
    } else if (propertyType == Byte.class) {
      return Byte.valueOf(Objects.toString(val));
    }
    throw new RuntimeException(
        String.format("tryConvertType failed ,unsupported type:%s,%s,%s",
            val.toString(),
            val.getClass().getName(),
            propertyType.getName()));
  }
}
