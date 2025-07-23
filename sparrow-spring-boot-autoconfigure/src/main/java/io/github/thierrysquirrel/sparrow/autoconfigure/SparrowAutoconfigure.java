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
package io.github.thierrysquirrel.sparrow.autoconfigure;

import io.github.thierrysquirrel.sparrow.aspect.SparrowAspect;
import io.github.thierrysquirrel.sparrow.init.ConsumerInit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: SparrowAutoconfigure
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
@Configuration
@EnableConfigurationProperties(SparrowProperties.class)
public class SparrowAutoconfigure {
    @Bean
    @ConditionalOnMissingBean(SparrowAspect.class)
    public SparrowAspect sparrowAspect() {
        return new SparrowAspect();
    }

    @Bean
    @ConditionalOnMissingBean(ConsumerInit.class)
    public ConsumerInit consumerInit() {
        return new ConsumerInit();
    }
}
