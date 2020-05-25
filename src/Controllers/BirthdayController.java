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

    public void addBirthday(Birthday event) throws SQLException {
        context.addBirthday(event);
    }

    public void deleteBirthday(Birthday element) throws SQLException {
        context.deleteBirthday(element);
    }

    public void editBirthday(){

    }
}
