package Controllers;

import DB.DbContext;
import agenda.Birthday;

import java.sql.SQLException;
import java.util.ArrayList;

public class BirthdayController {

    private DbContext context = DbContext.getInstance();

    public BirthdayController() throws SQLException {
    }

    public ArrayList<Birthday> getBirthdays() throws SQLException {
        ArrayList<Birthday> birthdays = context.getBirthdays();
        return birthdays;
    }

    public void addBirthday(){

    }

    public void deleteBirthday(){

    }

    public void editBirthday(){

    }
}
