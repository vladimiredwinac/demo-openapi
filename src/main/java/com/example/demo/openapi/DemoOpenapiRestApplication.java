package com.example.demo.openapi;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * In this class you can define additional components of the application
 *
 * @author Vladimir Edwin Alaro
 * @version 1.0
 * @since 2020-12-04
 */
@OpenAPIDefinition(
        info = @Info(title = "BolivianJUG Talks", version = "1.0.0",
                description = "You can find information about talks that were given in our first year",
                contact = @Contact(name = "Java User Group Bolivia", url = "https://bolivianjug.org/", email = "contacto@bolivianjug.org")
        ),
        tags = {
                @Tag(name = "Talks", description = "Information about talks, dates and speakers"),
                @Tag(name = "Speakers", description = "Speaker name who gave the talk")
        },
        security = {
                @SecurityRequirement(name = "Basic Authentication")
        },
        components = @Components(
                securitySchemes = {
                        @SecurityScheme(
                                securitySchemeName = "Basic Authentication", scheme = "basic",
                                type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER
                        )
                }
        )
)
@ApplicationPath("/demo-openapi")
@ApplicationScoped
public class DemoOpenapiRestApplication extends Application {
}
