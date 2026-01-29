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
package io.github.thierrysquirrel.sparrow.init;

import io.github.thierrysquirrel.sparrow.autoconfigure.SparrowProperties;
import io.github.thierrysquirrel.sparrow.init.core.factory.execution.ConsumerInitFactoryExecution;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ClassName: ConsumerInit
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class ConsumerInit implements ApplicationContextAware {
    @Autowired
    private SparrowProperties sparrowProperties;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        ConsumerInitFactoryExecution.consumerInit(sparrowProperties.getSparrowServerUrl(), applicationContext);
    }
}
