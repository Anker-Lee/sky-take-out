package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration // 只有在 @Configuration 类中，@Bean 注解的方法才会被正确解析为 Spring 容器中的 Bean
public class OssConfiguration {

    @Bean // Spring 容器会在启动时调用该方法，方法的返回值应该作为一个 Bean 注册到容器中。
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        log.info("开始创建阿里云文件上传工具类对象: {}", aliOssProperties);
        return new AliOssUtil(
                aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName()
        );
    }
}

/*
这段代码会在项目启动时自动运行，是因为以下原因：
1. `@Configuration` 注解：
   - 类 `OssConfiguration` 被标注为 `@Configuration`，表示这是一个 Spring 的配置类，Spring 容器会在启动时加载并解析其中的内容。
2. `@Bean` 注解：
   - 方法 `aliOssUtil` 被标注为 `@Bean`，Spring 容器会在启动时调用该方法，并将其返回值（`AliOssUtil` 对象）注册为一个 Bean。
3. `@ConditionalOnMissingBean` 注解：
   - 该注解的作用是：只有当 Spring 容器中没有同类型的 Bean 时，才会执行该方法创建 Bean。这确保了不会重复创建 Bean。
4. Spring Boot 自动配置机制：
   - Spring Boot 会扫描 `@Configuration` 类，并根据 `@Bean` 注解的方法自动初始化相关的 Bean。因此，项目启动时，`aliOssUtil` 
   方法会被调用，`AliOssUtil` 对象会被创建并注册到 Spring 容器中。
5. 日志输出：
   - 方法中调用了 `log.info`，因此在项目启动时会输出日志，表明该方法被执行了。
总结：Spring Boot 的自动配置机制会在项目启动时加载 `@Configuration` 类，
    并执行 `@Bean` 方法来初始化和注册 Bean，这就是为什么项目一运行这段代码就会被执行的原因。
 */
