package com.gdemecki.crudtask.rest.endpoints;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gdemecki.crudtask.Validate;
import com.gdemecki.crudtask.domain.User;
import com.gdemecki.crudtask.service.UserService;

@Path("/users")
@Produces("application/json")
@Consumes("application/json")
@Component
public class UserEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(UserEndpoint.class);

    private final UserService userService;

    @Inject
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response create(@Valid User user) {
        Validate.notNull(user, "Empty request is not allowed");

        user.setId(null);
        user = userService.createUser(user);

        logger.info("Created a new user: {}", user);

        URI createdUserUri = UriBuilder.fromResource(UserEndpoint.class)
                .path(String.valueOf(user.getId()))
                .build();
        return Response.created(createdUserUri).entity(user).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {
        User user = userService.findUserById(id);

        return Response.ok(user).build();
    }

    @GET
    public Response getUsers() {
        List<User> allUsers = userService.findUsers();

        return Response.ok(allUsers).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long userId, @Valid User user) {
        Validate.notNull(user, "Empty request is not allowed");

        user.setId(userId);
        user = userService.updateUser(user);

        logger.info("Updated user: {}", user);
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteUser(@PathParam("id") Long userId) {
        User deletedUser = userService.deleteUser(userId);

        logger.info("Deleted user: {}", deletedUser);
        return Response.ok().build();
    }
}
