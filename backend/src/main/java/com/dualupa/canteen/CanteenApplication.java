package com.dualupa.canteen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

import static com.dualupa.canteen.service.InitialCatalogLoader.CANTEEN_IMAGE_PATH;

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
            // static data (for SPA bundle)
            registry.addResourceHandler("/static/**")
                    .addResourceLocations("classpath:public/static/")
                    .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
            // non-static images
            registry.addResourceHandler(CANTEEN_IMAGE_PATH + "**")
                    .addResourceLocations("classpath:" + CANTEEN_IMAGE_PATH)
                    .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
        }

    }
}