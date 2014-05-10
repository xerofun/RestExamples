package com.mycompany.boundary;

import com.mycompany.ejb.UserService;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Brian
 */
@Path("User")
public class UserResource
{
    @Inject
    UserService userService;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource()
    {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.UserResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers()
    {
        return Response.ok(userService.getAllUsers()).build();
    }

    /**
     * PUT method for updating or creating an instance of UserResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
    }
}
