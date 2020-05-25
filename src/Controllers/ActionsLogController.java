package Controllers;

import DB.DbContext;
import agenda.Birthday;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ActionsLogController {
    private DbContext context;

    public ActionsLogController() throws SQLException {
        context = DbContext.getInstance();
    }

    public ArrayList<String> getLogs() throws SQLException {
        ArrayList<String> logs = context.getLogs();
        return logs;
    }

    public void addLog(ArrayList<String> log){
        String line = String.join(", ", log);
        try {
            LocalTime date = LocalTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss"); // or DateTimeFormatter.ISO_LOCAL_TIME
            String dateFormatted = date.format(myFormatObj);
            context.addLog("Logged at: " + dateFormatted + ", " + line);
        }catch (SQLException e){
            System.out.println("An error occurred while inserting into the DB");
        }
    }

    public void editLogs(){
    }
}

