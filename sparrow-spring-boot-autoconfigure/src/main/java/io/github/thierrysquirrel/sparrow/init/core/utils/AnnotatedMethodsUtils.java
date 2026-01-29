/**
 * Copyright 2024/8/9 ThierrySquirrel
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package io.github.thierrysquirrel.sparrow.init.core.utils;

import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: AnnotatedMethodsUtils
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class AnnotatedMethodsUtils {
    private AnnotatedMethodsUtils() {
    }

    public static <T extends Annotation> Map<Method, T> getMethodAndAnnotation(Object bean, Class<T> annotation) {
        Map<Method, T> methodAndAnnotation = new ConcurrentHashMap<>();
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            T t = method.getAnnotation(annotation);
            if (ObjectUtils.isEmpty(t)) {
                continue;
            }
            methodAndAnnotation.put(method, t);
        }
        return methodAndAnnotation;
    }
}
