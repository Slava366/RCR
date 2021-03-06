package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Конфигурация MVC
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/add").setViewName("add");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
    }
}
