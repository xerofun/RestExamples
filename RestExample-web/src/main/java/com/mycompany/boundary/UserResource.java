package com.mycompany.boundary;

import com.mycompany.ejb.UserAlreadyExistsException;
import com.mycompany.ejb.UserNotFoundException;
import com.mycompany.ejb.UserService;
import com.mycompany.entities.User;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
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


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers()
    {
        return Response.ok(userService.getAllUsers()).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") long id)
    {
        Response response;
        
        try
        {
            response = Response.ok(userService.getUserById(id)).build();
        }
        catch (UserNotFoundException ex)
        {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return response;
    }
    
    

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user)
    {           
        Response response;
        
        try
        {
            userService.addUser(user);
            response = Response.status(Response.Status.CREATED).build();
        }
        catch (UserAlreadyExistsException ex)
        {   // TODO: Log Exception Details
            response = Response.status(Response.Status.CONFLICT).build();
        }

        return response;
    }
}
