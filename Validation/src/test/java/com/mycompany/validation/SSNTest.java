package com.mycompany.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static junit.framework.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Brian
 */
public class SSNTest
{
    private static Validator validator;
    
    
    public SSNTest()
    {
    }
    
    @BeforeClass
    public static void setup()
    {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testSSN_invalidLength() throws Exception
    {
        User user = new User("1");
        
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);
        
        assertEquals(1, constraintViolations.size());
        assertEquals("Invalid SSN", constraintViolations.iterator().next().getMessage());
    }
    
    @Test
    public void testSSN_invalidCharacters() throws Exception
    {
        User user = new User("A23-45-6789");
        
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);
        
        assertEquals(1, constraintViolations.size());
        assertEquals("Invalid SSN", constraintViolations.iterator().next().getMessage());
    }
 
    @Test
    public void testSSN_valid() throws Exception
    {
        User user = new User("123-45-6789");
        
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);
        
        assertEquals(0, constraintViolations.size());
    }
    
    private static class User
    {
        @SSN
        String ssn;
        
        public User(String ssn)
        {
            this.ssn = ssn;
        }
    }
}
