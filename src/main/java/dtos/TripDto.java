package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Trip} entity
 */
public class TripDto implements Serializable {
    private  Integer id;
    @NotNull
    private  Float distance;
    @NotNull
    private  Float emission;
    @NotNull
    private  Float cost;
    @NotNull
    private  JourneyDto1 journey;
    @NotNull
    private  FuelDto1 fuel;
    @NotNull
    private  TransportationDto1 transportation;

    public TripDto(Integer id, Float distance, Float emission, Float cost, JourneyDto1 journey, FuelDto1 fuel, TransportationDto1 transportation) {
        this.id = id;
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

    public Float getDistance() {
        return distance;
    }

    public Float getEmission() {
        return emission;
    }

    public Float getCost() {
        return cost;
    }

    public JourneyDto1 getJourney() {
        return journey;
    }

    public FuelDto1 getFuel() {
        return fuel;
    }

    public TransportationDto1 getTransportation() {
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
                Objects.equals(this.journey, entity.journey) &&
                Objects.equals(this.fuel, entity.fuel) &&
                Objects.equals(this.transportation, entity.transportation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, emission, cost, journey, fuel, transportation);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "distance = " + distance + ", " +
                "emission = " + emission + ", " +
                "cost = " + cost + ", " +
                "journey = " + journey + ", " +
                "fuel = " + fuel + ", " +
                "transportation = " + transportation + ")";
    }

    /**
     * A DTO for the {@link entities.Journey} entity
     */
    public static class JourneyDto1 implements Serializable {
        private  Integer id;
        @Size(max = 45)
        @NotNull
        private  String name;
        @NotNull
        private  LocalDate date;
        @NotNull
        private  Float totalEmission;
        @NotNull
        private  Float totalDistance;
        @NotNull
        private  Float totalCost;

        public JourneyDto1(Integer id, String name, LocalDate date, Float totalEmission, Float totalDistance, Float totalCost) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.totalEmission = totalEmission;
            this.totalDistance = totalDistance;
            this.totalCost = totalCost;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public LocalDate getDate() {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JourneyDto1 entity = (JourneyDto1) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.date, entity.date) &&
                    Objects.equals(this.totalEmission, entity.totalEmission) &&
                    Objects.equals(this.totalDistance, entity.totalDistance) &&
                    Objects.equals(this.totalCost, entity.totalCost);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, date, totalEmission, totalDistance, totalCost);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "date = " + date + ", " +
                    "totalEmission = " + totalEmission + ", " +
                    "totalDistance = " + totalDistance + ", " +
                    "totalCost = " + totalCost + ")";
        }
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
    public static class TransportationDto1 implements Serializable {
        private  Integer id;
        @Size(max = 45)
        @NotNull
        private  String name;

        public TransportationDto1(Integer id, String name) {
            this.id = id;
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
            TransportationDto1 entity = (TransportationDto1) o;
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