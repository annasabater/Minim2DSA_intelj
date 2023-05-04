package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.EmailAlreadyInUseException;
import edu.upc.dsa.exceptions.IncorrectPasswordException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {

    private GameManager manager;

    public GameService() throws EmailAlreadyInUseException {
        this.manager = GameManagerImpl.getInstance();
        if (manager.getUsers().size()==0){
            manager.register(new User("Toni","Boté","toni@upc.edu","12345"));
            manager.register(new User("Jordi","Pié","jordi@upc.edu","123"));
            manager.register(new User("Anna","Sabater","anna@upc.edu","1234"));
            manager.register(new User("Sara","Aguiló","sara@upc.edu","123456"));
        }
    }

    @POST
    @ApiOperation(value = "User registration", notes = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully registered", response = User.class),
            @ApiResponse(code = 404, message = "This email address is already in use"),
    })
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Register(User user) throws EmailAlreadyInUseException {
        try{

            this.manager.register(new User(user.getName(), user.getSurname(), user.getEmail(), user.getPassword()));
            return Response.status(201).entity(user).build();

        } catch (EmailAlreadyInUseException e){

            return Response.status(404).entity(user).build();

        }

    }
    @POST
    @ApiOperation(value = "User login", notes = "log in using credentials")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 404, message = "Incorrect credentials")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(Credentials credentials) throws IncorrectPasswordException, UserNotRegisteredException {
        User user = this.manager.Login(credentials.getEmail(), credentials.getPassword());
        if (user!= null) {
            return Response.status(201).entity(user).build();
        }
        else {
            return Response.status(404).entity(user).build();
        }
    }

}