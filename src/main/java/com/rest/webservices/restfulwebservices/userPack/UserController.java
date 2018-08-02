package com.rest.webservices.restfulwebservices.userPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> getUserByName(@PathVariable int id) {
        User user = userService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);

        Resource<User> resource = new Resource<User>(user);

        ControllerLinkBuilder linkedTo =
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
        resource.add(linkedTo.withRel("all-users"));
        //HATEOAS
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);//new user is created
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable int id) {
        User user = userService.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);
    }
}
