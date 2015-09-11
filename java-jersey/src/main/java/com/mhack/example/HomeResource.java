package com.mhack.example;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

@Singleton
@Path("/")
public class HomeResource {
  
  private String instanceName;
  private static int instanceNum = 0;
  
  public HomeResource() {
    instanceName = "HomeResource-" + instanceNum++;
  }
  
  public HomeResource(String name) {
    
  }

  @GET
  public Response index() {
    return createResponse(Status.NO_CONTENT).build();
  }

  private ResponseBuilder createResponse(Status status) {
    ResponseBuilder rb = Response.status(status);
    rb.header("Resource-Instance-ID", instanceName);
    rb.link(UriBuilder.fromPath("/").build(), "self");
    return rb;
  }
}
