package com.leo.controller;

import com.leo.exception.ErrorKey;
import com.leo.exception.UsernameException;
import com.leo.model.Result;
import com.leo.model.User;
import com.leo.resource.UserResource;
import com.leo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.leo.controller.UserController.USER_BASE;

/**
 * Manages the mapping of {@link User}.
 */
@Controller
@RequestMapping(value = USER_BASE)
public class UserController {
    public static final String USER_BASE = "/users";
    private static final String USER_ID = "/{id}";
    private static final String CHECK_USERNAME = "/checkUsername";

    @Autowired
    private UserService userService;

    /**
     * Search {@link User}s by username
     */
    @GetMapping
    public ResponseEntity<List<User>> getUsers(
        @RequestParam(name = "search", required = false, defaultValue = "") String search) throws Exception {

        List<User> users = userService.searchUsername(search);
        if (users == null) {
            throw new UsernameException(ErrorKey.USERNAME_NOT_FOUND);
        }

        return new ResponseEntity(users, HttpStatus.OK);
    }

    /**
     * Get {@link User} by Id
     */
    @GetMapping(USER_ID)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

        User user = userService.getUserById(id);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    /**
     * Checks username validity and creates a {@link User}
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Result> createUser(@RequestBody @Valid UserResource userResource) throws Exception {

        Result result = userService.createUser(userResource);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * Check username availability
     */
    @GetMapping(CHECK_USERNAME)
    public ResponseEntity<Result> checkUser(@RequestParam(name = "username") String username) throws Exception {
        if (StringUtils.isEmpty(username) || username.length() < 6) {
            throw new UsernameException(ErrorKey.USERNAME_INVALID);
        }

        Result result = userService.checkUsername(username);

        return new ResponseEntity(result, HttpStatus.OK);
    }
}