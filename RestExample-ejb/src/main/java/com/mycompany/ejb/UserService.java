package com.mycompany.ejb;

import com.mycompany.entities.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author Brian
 */
@Stateless
public class UserService 
{
    private final Map<Long, User> users = new HashMap<>();
    
    @PostConstruct
    private void initialize()
    {        
        User sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setFirstName("Rick");
        sampleUser.setLastName("Roll");
        sampleUser.setAccountId("ABCD-1234-EFGH-5678");
        sampleUser.setSsn("123-45-6789");
        
        users.put(sampleUser.getId(), sampleUser);
        
        User anotherUser = new User();
        anotherUser.setId(2L);
        anotherUser.setFirstName("Sterling");
        anotherUser.setLastName("Archer");
        anotherUser.setAccountId("EFGH-5678-ABCD-1234");
        anotherUser.setSsn("987-65-4321");
        
        users.put(anotherUser.getId(), anotherUser);
    }
    
    /**
     * Return all {@link User} instances
     * @return Collection of all the users
     */
    public Collection<User> getAllUsers()
    {   // TODO: Persistence Layer        
        return users.values();
    }
    
    /**
     * Return the {@link User} instance identified by id
     * @param id the user id
     * @return the {@link User}
     * @throws UserNotFoundException if no user exists with id
     */
    public User getUserById(long id) throws UserNotFoundException
    {        
        if (users.containsKey(id))
        {
            return users.get(id);
        }

        throw new UserNotFoundException();
    }
    
    
    /**
     * Persist a @{link User}
     * @param user the @{link User}
     * @throws UserAlreadyExistsException if the user id is not unique
     */
    public void addUser(User user) throws UserAlreadyExistsException
    {
        if (users.containsKey(user.getId()))
        {
            throw new UserAlreadyExistsException();
        }
        
        users.put(user.getId(), user);
    }
}
