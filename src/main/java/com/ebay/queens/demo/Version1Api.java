package com.ebay.queens.demo;

import org.springframework.stereotype.Component;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Component
@Path("/v1")
public class Version1Api {


	// Calling this API simply returns the plaintext response "Hello World"
    @GET
    @Path("/{hello}")
    public String getMessage() {
        return "Hello World ";
    }
    
    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnMessage(String message) {
    	String newMessage = message;
    	if(newMessage.isEmpty()) {
    		newMessage = "The first name is required";
    	}
        return Response.status(Status.CREATED).entity(newMessage).build();
    }
}