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

import io.github.thierrysquirrel.sparrow.aspect.core.thread.execution.SparrowProducerThreadExecution;
import io.github.thierrysquirrel.sparrow.core.constant.ThreadPoolExecutorConstant;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: SparrowProducerExecution
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class SparrowProducerExecution {
    private SparrowProducerExecution() {
    }

    public static void sendMessage(String sparrowServerUrl, String topic, byte[] message) {
        SparrowProducerThreadExecution sparrowProducerThreadExecution = new SparrowProducerThreadExecution(sparrowServerUrl, topic, message);
        ThreadPoolExecutor sparrowProducer = ThreadPoolExecutorConstant.SPARROW_PRODUCER;
        sparrowProducer.execute(sparrowProducerThreadExecution);
    }
}
