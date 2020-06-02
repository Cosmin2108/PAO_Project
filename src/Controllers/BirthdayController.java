package Controllers;

import DB.DbContext;
import agenda.Birthday;

import java.sql.SQLException;
import java.util.ArrayList;

public class BirthdayController {

    private DbContext context = DbContext.getInstance();

    public BirthdayController() {
    }

    public ArrayList<Birthday> getBirthdays() {
        ArrayList<Birthday> birthdays = context.getBirthdays();
        return birthdays;
    }

    public void addBirthday(Birthday event) {
        context.addBirthday(event);
    }

    public void deleteBirthday(Birthday element) {
        context.deleteBirthday(element);
    }

    public void editBirthday(Birthday event){
        context.editBirthday(event);
    }
}
