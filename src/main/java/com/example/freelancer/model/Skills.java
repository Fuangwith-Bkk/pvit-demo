package com.example.freelancer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "freelancer_skills")
@JsonPropertyOrder({ "skill", "detail" })
public class Skills implements Serializable{

    private static final long serialVersionUID = -182421598881384687L;

    @Id
    @Column(name="id")
    @JsonIgnore
    private Long id;

    @Column(name="skill")
    private String skill;

    @Column(name="detail")
    private String detail;

    // @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "freelancer_id",insertable=false, updatable=false)
    @JsonIgnore
    private Freelancer freelancer;

    public String getDetail(){
        return detail;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }
    public Freelancer getFreelancer(){
        return freelancer;
    }
    public void setFreelancer(Freelancer freelancer){
        this.freelancer = freelancer;
    }

    public Long getId() {
        return id;
    }
    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    
}