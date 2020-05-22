package Controllers;

import DB.DbContext;
import agenda.Birthday;
import agenda.Trip;

import java.sql.SQLException;
import java.util.ArrayList;

public class TripController {

    private DbContext context = DbContext.getInstance();

    public TripController() throws SQLException {
    }

    public ArrayList<Trip> getTrips() throws SQLException {
        ArrayList<Trip> trips = context.getTrips();
        return trips;
    }

    public void addTrip(){

    }

    public void deleteTrip(){

    }

    public void editTrip(){

    }
}
