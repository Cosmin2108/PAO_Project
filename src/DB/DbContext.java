package DB;

import agenda.Birthday;
import agenda.Trip;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class DbContext {
    private static DbContext instance = null;
    // protocol = jdbc
    // vendor = mysql
    // adresa pt conexiune la baza de date si numele schemei
    private String url = "jdbc:mysql://localhost:3306/laboratorpao?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "root";
    private String password = "";
    private static Connection connection;
    private static Statement statement;

    private DbContext() {}

    public static DbContext getInstance() {
        if (instance == null){
            instance = new DbContext();
        }
        return instance;
    }

    public ArrayList<Birthday> getBirthdays(){
        ArrayList<Birthday> birthdays = new ArrayList<>();
        String query = "SELECT * FROM birthdays JOIN events_ on birthdays.id = events_.birthdayId";
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Birthday event = new Birthday();
                // event class
                event.setName(result.getString("name"));
                event.setDate(LocalDate.parse(result.getString("date"),DateTimeFormatter.ofPattern("dd/MM/yyy")));
                event.setWhere(result.getString("location"));
                event.setTime(result.getString("time"));
                event.setEventId(result.getInt("eventId"));
                //birthday class
                event.setId(result.getInt("id"));
                event.setGift(result.getString("gift"));
                event.setWhose(result.getString("whose"));

                birthdays.add(event);
            }

            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e);
        }
        return birthdays;
    }

    public void addBirthday(Birthday event){
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            ArrayList<Birthday> birthdays = new ArrayList<>();
            String query = "INSERT INTO birthdays VALUE(null, '" + event.getWhose() + "', '" + event.getGift() + "')";
            statement.executeUpdate(query);
            query = "INSERT INTO events_ VALUE(null, LAST_INSERT_ID(), null, '" + event.getName() + "', '" + event.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyy")) + "', '"+ event.getWhere() + "', '"+ event.getTime() + "')";
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void editBirthday(Birthday event){
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String query = "UPDATE birthdays SET whose = '" + event.getWhose() + "', gift = '" + event.getGift() + "' WHERE id = " + event.getId();
            statement.executeUpdate(query);
            query = "UPDATE events_ SET name = '" + event.getName() + "', date = '" + event.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyy")) + "', location = '" + event.getWhere() + "', time = '" + event.getTime() + "' WHERE birthdayId = " + event.getId();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void deleteBirthday(Birthday event){
        String query = "DELETE FROM birthdays WHERE id = " + event.getId();
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            statement.executeUpdate(query); // table has delete cascade, so I think the next 2 lines aren't necessary
//          query = "DELETE FROM events WHERE birthdayId = " + event.getId();
//          statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public ArrayList<Trip> getTrips() {
        ArrayList<Trip> trips = new ArrayList<>();
        String query = "SELECT * FROM trips JOIN events_ on trips.id = events_.tripId";
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Trip event = new Trip();
                // event class
                event.setName(result.getString("name"));
                event.setDate(LocalDate.parse(result.getString("date"), DateTimeFormatter.ofPattern("dd/MM/yyy")));
                event.setWhere(result.getString("location"));
                event.setTime(result.getString("time"));
                event.setEventId(result.getInt("eventId"));
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

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return trips;
    }

    public void addTrip(Trip event) {
        ArrayList<Birthday> birthdays = new ArrayList<>();
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            String query = "INSERT INTO trips VALUE(null, " + event.getNumberOfDays() + ", " + event.getTransportPrice() + ", " + event.getHotelPrice() + ", '" + String.join(", ", event.getTouristAttractions()) + "', " + event.getExtraBudget() + ")";
            statement.executeUpdate(query);
            query = "INSERT INTO events_ VALUE(null, null, LAST_INSERT_ID(), '" + event.getName() + "', '" + event.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyy")) + "', '"+ event.getWhere() + "', '"+ event.getTime() + "')";
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void editTrip(Trip event){
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            String query = "UPDATE trips SET noOfDays = " + event.getNumberOfDays() + ", transportPrice = " + event.getTransportPrice() + ", hotelPrice = " + event.getHotelPrice() + ", attractions = '" + String.join(", ", event.getTouristAttractions())+ "', extraBudget = " + event.getExtraBudget() + " WHERE id = " + event.getId();
            statement.executeUpdate(query);
            query = "UPDATE events_ SET name = '" + event.getName() + "', date = '" + event.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyy")) + "', location = '" + event.getWhere() + "', time = '" + event.getTime() + "' WHERE tripId = " + event.getId();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void deleteTrip(Trip event) {
        String query = "DELETE FROM trips WHERE id = " + event.getId(); // cascade delete
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            statement.executeUpdate(query);
//          query = "DELETE FROM events WHERE tripId = " + event.getId();
//          statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                categories.add(result.getString("name"));
            }

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return categories;
    }

    public void addCategories(String category) {
        String query = "INSERT INTO categories VALUE(null, '" + category + "')";
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void editCategory(String category, String newName){
        String query = "UPDATE categories SET name = '" + newName + "' WHERE name = '" + category + "'";
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void deleteCategory(String name)  {
        String query = "DELETE FROM categories WHERE name = '" + name + " ' ";
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void addLog(String log) {
        String query = "INSERT INTO logs VALUE(null, '" + log + "')";
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            statement.executeUpdate(query);

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public ArrayList<String> getLogs() {
        ArrayList<String> logs = new ArrayList<>();
        String query = "SELECT * FROM logs";
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                logs.add(result.getString("info"));
            }
            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return logs;
    }
}
