package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component // 被标记的类会被自动扫描并注册为 Spring Bean，以便进行依赖注入和管理。
@ConfigurationProperties(prefix = "sky.alioss") // 通过 @ConfigurationProperties 自动绑定配置文件中的值。
@Data
public class AliOssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}
