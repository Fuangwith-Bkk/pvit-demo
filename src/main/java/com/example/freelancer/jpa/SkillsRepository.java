package com.example.freelancer.jpa;

import java.util.Optional;

import com.example.freelancer.model.Skills;

import org.springframework.data.repository.CrudRepository;

public interface SkillsRepository extends CrudRepository<Skills,Long>{
    Optional<Skills> findById(Long skillId);
}