package ru.netology.springboothomework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springboothomework.profiles.DevProfile;
import ru.netology.springboothomework.profiles.ProductionProfile;
import ru.netology.springboothomework.profiles.SystemProfile;


@Configuration
 class JavaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile",
            name = "dev",
            havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile",
            name = "dev",
            havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }

}
