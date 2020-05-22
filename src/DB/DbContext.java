package DB;

import agenda.Birthday;
import agenda.Trip;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class DbContext {
    private static DbContext instance = null;
    // protocol = jdbc
    // vendor = mysql
    // adresa pt conexiune la baza de date si numele schemei
    private String url = "jdbc:mysql://localhost:3306/laboratorpao";
    private String username = "root";
    private String password = "";
    private static Connection connection;
    private static Statement statement;

    private DbContext() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
    }

    public static DbContext getInstance() throws SQLException {
        if (instance == null){
            instance = new DbContext();
        }
        return instance;
    }

    public ArrayList<Birthday> getBirthdays() throws SQLException {
        ArrayList<Birthday> birthdays = new ArrayList<>();
        String query = "SELECT * FROM birthdays JOIN events_ on birthdays.id = events_.birthdayId";
        ResultSet result = statement.executeQuery(query);

        while(result.next()){
            Birthday event = new Birthday();
            // event class
            event.setName(result.getString("name"));
            event.setDate(LocalDate.parse(result.getString("date")));
            event.setWhere(result.getString("where"));
            event.setTime(result.getString("time"));
            //birthday class
            event.setId(result.getInt("id"));
            event.setGift(result.getString("gift"));
            event.setWhose(result.getString("whose"));

            birthdays.add(event);
        }

        return birthdays;
    }

    public void addBirthday(Birthday event){

    }

    public void deleteBirthday(int Id){

    }

    public ArrayList<Trip> getTrips() throws SQLException {
        ArrayList<Trip> trips = new ArrayList<>();
        String query = "SELECT * FROM trips JOIN events_ on trips.id = events_.tripId";
        ResultSet result = statement.executeQuery(query);

        while(result.next()){
            Trip event = new Trip();
            // event class
            event.setName(result.getString("name"));
            event.setDate(LocalDate.parse(result.getString("date")));
            event.setWhere(result.getString("where"));
            event.setTime(result.getString("time"));
            //birthday class
            event.setId(result.getInt("id"));
            event.setTransportPrice(result.getString("transportPrice"));
            event.setNumberOfDays(result.getString("noOfDays"));
            event.setHotelPrice(result.getString("hotelPrice"));
            event.setExtraBudget(result.getString("extraBudget"));
            ArrayList<String> attractions = new ArrayList<String>(Arrays.asList(result.getString("attractions").split(", ")));
            event.setTouristAttractions(attractions);

            trips.add(event);
        }

        return trips;
    }

    public void addTrip(Trip event){

    }

    public void deleteTrip(int Id){

    }

    public ArrayList<String> getCategories() throws SQLException {
        ArrayList<String> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            categories.add(result.getString("name"));
        }
        return categories;
    }
}
