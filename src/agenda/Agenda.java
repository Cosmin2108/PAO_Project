package agenda;

import filesServices.CategoriesFileService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Agenda {

    private static Agenda single_instance = null;

    private Scanner myScanner;
    private String description;
    private CategoriesFileService categoriesService;
    private Dictionary<String, List<Event>> events_of_the_categories;

    private Agenda()
    {
        description = "~ Your Agenda! ~";
        events_of_the_categories = new Hashtable<>();
        myScanner = new Scanner(System.in);
        categoriesService = CategoriesFileService.getInstance("src/categories.csv");

        //Default categories
        ArrayList<String> categoriesList = categoriesService.getCategoriesFromFile();
        for(String categories:categoriesList){
            events_of_the_categories.put(categories, new ArrayList<Event>()); // put an empty list, instead of None
        }
    }

    private static void Run(){
        Boolean run = true;
        while(run){
            switch (single_instance.ChooseAction()){
                case 1: single_instance.AddEvent(); break;
                case 2: single_instance.DeleteEvent(); break;
                case 3: break; // edit event Not implemented
                case 4: single_instance.AddCategory(); break;
                case 5: single_instance.DeleteCategory(); break;
                case 6: break; // edit category. Not implemented
                case 7: single_instance.showEvents(); break;
                case 8: run = false; single_instance = null; break; // close
            }
        }
    }

    // static method to create instance of Singleton class
    public static Agenda getInstance()
    {
        if (single_instance == null) {
            single_instance = new Agenda();
            Agenda.Run();
        }
        return single_instance;
    }

    private String showCategoriesOptions(Boolean show){

        int i = 0;
        List<String> keys = new ArrayList<String>(); // List of categories
        for(Enumeration k = this.events_of_the_categories.keys(); k.hasMoreElements();){
            i+=1;
            String key = k.nextElement().toString();
            keys.add(key);
            System.out.println(i + ". " + key);
        }

        i+=1;
        if(show) // not delete for example
            System.out.println(i + ". " + "All");
        System.out.print("Choose an category: ");

        int option = Integer.parseInt(this.myScanner.nextLine()) - 1; // Because indexes start at 1
        if(option == i - 1) // Reason of -1 ^^^
            return "All";

        if(keys.isEmpty())
            return "Empty";

        if(keys.size() -1 < option)
            return "Not found";

        String category = keys.get(option);
        return category;
    }

    private void AddEventDetails(Event event){
        // General information
        System.out.print("Event name: ");
        String name = this.myScanner.nextLine();

        System.out.print("\nDate (dd/MM/yyy): ");
        String inputDate = this.myScanner.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd/MM/yyy"));
        } catch (Exception e){
            System.out.println("You input isn't valid. We set the date to current date");
            date = LocalDate.now();
        }

        System.out.print("\nWhere: ");
        String where = this.myScanner.nextLine();

        System.out.print("\nWhen (HH:mm:ss): ");
        String when = this.myScanner.nextLine();

        event.setName(name);
        event.setDate(date);
        event.setWhere(where);
        event.setTime(when);
    }

    private void AddBirthdayEventDetails(Birthday event){
        System.out.print("\nWhose: ");
        String name = this.myScanner.nextLine();
        event.setWhose(name);
        System.out.print("\nGift: ");
        String gift = this.myScanner.nextLine();
        event.setGift(gift);
    }

    private void AddConcertEventDetails(Concert event){
        System.out.print("\nWho is singing?: ");
        String singer = this.myScanner.nextLine();
        event.setSinger(singer);

        System.out.print("\nEntry tax: ");
        String tax = this.myScanner.nextLine();
        event.setEntry(tax);
    }

    private void AddTripEventDetails(Trip event){
        System.out.print("\nDays: ");
        int days = Integer.parseInt(this.myScanner.nextLine());
        event.setNumberOfDays(days);

        System.out.print("\nTransport price: ");
        double priceTransport = Double.parseDouble(this.myScanner.nextLine());
        event.setTransportPrice(priceTransport);

        System.out.print("\nHotel price: ");
        double priceHotel = Double.parseDouble(this.myScanner.nextLine());
        event.setHotelPrice(priceHotel);

        System.out.print("\nExtra budget: ");
        double extraBudget = Double.parseDouble(this.myScanner.nextLine());
        event.setExtraBudget(extraBudget);

        System.out.print("\nTourist attractions (Enter names or 'x' to finish): ");

        List<String> Attractions = new ArrayList<>();
        while(true) {
            String name = this.myScanner.nextLine();
            if(name.contains("x"))
                break;
            else
                Attractions.add(name);
        }
        event.setTouristAttractions(Attractions);
    }

    private void AddExamEventDetails(Exam event){
        System.out.print("\nSubject studied: ");
        String subject = this.myScanner.nextLine();
        event.setSubject(subject);
    }

    private void AddMeetingEventDetails(Meeting event){
        System.out.print("\nReason: ");
        String reason = this.myScanner.nextLine();
        event.setReason(reason);

        System.out.print("\nStatus(Ex: personal, business): ");
        String status = this.myScanner.nextLine();
        event.setStatus(status);
    }

    private void AddOtherEventDetails(OtherEvent event){
        System.out.print("\nDescription: ");
        String desc = this.myScanner.nextLine();
        event.setDescription(desc);
    }

    public void AddEvent()
    {
        System.out.println("----------------------------------------------------");
        System.out.println("Available categories:");
        String category = this.showCategoriesOptions(false);
        System.out.println("----------------------------------------------------");

        if (category.contains("Empty") || category.contains("Not found")){
            System.out.println("No categories.");
            return;
        }

        Event event;

        // Extra information
        if(category.equals("Exam")) {
            event = new Exam();                     // daca il initializez in functie devine local si trebuie sa ii dau return
            AddEventDetails(event);
            AddExamEventDetails((Exam) event);
        }
        else
        if(category.equals("Trip")){
            event = new Trip();
            AddEventDetails(event);
            AddTripEventDetails((Trip) event);
        }
        else
        if(category.equals("Birthday")){
            event = new Birthday();
            AddEventDetails(event);
            AddBirthdayEventDetails((Birthday) event);
        }
        else
        if(category.equals("Meeting")){
            event = new Meeting();
            AddEventDetails(event);
            AddMeetingEventDetails((Meeting) event);
        }
        else
        if(category.equals("Concert")){
            event = new Concert();
            AddEventDetails(event);
            AddConcertEventDetails((Concert) event);
        }
        else
        {
            event = new OtherEvent();
            AddEventDetails(event);
            AddOtherEventDetails((OtherEvent) event);
        }
        System.out.print("\nDo you want to save changes? Yes/No: ");
        String answer = myScanner.nextLine();
        if(answer.contains("Yes")) {
            this.events_of_the_categories.get(category).add(event);         // add event to the chosen category
            System.out.println("Action performed.");
        }else
            System.out.println("Event wasn't added.");
    }

    public void DeleteEvent(){
        System.out.println("----------------------------------------------------");
        System.out.println("Available categories:");
        String category = this.showCategoriesOptions(true);
        System.out.println("----------------------------------------------------");

        if (category.contains("Empty") || category.contains("Not found")){
            System.out.println("No categories.");
            return;
        }

        if(category.equals("All")){
            for(Enumeration k = this.events_of_the_categories.keys(); k.hasMoreElements();){
                String key = k.nextElement().toString();
                this.events_of_the_categories.get(key).clear();
            }
        }else{
            List<Event> categoriesEvents = this.events_of_the_categories.get(category);
            if(!categoriesEvents.isEmpty()) {
                int i = 0;
                for (Event categoriesEvent : categoriesEvents)
                    System.out.println("Id: " + (++i) + "\n" + categoriesEvent.toString());
                System.out.println("----------------------------------------------------");
                System.out.print("Choose an event Id:");

                int element = Integer.parseInt(myScanner.nextLine()) - 1;
                if(this.events_of_the_categories.size() - 1 < element) {
                    System.out.println("Not exist.");
                    return; // oops.
                }
                this.events_of_the_categories.get(category).remove(element);
            }else
                System.out.println("List is empty.");
        }
    }

    public void AddCategory()
    {
        System.out.println("----------------------------------------------------");

        System.out.print("Enter the name of the new category: ");
        String name = this.myScanner.nextLine();
        this.events_of_the_categories.put(name, new ArrayList<>()); // there is no need to specify the argument for ArrayList
        categoriesService.addCategoriesToFile(name);
    }

    public void DeleteCategory() {
        System.out.println("----------------------------------------------------");
        System.out.println("Your categories. Choose one to delete.");
        String category = this.showCategoriesOptions(false);

        if (category.contains("Empty") || category.contains("Not found")){
            System.out.println("No categories.");
            return;
        }
        this.events_of_the_categories.remove(category);
    }


    public void showEvents()
    {
        System.out.println("----------------------------------------------------");
        System.out.println("Your categories. Choose one to view.");
        String category = this.showCategoriesOptions(true);
        System.out.println("----------------------------------------------------");

        if (category.contains("Empty") || category.contains("Not found")){
            System.out.println("No categories.");
            return;
        }

        if(category.equals("All")){
            for(Enumeration k = this.events_of_the_categories.keys(); k.hasMoreElements();){
                String key = k.nextElement().toString();
                System.out.println(key+":");
                List<Event> keyEvent = this.events_of_the_categories.get(key);
                for(Event event : keyEvent){
                    System.out.println(event.toString());
                    System.out.println();
                }
                System.out.println("----------------------------------------------------");
            }
        }else{
            List<Event> categoriesEvents = this.events_of_the_categories.get(category);
            for (Event categoriesEvent : categoriesEvents) System.out.println(categoriesEvent.toString());
        }
    }

    public int ChooseAction(){
        System.out.println("1. Create event\n2. Delete event\n3. Edit event\n4. Create category\n5. Delete category\n6. Edit category\n7. Show events\n8. Close");
        System.out.print("Choose an action: ");
        int option = Integer.parseInt(myScanner.nextLine());
        return option;
    }
}
