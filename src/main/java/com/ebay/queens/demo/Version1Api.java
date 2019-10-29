package com.ebay.queens.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.ebay.queens.demo.User;

@Component
@Path("/v1")
public class Version1Api {


	// Calling this API simply returns the plaintext response "Hello World"
    @GET
    @Path("/{hello}")
    public String getMessage() {
        return "Hello World ";
    }
  
    /*
    @PostMapping(consumes = {MediaType.APPLICATION_JSON }, produces = {MediaType.APPLICATION_JSON})
    public Response newUser(@Valid @RequestBody User newUser) {
        return Response.status(Status.CREATED).entity(newUser).build();
    }
    */ 
    
    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String returnMessage(@Valid @RequestBody User newUser) {
    	User returnUser = newUser; 
    	returnUser.setFirstName(newUser.getFirstName());
    	returnUser.setLastName(newUser.getLastName());
        return ("Hello " + returnUser.getFirstName() + " " + returnUser.getLastName());
    }
}