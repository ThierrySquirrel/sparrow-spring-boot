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
package io.github.thierrysquirrel.sparrow.init.core.factory;

import io.github.thierrysquirrel.sparrow.core.consumer.domain.MethodDomain;
import io.github.thierrysquirrel.sparrow.core.consumer.domain.builder.MethodDomainBuilder;
import io.github.thierrysquirrel.sparrow.core.consumer.factory.MethodFactory;
import io.github.thierrysquirrel.sparrow.core.consumer.listener.DefaultConsumerListener;
import io.github.thierrysquirrel.sparrow.server.common.hummingbird.consumer.init.client.SparrowConsumer;
import io.github.thierrysquirrel.sparrow.server.common.hummingbird.utils.constant.SocketAddressConstant;

import java.lang.reflect.Method;
import java.util.List;

/**
 * ClassName: ConsumerInitFactory
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class ConsumerInitFactory {
    private ConsumerInitFactory() {
    }

    public static void createSparrowConsumer(List<SparrowConsumer> sparrowConsumerList, String sparrowServerUrl, Object bean, Method method, String topic) {
        MethodDomain methodDomain = MethodDomainBuilder.builderMethodDomain(bean, method, MethodFactory.getParameterType(method));
        DefaultConsumerListener defaultConsumerListener = new DefaultConsumerListener(methodDomain);
        String[] split = sparrowServerUrl.split(SocketAddressConstant.URL_SEPARATOR);
        for (String url : split) {
            SparrowConsumer sparrowConsumer = new SparrowConsumer(defaultConsumerListener, url, topic);
            sparrowConsumerList.add(sparrowConsumer);
        }
    }
}
