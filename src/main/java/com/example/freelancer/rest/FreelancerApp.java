package com.example.freelancer.rest;

import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
// import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(title = "Freelancer API",
        description = "This API allows CRUD operations on a Freelancer",
        version = "1.0.0",
        contact = @Contact(name = "Quarkus", url = "")),
    servers = {
        @Server(url = "http://localhost:8080")
    },
    externalDocs = @ExternalDocumentation(url = "https://github.com/quarkusio/quarkus-workshops", description = "Sample Quarkus"),
    tags = {
        @Tag(name = "api", description = "Demo RESTful API with JPA"),
        @Tag(name = "freelancers", description = "Find some freelance?")
    }
)
public class FreelancerApp extends Application {

}