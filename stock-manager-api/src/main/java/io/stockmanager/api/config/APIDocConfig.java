package io.stockmanager.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIDocConfig {

    private static final String APP_TITLE = "Stock Manager Backend Application";
    private static final String APP_DESCRIPTION = "Stock Manager Backend Application to manager enterprises stock quotes.";
    private static final String APP_VERSION = "1.0.0";

    private static final String SWAGGER_TERMS = "https://swagger.io/terms";
    private static final String SWAGGER_LICENSE_NAME = "Apache 2.0";
    private static final String SWAGGER_LICENSE_URL = "http://springdoc.org";

    private static final String CONTACT_NAME = "Eriton Santos";
    private static final String CONTACT_GITHUB = "https://github.com/eritonsg";
    private static final String CONTACT_EMAIL = "dev.eritonsg@gmail.com";

    @Bean
    public OpenAPI customOpenAPI() {

        Info info = new Info()
                .title(APP_TITLE)
                .description(APP_DESCRIPTION)
                .version(APP_VERSION)
                .termsOfService(SWAGGER_TERMS)
                .contact(getContact())
                .license(getLicense());

        return new OpenAPI().info(info);
    }

    private License getLicense() {
        return new License()
                .name(SWAGGER_LICENSE_NAME)
                .url(SWAGGER_LICENSE_URL);
    }

    private Contact getContact() {
        return new Contact()
                .name(CONTACT_NAME)
                .email(CONTACT_EMAIL)
                .url(CONTACT_GITHUB);
    }

}
