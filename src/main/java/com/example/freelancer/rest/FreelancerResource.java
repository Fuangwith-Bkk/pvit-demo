package com.example.freelancer.rest;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.example.freelancer.jpa.FreelancerRepository;
import com.example.freelancer.model.Freelancer;

import org.jboss.logging.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;

import org.eclipse.microprofile.openapi.annotations.Operation;
// import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
// import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
// import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import  static javax.ws.rs.core.Response.Status.OK;
import  static javax.ws.rs.core.Response.Status.NO_CONTENT;
import  static javax.ws.rs.core.Response.Status.CREATED;
import  static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/freelancers")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@ApplicationScoped
public class FreelancerResource {

    private final FreelancerRepository freelancerRepository;

    private static final Logger logger = Logger.getLogger(FreelancerResource.class);

    @ConfigProperty(name = "app.version", defaultValue = "1.0.0")
    String version;


    public FreelancerResource(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    @GET
    @Operation(
        summary = "Returns all freelancers"
        )
    @APIResponse(
        responseCode = "200", 
        content = @Content(mediaType = APPLICATION_JSON, 
        schema = @Schema(implementation = Freelancer.class, required = true))
        )
    @Counted(
        name = "countFindAll", 
        description = "Counts how many times the findAll method has been invoked"
        )
    @Timed(
        name = "timeFindAll", 
        description = "Times how long it takes to invoke the findAll method", 
        unit = MetricUnits.MILLISECONDS
        )
    //Iterable<Freelancer> 
    public Response findAll() {
        logger.info("finaAll");
        return Response
            .status(OK)
            .encoding(APPLICATION_JSON)
            .entity(freelancerRepository.findAll())
            .build();
    }

    @GET
    @Path("/{freelancerId}")
    @Operation(summary = "Get freelaner by Id")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Freelancer.class, required = true)))
    @APIResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Counted(
        name = "countFindById", 
        description = "Counts how many times the findById method has been invoked"
        )
    @Timed(
        name = "timeFindById", 
        description = "Times how long it takes to invoke the findById method", 
        unit = MetricUnits.MILLISECONDS
        )
    public Response findById(
        @PathParam("freelancerId") long freelancerId
    ) {
        logger.info("findById: " + freelancerId);
        Optional < Freelancer > freelancers = freelancerRepository.findById(freelancerId);
        if (freelancers.isPresent()) {
            logger.info(freelancers.get().getId() + " is found");
            return Response
                .status(OK)
                .encoding(APPLICATION_JSON)
                .entity(freelancers.get())
                .build();
        } else {
            logger.info(freelancerId + " is not found");
            return Response
                .status(NOT_FOUND)
                .encoding(APPLICATION_JSON)
                .build();
        }
    }

    @DELETE
    @Path("/{freelancerId}")
    @Operation(summary = "Returns all freelancers")
    @APIResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Counted(
        name = "countDeleteById", 
        description = "Counts how many times the deleteById method has been invoked"
        )
    @Timed(
        name = "timeDeleteById", 
        description = "Times how long it takes to invoke the deleteById method", 
        unit = MetricUnits.MILLISECONDS
        )
    public Response deleteById(
        @PathParam("freelancerId") long freelancerId
    ) {
        logger.info("deleteById: " + freelancerId);
        freelancerRepository.deleteById(freelancerId);
        return Response
            .status(NO_CONTENT)
            .encoding(APPLICATION_JSON)
            .build();
    }

    @PUT
    @Path("/{freelancerId}")
    @Operation(summary = "Update freelancer")
    @APIResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Counted(
        name = "countUpdateById", 
        description = "Counts how many times the updateById method has been invoked"
        )
    @Timed(
        name = "timeUpdateById", 
        description = "Times how long it takes to invoke the updateById method", 
        unit = MetricUnits.MILLISECONDS
        )
    public Response updateById(
        @PathParam("freelancerId") long freelancerId,
        Freelancer freelancer
    ) {
        logger.info("updateById: " + freelancerId);
        boolean isPresent = freelancerRepository.findById(freelancerId).isPresent();
        
        logger.info("isPresent: " + isPresent);
       
        if (isPresent){
            freelancerRepository.save(freelancer);
            logger.info(freelancer.getId() + " is updated");
            return Response
                .status(NO_CONTENT)
                .encoding(APPLICATION_JSON)
                .build();
         }else{
            Freelancer responseFreelancer = freelancerRepository.save(freelancer);
            logger.info(responseFreelancer.getId() + " is created");
            return Response
                .status(CREATED)
                .encoding(APPLICATION_JSON)
                .entity(responseFreelancer)
                .header("Location", "/freelancers/" + responseFreelancer.getId())
                .build();
        }
            }

    @POST
    @Path("/")
    @Operation(summary = "Create freelancer")
    @APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Counted(
        name = "countCreateFreelancer", 
        description = "Counts how many times the createFreelancer method has been invoked"
        )
    @Timed(
        name = "timeCreateFreelancer", 
        description = "Times how long it takes to invoke the createFreelancer method", 
        unit = MetricUnits.MILLISECONDS
        )
    public Response createFreelancer(
        Freelancer freelancer
    ) {
        logger.info("createFreelancer: " + freelancer.getId());
        return Response.status(CREATED)
            .encoding(APPLICATION_JSON)
            .entity(freelancerRepository.save(freelancer))
            .header("Location", "/freelancers/" + freelancer.getId())
            .build();
    }


}