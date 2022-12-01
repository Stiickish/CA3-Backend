package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JourneyDto;
import dtos.ProfileDto;
import entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Link;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JourneyFacadeTest {

    private static EntityManagerFactory emf;
    private static JourneyFacade journeyFacade;


    User u1;
    Profile p1;
    JourneyDto.ProfileDto journeyProfileDto;
    Journey j1, j2;
    JourneyType jt1, jt2;
    JourneyDto.JourneyTypeDto journeyJourneyTypeDto;
    Transportation transportation1;
    Fuel f1;
    Trip t1, t2;
    LinkedHashSet trips;

    public JourneyFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        journeyFacade = JourneyFacade.getInstance(emf);
    }

    @BeforeEach
    public void setup() {
        EntityManager em = emf.createEntityManager();

        u1 = new User("John", "123");
        p1 = new Profile(1,"a@a.dk", "JohnnyBoy", u1);
        f1 = new Fuel("Rugbrødsmotor");
        transportation1 = new Transportation("Løb");
        jt1 = new JourneyType("Recurring");
        j1 = new Journey("Work", LocalDate.of(2022,11,10),200F, 20F, 2F, jt1);
        LinkedHashSet journeys = new LinkedHashSet<Journey>();
        journeys.add(j1);
        t1 = new Trip(22.5F, 2600F, 0F, j1,f1,transportation1);
        LinkedHashSet trips = new LinkedHashSet<JourneyDto.TripDto>();
        trips.add(t1);



        j1.setProfile(p1);
        journeyProfileDto = new JourneyDto.ProfileDto(p1);
        journeyJourneyTypeDto = new JourneyDto.JourneyTypeDto(jt1);
        p1.setJourneys(journeys);

        try {
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(p1);
            em.persist(f1);
            em.persist(jt1);
            em.persist(transportation1);
            em.persist(t1);
            em.persist(j1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    @Test
    void createJourneyTest() {

        JourneyDto newJourney = new JourneyDto("Til træning",LocalDate.of(2022,11,10), 20.5F, 0.2F, 90F, journeyProfileDto,journeyJourneyTypeDto, trips);
        JourneyDto result = journeyFacade.createJourney(newJourney);

        assertNotNull(result.getId());
        assertEquals(newJourney.getName(), result.getName());
    }

    @Test
    void deleteJourneyTest () {
        boolean response = journeyFacade.deleteJourney(j1.getId());
        assertEquals(true, response);
    }

    @Test
    void updateJourneytest() {
        JourneyDto newJourney = new JourneyDto("Til håndbold", LocalDate.of(2022, 9,11), 80.1F, 20F,2F,journeyProfileDto,journeyJourneyTypeDto, trips);
        newJourney.setId(j1.getId());
        journeyFacade.updateJourney(newJourney);
        JourneyDto result = journeyFacade.getJourneyById(j1.getId());

        assertEquals(newJourney.getName(), result.getName());
    }

    @Test
    void getJourneyById () {
        JourneyDto result = journeyFacade.getJourneyById(j1.getId());

        assertEquals(j1.getJourneyType(), result.getJourneyType());
    }

}
