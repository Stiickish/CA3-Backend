package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JourneyDto;
import dtos.ProfileDto;
import entities.Journey;
import facades.CalculationFacade;
import facades.JourneyFacade;
import facades.ProfileFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("journey")
public class JourneyRescource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final JourneyFacade FACADE =  JourneyFacade.getInstance(EMF);
    private static final CalculationFacade CALCULATION_FACADE =  CalculationFacade.getInstance(EMF);

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJourney(String content) {
        System.out.println(content);
        JourneyDto journeyDtoFromJSON = GSON.fromJson(content, JourneyDto.class);
        JourneyDto journeyDto = FACADE.createJourney(journeyDtoFromJSON);
        //System.out.println(journeyDtoFromJSON);

        return Response.ok().entity(GSON.toJson(journeyDto)).build();
    }

    @POST
    @Path("anonymous")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAnonymousJourney(String content) throws Exception {
        JourneyDto journeyDtoFromJSON = GSON.fromJson(content, JourneyDto.class);
        JourneyDto journeyDto = CALCULATION_FACADE.calculateJourney(journeyDtoFromJSON);

        return Response.ok().entity(GSON.toJson(journeyDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllProfiles() {
        String response = "ALL THE JOURNEYS HERE IF NEEDED";

        return Response.ok().entity(GSON.toJson(response)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJourney(@PathParam("id") int id){
        return Response.ok().entity(GSON.toJson(FACADE.deleteJourney(id))).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJourneyById(@PathParam("id") int id){
        JourneyDto facadeJourney = FACADE.getJourneyById(id);
        return Response.ok().entity(GSON.toJson(facadeJourney)).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateJourney(@PathParam("id") int id, String content) throws Exception {
        JourneyDto journeyDtoFromJSON = GSON.fromJson(content, JourneyDto.class);
        journeyDtoFromJSON.setId(id);
        JourneyDto journeyDto = FACADE.updateJourney(journeyDtoFromJSON);

        return Response.ok().entity(GSON.toJson(journeyDto)).build();
    }
}
