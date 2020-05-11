package com.example.freelancer.model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "freelancer_skills")
@JsonPropertyOrder({ "skill", "detail" })
public class Skills implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    // @JsonbTransient
    private Long id;

    @Column(name="skill")
    private String skill;

    @Column(name="detail")
    private String detail;
    

    @ManyToOne(fetch=FetchType.LAZY, optional = true)
    @JoinColumn(name = "freelancer_id",insertable=true, updatable=true)
    //@JoinColumn(name = "freelancer_id",nullable = false)
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

  
    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
      public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    
}