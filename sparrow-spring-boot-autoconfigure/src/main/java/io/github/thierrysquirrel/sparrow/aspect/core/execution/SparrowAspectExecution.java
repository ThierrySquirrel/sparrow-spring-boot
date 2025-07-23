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
package io.github.thierrysquirrel.sparrow.aspect.core.execution;

import io.github.thierrysquirrel.sparrow.annotation.Producer;
import io.github.thierrysquirrel.sparrow.core.exception.SparrowException;
import io.github.thierrysquirrel.sparrow.server.common.netty.coder.utils.SerializerUtils;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * ClassName: SparrowAspectExecution
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class SparrowAspectExecution {
    private SparrowAspectExecution() {
    }

    public static Object sendMessage(ProceedingJoinPoint point, Producer producer, String sparrowServerUrl) throws SparrowException {
        try {
            Object proceed = point.proceed();
            String topic = producer.value();
            SparrowProducerExecution.sendMessage(sparrowServerUrl, topic, SerializerUtils.serialize(proceed));
            return proceed;
        } catch (Throwable throwable) {
            throw new SparrowException("sendMessage Error", throwable);
        }
    }
}
