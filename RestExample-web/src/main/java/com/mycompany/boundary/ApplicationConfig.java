package com.mycompany.boundary;

import com.mycompany.ejb.UserAlreadyExistsExceptionMapper;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 *
 * @author Brian
 */
@ApplicationPath("webresources") 
public class ApplicationConfig extends ResourceConfig 
{
    public ApplicationConfig()
    {
        packages("com.mycompany.boundary");
        addProperties(validationProperties());
        
        register(UserAlreadyExistsExceptionMapper.class);
    }
    
    private Map<String, Object> validationProperties()
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return properties;
    }
}
