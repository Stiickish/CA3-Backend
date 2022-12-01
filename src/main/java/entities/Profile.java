package entities;

import dtos.ProfileDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<Journey> journeys = new LinkedHashSet<>();

    public Profile() {
    }

    public Profile(ProfileDto profileDto, User user) {
        if(profileDto.getId() != null)
        {
            this.id = profileDto.getId();
        }
        this.email = profileDto.getEmail();
        this.name = profileDto.getName();
        this.user = user;
    }

    public Profile(ProfileDto profileDto){
        this.id = profileDto.getId();
        this.email = profileDto.getEmail();
        this.name = profileDto.getName();
        this.user = new User(profileDto.getUser());
        if(profileDto.getJourneys() != null){
            for(ProfileDto.JourneyDto journeyDto : profileDto.getJourneys()){
                this.journeys.add(new Journey(journeyDto));
            }
        }
    }

    public Profile(Integer id, String email, String name, User user) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(Set<Journey> journeys) {
        this.journeys = journeys;
    }

    public  void addJourney(Journey journey) {
        this.journeys.add(journey);
        journey.setProfile(this);
    }
}