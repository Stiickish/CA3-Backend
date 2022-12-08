package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProfileDto;
import facades.ProfileFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("profile")
public class ProfileResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ProfileFacade FACADE =  ProfileFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProfile(String content) throws Exception {
        ProfileDto profileDtoFromJSON = GSON.fromJson(content, ProfileDto.class);
        System.out.println(profileDtoFromJSON);
         ProfileDto profileDto = FACADE.createProfile(profileDtoFromJSON);

         return Response.ok().entity(GSON.toJson(profileDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllProfiles() {
        String response = "Oi";

        return Response.ok().entity(GSON.toJson(response)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfiles(@PathParam("id") int id){
        return Response.ok().entity(GSON.toJson(FACADE.deleteProfile(id))).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileById(@PathParam("id") int id){
        return Response.ok().entity(GSON.toJson(FACADE.getProfileById(id))).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfile(@PathParam("id") int id, String content) throws Exception {
        ProfileDto profileDtoFromJSON = GSON.fromJson(content, ProfileDto.class);
        profileDtoFromJSON.setId(id);
        ProfileDto profileDto = FACADE.updateProfile(profileDtoFromJSON);

        return Response.ok().entity(GSON.toJson(profileDto)).build();
    }

    @GET
    @Path("journey/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJourneys(@PathParam("id") int id){
        return Response.ok().entity(GSON.toJson(FACADE.getAllJourneys(id))).build();
    }

}
