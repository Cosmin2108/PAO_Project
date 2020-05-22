package agenda;
import java.util.*;

public class Trip extends Event {
    private String NumberOfDays;
    private String TransportPrice;
    private String HotelPrice;
    private List<String> TouristAttractions;
    private String ExtraBudget;
    private int Id;

    public Trip(){}

    public Trip(ArrayList<String> event){
        super(Arrays.asList(event.get(0), event.get(1), event.get(2), event.get(3)));
        setNumberOfDays(event.get(4));
        setTransportPrice(event.get(5));
        setHotelPrice(event.get(6));
        ArrayList<String> attractions = new ArrayList<String>(Arrays.asList(event.get(7).split(";")));
        setTouristAttractions(attractions);
        setExtraBudget(event.get(8));
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTouristAttractions(List<String> touristAttractions) {
        TouristAttractions = touristAttractions;
    }

    public void setNumberOfDays(String numberOfDays) {
        NumberOfDays = numberOfDays;
    }

    public void setTransportPrice(String transportPrice) {
        TransportPrice = transportPrice;
    }

    public void setHotelPrice(String hotelPrice) {
        HotelPrice = hotelPrice;
    }

    public void setExtraBudget(String extraBudget) {
        ExtraBudget = extraBudget;
    }

    @Override
    public String toString() {
        return super.toString() + "\nNumber of days: " + this.NumberOfDays + "\nTransport price: "  + this.TransportPrice + "\nHotel price: " + this.HotelPrice + "\nExtra budget: " + this.ExtraBudget + "\nTourist attractions: " + this.TouristAttractions + "\n";
    }
}
