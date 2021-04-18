package io.kimmking.spring02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName t
 * @Description TODO
 * @Author yins
 * @Date 2021-4-18
 * @Version 1.0
 **/
@Configuration
@ConditionalOnClass(MsgService.class)
@EnableConfigurationProperties(School.class)
public class Config {
    @Autowired
    private School school;

    @Bean
    @ConditionalOnMissingBean(MsgService.class)
    @ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
    public MsgService demoService() {
        MsgService msgService = new MsgService(school);
        // 如果提供了其他set方法，在此也可以调用对应方法对其进行相应的设置或初始化。
        return msgService;
    }
}
