package com.example.freelancer.jpa;
import java.util.List;
import java.util.Optional;

import com.example.freelancer.model.Freelancer;

import org.springframework.data.repository.CrudRepository;
public interface FreelancerRepository extends CrudRepository<Freelancer,Long>{

    //Optional<Freelancer> findById(Long freelancerId);

}