package com.fitness.aplicacion.utilidades;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fitness.aplicacion.controladores"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Usuarios API",
                "Esta API proporciona servicios para la gestión de usuarios, dietas y " +
                        "rutinas de ejercicio en una aplicación de salud y fitness. Permite registrar,"+
                        " verificar, actualizar y eliminar usuarios, así como gestionar sus datos personales. "+
                        "Además, ofrece funcionalidades para la creación, modificación y consulta de dietas"+
                        " personalizadas y rutinas de ejercicio.",
                "1.0",
                "http://codmind.com/terms",
                new Contact("Fitness Tracker Team", "https://github.com/kateBea/Fitness-Tracker", "fitness.tracker.opal@gmail.com"),
                "MIT",
                "https://github.com/kateBea/Fitness-Tracker/blob/main/LICENSE",
                Collections.emptyList()
        );
    }
}