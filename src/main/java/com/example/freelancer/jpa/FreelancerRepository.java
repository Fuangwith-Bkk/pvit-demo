package com.example.freelancer.jpa;

import java.util.Optional;

import com.example.freelancer.model.Freelancer;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.CrudRepository;
public interface FreelancerRepository extends CrudRepository<Freelancer,Long>{
//public class FreelancerRepository extends Repository<Freelancer,Long>{

    Optional<Freelancer> findById(Long freelancerId);
    boolean existsById(Long freelancerId);

}