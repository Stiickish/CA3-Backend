package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.CalculationFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("calculate")
public class CalculationResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final CalculationFacade FACADE = CalculationFacade.getInstance(EMF);

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    CalculationFacade calculationFacade = new CalculationFacade();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(){
        return calculationFacade.getAPIData();
    }
}

