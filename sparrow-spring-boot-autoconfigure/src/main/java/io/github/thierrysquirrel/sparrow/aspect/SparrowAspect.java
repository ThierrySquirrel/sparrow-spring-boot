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
package io.github.thierrysquirrel.sparrow.aspect;

import io.github.thierrysquirrel.sparrow.annotation.Producer;
import io.github.thierrysquirrel.sparrow.aspect.core.execution.SparrowAspectExecution;
import io.github.thierrysquirrel.sparrow.aspect.utils.SparrowAspectUtils;
import io.github.thierrysquirrel.sparrow.autoconfigure.SparrowProperties;
import io.github.thierrysquirrel.sparrow.core.exception.SparrowException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName: SparrowAspect
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
@Aspect
@Data
@Slf4j
public class SparrowAspect {
    @Autowired
    private SparrowProperties sparrowProperties;

    @Pointcut("@annotation(io.github.thierrysquirrel.sparrow.annotation.Producer)")
    public void producerPointcut() {
        log.debug("Start Producer");
    }

    @Around("producerPointcut()")
    public Object sparrowProducerAround(ProceedingJoinPoint point) throws SparrowException {
        return SparrowAspectExecution.sendMessage(point,
                SparrowAspectUtils.getAnnotation(point, Producer.class), sparrowProperties.getSparrowServerUrl());
    }
}
