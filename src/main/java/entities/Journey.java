package entities;

import dtos.JourneyDto;
import dtos.ProfileDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "journey")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "total_emission", nullable = false)
    private Float totalEmission;

    @NotNull
    @Column(name = "total_distance", nullable = false)
    private Float totalDistance;

    @NotNull
    @Column(name = "total_cost", nullable = false)
    private Float totalCost;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "journey_type_id", nullable = false)
    private JourneyType journeyType;

    @OneToMany(mappedBy = "journey", orphanRemoval = true, cascade = {CascadeType.ALL})
    private Set<Trip> trips = new LinkedHashSet<>();

    public Journey() {
    }

    public Journey(ProfileDto.JourneyDto journeyDto) {
        this.id = journeyDto.getId();
        this.name = journeyDto.getName();
        this.date = LocalDate.parse(journeyDto.getDate());
        this.totalEmission = journeyDto.getTotalEmission();
        this.totalDistance = journeyDto.getTotalDistance();
        this.totalCost = journeyDto.getTotalCost();
        this.journeyType = new JourneyType(journeyDto.getJourneyType());
        if(journeyDto.getTrips() != null){
            for(ProfileDto.JourneyDto.TripDto tripDto : journeyDto.getTrips()){
                this.trips.add(new Trip(tripDto));
            }
        }
    }
    public Journey(String name,Float totalEmission, Float totalDistance, Float totalCost, JourneyType journeyType) {
        this.name = name;
        this.totalEmission = totalEmission;
        this.totalDistance = totalDistance;
        this.totalCost = totalCost;
        this.profile = profile;
        this.journeyType = journeyType;
    }

    public Journey(String name, LocalDate date, Float totalEmission, Float totalDistance, Float totalCost, JourneyType journeyType) {
        this.name = name;
        this.date = date;
        this.totalEmission = totalEmission;
        this.totalDistance = totalDistance;
        this.totalCost = totalCost;
        this.profile = profile;
        this.journeyType = journeyType;
    }

    public Journey(int id, String name, LocalDate date, Float totalEmission, Float totalDistance, Float totalCost, JourneyType journeyType) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.totalEmission = totalEmission;
        this.totalDistance = totalDistance;
        this.totalCost = totalCost;
        this.profile = profile;
        this.journeyType = journeyType;
    }
    public Journey(JourneyDto journeyDto) {
        this.name = journeyDto.getName();
        this.date = LocalDate.parse(journeyDto.getDate());
        this.totalEmission = journeyDto.getTotalEmission();
        this.totalDistance = journeyDto.getTotalDistance();
        this.totalCost = journeyDto.getTotalCost();
        this.journeyType = new JourneyType(journeyDto.getJourneyType());
    }

    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", totalEmission=" + totalEmission +
                ", totalDistance=" + totalDistance +
                ", totalCost=" + totalCost +
                ", profile=" + profile +
                ", journeyType=" + journeyType +
                ", trips=" + trips +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getTotalEmission() {
        return totalEmission;
    }

    public void setTotalEmission(Float totalEmission) {
        this.totalEmission = totalEmission;
    }

    public Float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public JourneyType getJourneyType() {
        return journeyType;
    }

    public void setJourneyType(JourneyType journeyType) {
        this.journeyType = journeyType;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
        trip.setJourney(this);
    }





}