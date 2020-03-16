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
import javax.ws.rs.core.Response.Status;

import com.example.freelancer.jpa.FreelancerRepository;
import com.example.freelancer.model.Freelancer;

import org.jboss.logging.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/freelancers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    //Iterable<Freelancer> 
    public Response findAll() {
        logger.info("finaAll");
        return Response
            .status(Status.OK)
            .encoding(MediaType.APPLICATION_JSON)
            .entity(freelancerRepository.findAll())
            .build();
    }
    @GET
    @Path("/{freelancerId}")
    public Response findById(
        @PathParam("freelancerId") long freelancerId
    ) {
        logger.info("findById: " + freelancerId);
        Optional < Freelancer > freelancers = freelancerRepository.findById(freelancerId);
        if (freelancers.isPresent()) {
            logger.info(freelancers.get().getId() + " is found");
            return Response
                .status(Status.OK)
                .encoding(MediaType.APPLICATION_JSON)
                .entity(freelancers.get())
                .build();
        } else {
            logger.info(freelancerId + " is not found");
            return Response
                .status(Status.NOT_FOUND)
                .encoding(MediaType.APPLICATION_JSON)
                .build();
        }
    }

    @DELETE
    @Path("/{freelancerId}")
    public Response deleteById(
        @PathParam("freelancerId") long freelancerId
    ) {
        logger.info("deleteById: " + freelancerId);
        freelancerRepository.deleteById(freelancerId);
        return Response
            .status(Status.NO_CONTENT)
            .encoding(MediaType.APPLICATION_JSON)
            .build();
    }

    @PUT
    @Path("/{freelancerId}")
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
                .status(Status.NO_CONTENT)
                .encoding(MediaType.APPLICATION_JSON)
                .build();
         }else{
            Freelancer responseFreelancer = freelancerRepository.save(freelancer);
            logger.info(responseFreelancer.getId() + " is created");
            return Response
                .status(Status.CREATED)
                .encoding(MediaType.APPLICATION_JSON)
                .entity(responseFreelancer)
                .header("Location", "/freelancers/" + responseFreelancer.getId())
                .build();
        }
            }

    @POST
    @Path("/")
    public Response createFreelancer(
        Freelancer freelancer
    ) {
        logger.info("createFreelancer: " + freelancer.getId());
        return Response.status(Status.CREATED)
            .encoding(MediaType.APPLICATION_JSON)
            .entity(freelancerRepository.save(freelancer))
            .header("Location", "/freelancers/" + freelancer.getId())
            .build();
    }


}