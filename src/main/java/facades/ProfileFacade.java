package facades;


import dtos.ProfileDto;
import entities.Journey;
import entities.Profile;
import entities.User;
import security.errorhandling.AuthenticationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProfileFacade
{
    private static EntityManagerFactory emf;
    private static ProfileFacade instance;

    UserFacade userFacade = UserFacade.getUserFacade(emf);

    public ProfileFacade() {
    }

    public static ProfileFacade getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new ProfileFacade();
        }
        return instance;
    }

    public ProfileDto getVeryfiedProfile(String username, String password) throws AuthenticationException{
        EntityManager em = emf.createEntityManager();
        Profile profile;
        User user;
        try{
            user = userFacade.getVeryfiedUser(username, password);
            TypedQuery<Profile> query = em.createQuery("SELECT p FROM Profile p WHERE p.user.id = :userid", Profile.class);
            query.setParameter("userid", user.getId());
            profile = query.getSingleResult();
        } finally {
            em.close();
        }
        return new ProfileDto(profile);
    }
    public ProfileDto createProfile(ProfileDto profileDto)
    {

        EntityManager em = emf.createEntityManager();
        User user = new User(profileDto.getUser());
        Profile profile = new Profile(profileDto, user);

        try {
            em.getTransaction().begin();
            em.persist(profile);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return new ProfileDto(profile);
    }

    public Boolean deleteProfile(int id){
        EntityManager em = emf.createEntityManager();
        Profile profile = em.find(Profile.class, id);
        User u;

        try{
            em.getTransaction().begin();
            /*for(Journey journey : profile.getJourneys())
            {
                for(Trip trip : journey.getTrips())
                {
                    em.remove(trip);
                }
            }*/
            em.remove(profile);
            em.getTransaction().commit();
            u = em.find(User.class, profile.getUser().getId());
        } finally {
            em.close();
        }

        if(u == null)
        {
            return true;
        }
        return false;
    }

    public ProfileDto getProfileById(int id)
    {
        EntityManager em = emf.createEntityManager();
        Profile p = em.find(Profile.class, id);

        return new ProfileDto(p);
    }

    public ProfileDto updateProfile( ProfileDto profileDto){
        EntityManager em = emf.createEntityManager();
        Profile profile = new Profile(profileDto);

        try{
            em.getTransaction().begin();
            em.merge(profile);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ProfileDto(profile);
    }

    public List<ProfileDto.JourneyDto> getAllJourneys(int id){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Journey> query = em.createQuery("SELECT p.journeys FROM Profile p WHERE p.id = :id", Journey.class);
        query.setParameter("id", id);
        List<Journey> journey = query.getResultList();
        return ProfileDto.JourneyDto.getDtos(journey);
    }

}
