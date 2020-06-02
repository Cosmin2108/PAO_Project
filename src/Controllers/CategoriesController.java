package Controllers;

import DB.DbContext;
import agenda.Birthday;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriesController {
    private DbContext context = DbContext.getInstance();

    public CategoriesController() {
    }

    public ArrayList<String> getCategories() {
        ArrayList<String> categories = context.getCategories();
        return categories;
    }

    public void addCategory(String name) {
        context.addCategories(name);
    }

    public void deleteCategory(String name) {
        context.deleteCategory(name);
    }

    public void editCategory(String category, String newName) {
        context.editCategory(category, newName);
    }
}
