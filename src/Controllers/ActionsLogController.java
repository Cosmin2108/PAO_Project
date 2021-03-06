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

    public ActionsLogController() {
        context = DbContext.getInstance();
    }

    public ArrayList<String> getLogs() {
        ArrayList<String> logs = context.getLogs();
        return logs;
    }

    public void addLog(ArrayList<String> log){
        String line = String.join(", ", log);
        LocalTime date = LocalTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss"); // or DateTimeFormatter.ISO_LOCAL_TIME
        String dateFormatted = date.format(myFormatObj);
        context.addLog("Logged at: " + dateFormatted + ", " + line);
    }

    public void editLogs(){
    }
}

