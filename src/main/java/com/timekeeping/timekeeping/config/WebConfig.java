package com.timekeeping.timekeeping.config;

import com.timekeeping.timekeeping.services.SalaryTemplateConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SalaryTemplateConverter salaryTemplateConverter;

    public WebConfig(SalaryTemplateConverter salaryTemplateConverter) {
        this.salaryTemplateConverter = salaryTemplateConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(salaryTemplateConverter);
    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadDir + "/"); // Serve files from external upload directory
    }
}
