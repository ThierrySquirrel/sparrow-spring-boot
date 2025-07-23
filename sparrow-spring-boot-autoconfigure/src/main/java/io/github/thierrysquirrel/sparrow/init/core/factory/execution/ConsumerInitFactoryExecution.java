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
package io.github.thierrysquirrel.sparrow.init.core.factory.execution;

import io.github.thierrysquirrel.sparrow.annotation.ConsumerListener;
import io.github.thierrysquirrel.sparrow.annotation.SparrowListener;
import io.github.thierrysquirrel.sparrow.core.builder.ThreadPoolExecutorBuilder;
import io.github.thierrysquirrel.sparrow.init.core.factory.ConsumerInitFactory;
import io.github.thierrysquirrel.sparrow.init.core.utils.AnnotatedMethodsUtils;
import io.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.SparrowConsumer;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ConsumerInitFactoryExecution
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class ConsumerInitFactoryExecution {
    private ConsumerInitFactoryExecution() {
    }

    public static void consumerInit(String sparrowServerUrl, ApplicationContext applicationContext) {
        List<SparrowConsumer> sparrowMessageList = new ArrayList<>();
        applicationContext.getBeansWithAnnotation(SparrowListener.class).forEach((beanName, bean) ->
                AnnotatedMethodsUtils.getMethodAndAnnotation(bean, ConsumerListener.class)
                        .forEach((method, consumerListener) -> ConsumerInitFactory.createSparrowConsumer(sparrowMessageList, sparrowServerUrl, bean, method, consumerListener.value())));
        if (sparrowMessageList.isEmpty()) {
            return;
        }
        ThreadPoolExecutor consumerInitThreadPoolExecutor = ThreadPoolExecutorBuilder.builderSparrowConsumerThreadPoolExecutor(sparrowMessageList.size());
        for (SparrowConsumer sparrowConsumer : sparrowMessageList) {
            consumerInitThreadPoolExecutor.execute(sparrowConsumer);
        }
    }
}
