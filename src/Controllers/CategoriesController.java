package Controllers;

import DB.DbContext;
import agenda.Birthday;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriesController {
    private DbContext context = DbContext.getInstance();

    public CategoriesController() throws SQLException {
    }

    public ArrayList<String> getCategories() throws SQLException {
        ArrayList<String> categories = context.getCategories();
        return categories;
    }

    public void addCategory(String name) throws SQLException {
        context.addCategories(name);
    }

    public void deleteCategory(String name) throws SQLException {
        context.deleteCategory(name);
    }

    public void editCategory(){

    }
}
