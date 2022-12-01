package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JourneyDto;
import entities.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.LinkedHashSet;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JourneyResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();

    User u1, u2;
    Profile p1;
    Journey j1;
    Journey j2;
    JourneyType jt1;
    Trip trip1;
    Transportation transportation1;
    Fuel f1;

    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        u1 = new User("John", "123");
        u2 = new User("Bertha", "prop");
        f1 = new Fuel("Rugbrødsmotor");
        transportation1 = new Transportation("Løb");
        jt1 = new JourneyType("Recurring");
        j1 = new Journey("Work", LocalDate.of(2022,1,1),200F, 20F, 2F, jt1);
        //j1 = new Journey("Work",200F, 20F, 2F, jt1);

        // j2 = new Journey("Home", LocalDate.of(2022,11,10),trip1.getEmission(),trip1.getDistance(),trip1.getCost(), jt1);
        LinkedHashSet journeys = new LinkedHashSet<Journey>();
        journeys.add(j1);
        // journeys.add(j2);
        trip1 = new Trip(22.5F, 2600F, 0F, j1,f1,transportation1);

        p1 = new Profile(1,"a@a.dk", "name",  u1);
        j1.setProfile(p1);
        p1.setJourneys(journeys);

        try{
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(u2);
            em.persist(p1);
            em.persist(f1);
            em.persist(jt1);
            em.persist(transportation1);
            em.persist(trip1);
            em.persist(j1);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }


    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/profile").then().statusCode(200);
    }
    @Test
    public void testLogRequest() {
        System.out.println("Testing logging request details");
        given().log().all()
                .when().get("/profile")
                .then().statusCode(200);
    }
    @Test
    public void testLogResponse() {
        System.out.println("Testing logging response details");
        given()
                .when().get("/profile")
                .then().log().body().statusCode(200);
    }

    @Test
    public void getJourneyById() {
        given()
                .contentType(ContentType.JSON)
                .get("/journey/{id}", j1.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(j1.getId()));
    }

    @Test
    public void createJourney() {

    }
}
