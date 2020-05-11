package com.example.freelancer.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Entity
@Table(name = "freelancer")
@JsonPropertyOrder({ "freelancerId", "firstName", "lastName", "email" })
public class Freelancer implements Serializable {
	private static final long serialVersionUID = -635025978137518898L;
    
    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;
    
    @Column(name="firstname")
    @JsonProperty("firstName")
    private String firstName;
    
    @Column(name="lastname")
    @JsonProperty("lastName")
    private String lastName;
    
    @Column(name="email")
    @JsonProperty("email")
    private String email;
    
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "freelancer",
        cascade = CascadeType.ALL
    )
    private Set<Skills> skills; 
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    } 
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    
    //@JsonbTransient
    public Set<Skills> getSkills(){
        return skills;
    }

    public void setSkills(Set<Skills> skills){
        this.skills =skills;
    }
}