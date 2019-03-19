package com.dualupa.canteen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

import static com.dualupa.canteen.dao.dish.Dish.PATH_TO_DISH_IMAGES;

/**
 * @author avbelyaev
 */
@SpringBootApplication
public class CanteenApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanteenApplication.class, args);
    }

    @Configuration
    protected static class WebConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**")
                    .addResourceLocations("classpath:public/static/");
            registry.addResourceHandler(PATH_TO_DISH_IMAGES + "**")
                    .addResourceLocations("classpath:dish-images/")
                    .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        }

    }
}