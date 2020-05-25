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

    public void addTrip(Trip event) throws SQLException {
        context.addTrip(event);
    }

    public void deleteTrip(Trip element) throws SQLException {
        context.deleteTrip(element);
    }

    public void editTrip(){

    }
}
