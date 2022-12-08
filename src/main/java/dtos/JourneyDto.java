package dtos;

import entities.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Journey} entity
 */
public class JourneyDto implements Serializable {
    private  Integer id;
    @Size(max = 45)
    @NotNull
    private  String name;
    @NotNull
    private  String date;
    @NotNull
    private  Float totalEmission;
    @NotNull
    private  Float totalDistance;
    @NotNull
    private  Float totalCost;
    @NotNull
    private  ProfileDto profile;
    @NotNull
    private  JourneyTypeDto journeyType;
    private  Set<TripDto> trips = new LinkedHashSet<>();

    public JourneyDto(Integer id, String name, LocalDate date, Float totalEmission, Float totalDistance, Float totalCost, ProfileDto profile, JourneyTypeDto journeyType, Set<TripDto> trips) {
        this.id = id;
        this.name = name;
        this.date = date.toString();
        this.totalEmission = totalEmission;
        this.totalDistance = totalDistance;
        this.totalCost = totalCost;
        this.profile = profile;
        this.journeyType = journeyType;
        this.trips = trips;
    }

    public JourneyDto(Integer id, String name, String date, Float totalEmission, Float totalDistance, Float totalCost, ProfileDto profile, JourneyTypeDto journeyType, Set<TripDto> trips) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.totalEmission = totalEmission;
        this.totalDistance = totalDistance;
        this.totalCost = totalCost;
        this.profile = profile;
        this.journeyType = journeyType;
        this.trips = trips;
    }

    public JourneyDto(String name, LocalDate date, Float totalEmission, Float totalDistance, Float totalCost, ProfileDto profile, JourneyTypeDto journeyType, Set<TripDto> trips) {
        this.name = name;
        this.date = date.toString();
        this.totalEmission = totalEmission;
        this.totalDistance = totalDistance;
        this.totalCost = totalCost;
        this.profile = profile;
        this.journeyType = journeyType;
        this.trips = trips;
    }

    public JourneyDto(Journey journey) {
        if(journey.getId() != null){
            this.id = journey.getId();
        }
        this.name = journey.getName();
        this.date = journey.getDate().toString();
        this.totalEmission = journey.getTotalEmission();
        this.totalDistance = journey.getTotalDistance();
        if(journey.getTotalCost() != null) {
            this.totalCost = journey.getTotalCost();
        }
        if(journey.getProfile() != null){
        this.profile = new ProfileDto(journey.getProfile());
        }
        if(journey.getJourneyType() != null) {
            this.journeyType = new JourneyTypeDto(journey.getJourneyType());
        }
        if(journey.getTrips() != null){
            for(Trip trip : journey.getTrips()){
                this.trips.add(new TripDto(trip));
            }
        }
    }

    public JourneyDto(String name, LocalDate date, Float totalEmission, Float totalDistance, Float totalCost, ProfileDto profile, JourneyTypeDto journeyType) {
        this.name = name;
        this.date = date.toString();
        this.totalEmission = totalEmission;
        this.totalDistance = totalDistance;
        this.totalCost = totalCost;
        this.profile = profile;
        this.journeyType = journeyType;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Float getTotalEmission() {
        return totalEmission;
    }

    public Float getTotalDistance() {
        return totalDistance;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public ProfileDto getProfile() {
        return profile;
    }

    public JourneyTypeDto getJourneyType() {
        return journeyType;
    }

    public Set<TripDto> getTrips() {
        return trips;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotalEmission(Float totalEmission) {
        this.totalEmission = totalEmission;
    }

    public void setTotalDistance(Float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    public void setJourneyType(JourneyTypeDto journeyType) {
        this.journeyType = journeyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JourneyDto entity = (JourneyDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.totalEmission, entity.totalEmission) &&
                Objects.equals(this.totalDistance, entity.totalDistance) &&
                Objects.equals(this.totalCost, entity.totalCost) &&
                Objects.equals(this.profile, entity.profile) &&
                Objects.equals(this.journeyType, entity.journeyType) &&
                Objects.equals(this.trips, entity.trips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, totalEmission, totalDistance, totalCost, profile, journeyType, trips);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "date = " + date + ", " +
                "totalEmission = " + totalEmission + ", " +
                "totalDistance = " + totalDistance + ", " +
                "totalCost = " + totalCost + ", " +
                "profile = " + profile + ", " +
                "journeyType = " + journeyType + ", " +
                "trips = " + trips + ")";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * A DTO for the {@link entities.Profile} entity
     */
    public static class ProfileDto implements Serializable {
        private  Integer id;
        @Size(max = 45)
        @NotNull
        private  String email;
        @Size(max = 45)
        @NotNull
        private  String name;

        public ProfileDto(Integer id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }

        public ProfileDto(Profile profile) {
            this.id = profile.getId();
            this.email= profile.getEmail();
            this.name = profile.getName();
        }


        public Integer getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProfileDto entity = (ProfileDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.email, entity.email) &&
                    Objects.equals(this.name, entity.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, email, name);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "email = " + email + ", " +
                    "name = " + name + ")";
        }
    }

    /**
     * A DTO for the {@link entities.JourneyType} entity
     */
    public static class JourneyTypeDto implements Serializable {
        private  Integer id;
        @Size(max = 45)
        @NotNull
        private  String name;

        public JourneyTypeDto(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public JourneyTypeDto(JourneyType journeyType) {
            this.id = journeyType.getId();
            this.name = journeyType.getName();
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JourneyTypeDto entity = (JourneyTypeDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ")";
        }
    }

    /**
     * A DTO for the {@link entities.Trip} entity
     */
    public static class TripDto implements Serializable {
        private Integer id;
        @NotNull
        private  Float distance;
        @NotNull
        private  Float emission;
        @NotNull
        private  Float cost;
        @NotNull
        private  FuelDto1 fuel;
        @NotNull
        private  TransportationDto transportation;

        public TripDto(Integer id, Float distance, Float emission, Float cost, FuelDto1 fuel, TransportationDto transportation) {
            this.id = id;
            this.distance = distance;
            this.emission = emission;
            this.cost = cost;
            this.fuel = fuel;
            this.transportation = transportation;
        }

        public TripDto(Float distance, TransportationDto transportationDto){
            this.distance = distance;
            this.transportation = transportationDto;
        }

        public TripDto(Trip trip){
            this.id = trip.getId();
            this.distance = trip.getDistance();
            this.emission = trip.getEmission();
            this.cost = trip.getCost();
            this.fuel = new FuelDto1(trip.getFuel());
            this.transportation = new TransportationDto(trip.getTransportation());
        }

        public Integer getId() {
            return id;
        }

        public Float getDistance() {
            return distance;
        }

        public Float getEmission() {
            return emission;
        }

        public Float getCost() {
            return cost;
        }

        public FuelDto1 getFuel() {
            return fuel;
        }

        public TransportationDto getTransportation() {
            return transportation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TripDto entity = (TripDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.distance, entity.distance) &&
                    Objects.equals(this.emission, entity.emission) &&
                    Objects.equals(this.cost, entity.cost) &&
                    Objects.equals(this.fuel, entity.fuel) &&
                    Objects.equals(this.transportation, entity.transportation);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, distance, emission, cost, fuel, transportation);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "distance = " + distance + ", " +
                    "emission = " + emission + ", " +
                    "cost = " + cost + ", " +
                    "fuel = " + fuel + ", " +
                    "transportation = " + transportation + ")";
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setDistance(Float distance) {
            this.distance = distance;
        }

        public void setEmission(Float emission) {
            this.emission = emission;
        }

        public void setCost(Float cost) {
            this.cost = cost;
        }

        public void setFuel(FuelDto1 fuel) {
            this.fuel = fuel;
        }

        public void setTransportation(TransportationDto transportation) {
            this.transportation = transportation;
        }

        /**
         * A DTO for the {@link entities.Fuel} entity
         */
        public static class FuelDto1 implements Serializable {
            private  Integer id;
            @Size(max = 45)
            @NotNull
            private  String name;

            public FuelDto1(Integer id, String name) {
                this.id = id;
                this.name = name;
            }

            public FuelDto1(Fuel fuel){
                this.id = fuel.getId();
                this.name = fuel.getName();
            }

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                FuelDto1 entity = (FuelDto1) o;
                return Objects.equals(this.id, entity.id) &&
                        Objects.equals(this.name, entity.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id, name);
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "name = " + name + ")";
            }
        }

        /**
         * A DTO for the {@link entities.Transportation} entity
         */
        public static class TransportationDto implements Serializable {
            private  Integer id;
            @Size(max = 45)
            @NotNull
            private  String name;

            public TransportationDto(Integer id, String name) {
                this.id = id;
                this.name = name;
            }

            public TransportationDto(Transportation transportation){
                this.id = transportation.getId();
                this.name = transportation.getName();
            }

            public TransportationDto(String name) {
                this.name = name;
            }

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                TransportationDto entity = (TransportationDto) o;
                return Objects.equals(this.id, entity.id) &&
                        Objects.equals(this.name, entity.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id, name);
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "name = " + name + ")";
            }
        }
    }
}