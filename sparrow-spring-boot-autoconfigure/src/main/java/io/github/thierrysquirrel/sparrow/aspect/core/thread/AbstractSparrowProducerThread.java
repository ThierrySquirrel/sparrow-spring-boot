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
package io.github.thierrysquirrel.sparrow.aspect.core.thread;

/**
 * ClassName: AbstractSparrowProducerThread
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public abstract class AbstractSparrowProducerThread implements Runnable {

    private final String sparrowServerUrl;
    private final String topic;
    private final byte[] message;

    protected AbstractSparrowProducerThread(String sparrowServerUrl, String topic, byte[] message) {
        this.sparrowServerUrl = sparrowServerUrl;
        this.topic = topic;
        this.message = message;
    }

    /**
     * sendMessage
     *
     * @param sparrowServerUrl sparrowServerUrl
     * @param topic            topic
     * @param message          message
     */
    protected abstract void sendMessage(String sparrowServerUrl, String topic, byte[] message);

    @Override
    public void run() {
        sendMessage(this.sparrowServerUrl, this.topic, this.message);
    }
}
