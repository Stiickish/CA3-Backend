package facades;

import com.mysql.cj.log.Log;
import dtos.JourneyDto;
import dtos.ProfileDto;
import entities.*;
import rest.CalculationResource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class JourneyFacade {

    private static EntityManagerFactory emf;
    private static JourneyFacade instance;
    private CalculationFacade calculationFacade;

    public JourneyFacade() {
    }
    public static JourneyFacade getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new JourneyFacade();
        }
        return instance;
    }

    public JourneyDto createJourney (JourneyDto journeyDto) {
        EntityManager em = emf.createEntityManager();
        calculationFacade = CalculationFacade.getInstance(emf);

        try {
        calculationFacade.calculateJourney(journeyDto);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        Profile profile = em.find(Profile.class, journeyDto.getProfile().getId());
        Journey journey = new Journey(journeyDto);
        for (Trip trip : journey.getTrips()) {
            Transportation transportation = em.find(Transportation.class, trip.getTransportation().getId());
            trip.setTransportation(transportation);
            transportation.getTrips().add(trip);
            trip.setJourney(journey);
        }
        journey.setProfile(profile);
        profile.getJourneys().add(journey);

        try {
            em.getTransaction().begin();
            em.persist(journey);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }

        return new JourneyDto(journey);
    }

    public boolean deleteJourney (int id) {
        EntityManager em = emf.createEntityManager();
        Journey journey = em.find(Journey.class, id);

        try {
            em.getTransaction().begin();
            journey.getTrips().clear();
            em.remove(journey);
            em.getTransaction().commit();
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex);
            return false;
        }
        finally {
            em.close();
        }

        return true;
    }

    public JourneyDto updateJourney (JourneyDto journeyDto) {
        EntityManager em = emf.createEntityManager();
        calculationFacade = CalculationFacade.getInstance(emf);
        try {
            calculationFacade.calculateJourney(journeyDto);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        Profile profile = em.find(Profile.class, journeyDto.getProfile().getId());
        JourneyType type = em.find(JourneyType.class, journeyDto.getJourneyType().getId());
        Journey journey = new Journey(journeyDto);
        journey.setProfile(profile);
        journey.setJourneyType(type);

        for (Trip trip : journey.getTrips()) {
            Transportation transportation = em.find(Transportation.class, trip.getTransportation().getId());
            trip.setTransportation(transportation);
            transportation.getTrips().add(trip);
            trip.setJourney(journey);
        }

        try
        {
            em.getTransaction().begin();
            em.merge(journey);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new JourneyDto(journey);
    }

    public JourneyDto getJourneyById(int id) {
        EntityManager em = emf.createEntityManager();
        Journey j = em.find(Journey.class, id);
        System.out.println(j);
        return new JourneyDto(j);
    }
}
