package com.example.freelancer.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// @ConfigurationProperties(prefix="app")
@Component
@JsonPropertyOrder({ "version", "success", "freelancers", "message" })
public class FreelancerResponse {
    @JsonProperty("version")
    private String version;

    @JsonProperty("success")
    private boolean success;
    
    @JsonProperty("freelancers")
    private List<Freelancer> freelancers = new ArrayList<>();
    
    @JsonProperty("message")
    private String message;



    public FreelancerResponse(){
        //this.version="1.0.0";
        this.success = true;
        this.message = "";
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public List<Freelancer> getFreelancers() {
        return freelancers;
    }

  
    public void setFreelancers(List<Freelancer> freelancers) {
        this.freelancers = freelancers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

  

    
}