package com.basteez.rest;

import com.basteez.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserServiceRs {

    @POST
    @Transactional
    public Response saveUser(User u){
        //this method is oversimplified, add checks in real-world scenario
        try{
            u.persist();
            return Response.ok().entity(u).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response editUser(@PathParam("id") String id, User u){
        //this method is oversimplified, add checks in real-world scenario
        try{
            User.getEntityManager().merge(u);
            return Response.ok().entity(u).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    public Response getUserList(){
        //this method is oversimplified, add checks in real-world scenario
        try{
            List<User> users = User.find("select u from User u").list();
            return Response.ok().entity(users).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getSingleUser(@PathParam("id") String id){
        try{
            User u = User.find("select u from User u WHERE uuid = :uuid", id).singleResult();
            return Response.ok().entity(u).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
