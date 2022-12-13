package facades;

import com.google.gson.Gson;
import dtos.JourneyDto;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class CalculationFacade {

    private static EntityManagerFactory emf;

    private static CalculationFacade instance;
    JourneyFacade journeyFacade = JourneyFacade.getInstance(emf);

/*
    Gson gson = new Gson();*/

    public CalculationFacade() {

    }

    public static CalculationFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CalculationFacade();
        }
        return instance;
    }

    /*public String getAPIData() {
        String carTravelURL = HttpUtils.fetchAPIData("https://app.trycarbonapi.com/api/carTravel", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjUzMGM4ZmIyMTdlYmJiYjg3ZjgwMDdjNDZjYTc5ODMwZjQxNzgzZDVhZTExNTUwMTA4ODdjMzY1NGRlMWNiNDI4YTc2ZGNmMjM3YWFlMGUiLCJpYXQiOjE2NjkzNzA5OTYsIm5iZiI6MTY2OTM3MDk5NiwiZXhwIjoxNzAwOTA2OTk2LCJzdWIiOiIyMzI0Iiwic2NvcGVzIjpbXX0.Ot63eEC6iCdCaea2TKX7DlMgvCpKGM8CfBuMSGivsTOUVerSUyQGUR-SA5e2-5ffN0ATmMavvFtK0f6SgCfETg");
        String publicTransitURL = HttpUtils.fetchAPIData("https://app.trycarbonapi.com/api/publicTransit", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjUzMGM4ZmIyMTdlYmJiYjg3ZjgwMDdjNDZjYTc5ODMwZjQxNzgzZDVhZTExNTUwMTA4ODdjMzY1NGRlMWNiNDI4YTc2ZGNmMjM3YWFlMGUiLCJpYXQiOjE2NjkzNzA5OTYsIm5iZiI6MTY2OTM3MDk5NiwiZXhwIjoxNzAwOTA2OTk2LCJzdWIiOiIyMzI0Iiwic2NvcGVzIjpbXX0.Ot63eEC6iCdCaea2TKX7DlMgvCpKGM8CfBuMSGivsTOUVerSUyQGUR-SA5e2-5ffN0ATmMavvFtK0f6SgCfETg");


        return null;
    }
*/
    public JourneyDto calculateJourney(JourneyDto journeyDto) throws IOException {

        Float newTotalEmission = 0f;
        Float newTotalDistance = 0f;



        if(journeyDto.getTrips() == null)
        {
            return journeyDto;
        }

        for(JourneyDto.TripDto tripDto : journeyDto.getTrips()){

            HttpUtils.getEmission(tripDto);
            journeyDto.setTotalEmission(journeyDto.getTotalEmission() + tripDto.getEmission());
            journeyDto.setTotalDistance(journeyDto.getTotalDistance() + tripDto.getDistance());

        }

        journeyDto.setTotalEmission(newTotalEmission);
        journeyDto.setTotalDistance(newTotalDistance);

        return journeyDto;

    }
}
