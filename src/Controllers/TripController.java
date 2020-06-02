package Controllers;

import DB.DbContext;
import agenda.Birthday;
import agenda.Trip;

import java.sql.SQLException;
import java.util.ArrayList;

public class TripController {

    private DbContext context = DbContext.getInstance();

    public TripController() {
    }

    public ArrayList<Trip> getTrips() {
        ArrayList<Trip> trips = context.getTrips();
        return trips;
    }

    public void addTrip(Trip event) {
        context.addTrip(event);
    }

    public void deleteTrip(Trip element) {
        context.deleteTrip(element);
    }

    public void editTrip(Trip event){
        context.editTrip(event);
    }
}
