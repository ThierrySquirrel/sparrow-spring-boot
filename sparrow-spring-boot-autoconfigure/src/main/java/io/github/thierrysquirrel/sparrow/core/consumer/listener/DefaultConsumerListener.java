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
package io.github.thierrysquirrel.sparrow.core.consumer.listener;

import io.github.thierrysquirrel.sparrow.core.consumer.domain.MethodDomain;
import io.github.thierrysquirrel.sparrow.core.consumer.factory.MethodFactory;
import io.github.thierrysquirrel.sparrow.core.exception.SparrowException;
import io.github.thierrysquirrel.sparrow.server.common.hummingbird.consumer.listener.MessageListener;
import io.github.thierrysquirrel.sparrow.server.common.hummingbird.consumer.listener.constant.ConsumerState;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: DefaultConsumerListener
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class DefaultConsumerListener implements MessageListener {
    private static final Logger logger = Logger.getLogger(DefaultConsumerListener.class.getName());

    private final MethodDomain methodDomain;

    public DefaultConsumerListener(MethodDomain methodDomain) {
        this.methodDomain = methodDomain;
    }

    @Override
    public ConsumerState consumer(byte[] message) {
        try {
            MethodFactory.messageInvoke(methodDomain, message);
            return ConsumerState.SUCCESS;
        } catch (SparrowException e) {
            String logMsg = "messageInvoke Error";
            logger.log(Level.WARNING, logMsg, e);
            return ConsumerState.FAIL;
        }
    }
}
