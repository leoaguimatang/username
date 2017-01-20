package com.leo.service;

import com.leo.BaseIT;
import com.leo.exception.UsernameException;
import com.leo.model.Result;
import com.leo.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * Integration tests for {@link UserService}.
 */
public class UserServiceIT extends BaseIT {
    @Autowired
    private UserService service;

    @Test
    public void testGetWord() {
        User user = service.searchUsername("matthew").get(0);

        assertTrue(user.getId() == 7);
    }

    @Test
    public void testCheckUsername() {
        assertFalse(service.nameTaken("marlon"));
    }

    @Test
    public void testPostExistingUser() throws Exception {
        String username = "matthew";
        Result result = service.checkUsername(username);

        assertFalse(result.getValid());
        assertTrue(result.getSuggestions().size() == 14);
    }

    @Test
    public void testPostBadUsername() throws Exception {
        String username = "cannabisgalore";
        Result result = service.checkUsername(username);

        assertFalse(result.getValid());
        assertNotNull(result.getSuggestions());
    }

    @Test(expected = UsernameException.class)
    public void testPostVeryBadUsername() throws Exception {
        service.checkUsername("cannabis");
    }

    @Test
    public void testPostAvailableUser() throws Exception {
        String username = "marlon";
        Result result = service.checkUsername(username);

        assertTrue(result.getValid());
        assertTrue(CollectionUtils.isEmpty(result.getSuggestions()));
    }
}
