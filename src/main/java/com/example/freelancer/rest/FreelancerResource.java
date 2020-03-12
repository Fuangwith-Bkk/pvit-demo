package com.example.freelancer.rest;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.ws.rs.Produces;

import com.example.freelancer.jpa.FreelancerRepository;
import com.example.freelancer.model.Freelancer;

import org.jboss.logging.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/freelancers")
public class FreelancerResource {

    private final FreelancerRepository freelancerRepository;

    private static final Logger logger = Logger.getLogger(FreelancerResource.class);

    @ConfigProperty(name = "app.version", defaultValue = "1.0.0")
    String version;
    
    public FreelancerResource(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    @GET
    @Produces("application/json")
    public Iterable<Freelancer> findAll() {
        logger.info("finaAll");
        return freelancerRepository.findAll();
    }
    @GET
    @Produces("application/json")
    @Path("/{freelancerId}")
    public Optional<Freelancer> findById(@PathParam("freelancerId") long freelancerId){
        logger.info("findById: "+freelancerId);
        return freelancerRepository.findById(freelancerId);
    }
    
}