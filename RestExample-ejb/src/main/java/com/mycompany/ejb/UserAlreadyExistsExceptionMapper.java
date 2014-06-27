package com.mycompany.ejb;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Brian
 */
@Provider
public class UserAlreadyExistsExceptionMapper implements ExceptionMapper<UserAlreadyExistsException>
{
    @Override
    public Response toResponse(UserAlreadyExistsException exception)
    {
        return Response.status(Response.Status.CONFLICT).build();
    }
}
