package entities;

import dtos.JourneyDto;
import dtos.ProfileDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="Trip.deleteAllRows",query = "DELETE from Trip")
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "distance", nullable = false)
    private Float distance;

    @NotNull
    @Column(name = "emission", nullable = false)
    private Float emission;

    @NotNull
    @Column(name = "cost", nullable = false)
    private Float cost;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "journey_id", nullable = false)
    private Journey journey;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fuel_id", nullable = false)
    private Fuel fuel;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "transportation_id", nullable = false)
    private Transportation transportation;

    public Trip() {
    }

    public Trip(ProfileDto.JourneyDto.TripDto tripDto) {
        this.id = tripDto.getId();
        this.distance = tripDto.getDistance();
        this.emission = tripDto.getEmission();
        this.cost = tripDto.getCost();
        this.fuel = new Fuel(tripDto.getFuel());
        this.transportation = new Transportation(tripDto.getTransportation());
    }

    public Trip(JourneyDto.TripDto tripDto){
        if(tripDto.getId() != null){
        this.id = tripDto.getId();}
        this.distance = tripDto.getDistance();
        this.emission = tripDto.getEmission();
        this.cost = tripDto.getCost();
        this.fuel = new Fuel(tripDto.getFuel());
        this.transportation = new Transportation(tripDto.getTransportation());
    }

    public Trip(Float distance, Float emission, Float cost, Journey journey, Fuel fuel, Transportation transportation) {
        this.distance = distance;
        this.emission = emission;
        this.cost = cost;
        this.journey = journey;
        this.fuel = fuel;
        this.transportation = transportation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getEmission() {
        return emission;
    }

    public void setEmission(Float emission) {
        this.emission = emission;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

}