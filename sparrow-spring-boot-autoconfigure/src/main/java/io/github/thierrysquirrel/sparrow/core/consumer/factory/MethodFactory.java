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
package io.github.thierrysquirrel.sparrow.core.consumer.factory;

import io.github.thierrysquirrel.sparrow.core.consumer.domain.MethodDomain;
import io.github.thierrysquirrel.sparrow.core.consumer.factory.constant.MethodFactoryConstant;
import io.github.thierrysquirrel.sparrow.core.exception.SparrowException;
import io.github.thierrysquirrel.sparrow.server.common.netty.coder.utils.SerializerUtils;

import java.lang.reflect.Method;

/**
 * ClassName: MethodFactory
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class MethodFactory {
    private MethodFactory() {
    }

    public static Class<?> getParameterType(Method method) {
        return method.getParameterTypes()[MethodFactoryConstant.METHOD_PARAMETER_TYPE];
    }

    public static void messageInvoke(MethodDomain methodDomain, byte[] message) throws SparrowException {
        Object parameter = SerializerUtils.deSerialize(message, methodDomain.getParameterType());
        invoke(methodDomain.getBean(), methodDomain.getMethod(), parameter);
    }

    private static void invoke(Object bean, Method method, Object parameter) throws SparrowException {
        try {
            method.invoke(bean, parameter);
        } catch (Exception e) {
            throw new SparrowException("invokeError", e);
        }
    }
}
