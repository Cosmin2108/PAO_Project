package agenda;
import java.util.*;

public class Trip extends Event {
    private int NumberOfDays;
    private double TransportPrice;
    private double HotelPrice;
    private List<String> TouristAttractions;
    private double ExtraBudget;

    public Trip(){}

    public void setTouristAttractions(List<String> touristAttractions) {
        TouristAttractions = touristAttractions;
    }

    public void setNumberOfDays(int numberOfDays) {
        NumberOfDays = numberOfDays;
    }

    public void setTransportPrice(double transportPrice) {
        TransportPrice = transportPrice;
    }

    public void setHotelPrice(double hotelPrice) {
        HotelPrice = hotelPrice;
    }

    public void setExtraBudget(double extraBudget) {
        ExtraBudget = extraBudget;
    }

    @Override
    public String toString() {
        return super.toString() + "\nNumber of days: " + this.NumberOfDays + "\nTransport price: "  + this.TransportPrice + "\nHotel price: " + this.HotelPrice + "\nExtra budget: " + this.ExtraBudget + "\nTourist attractions: " + this.TouristAttractions + "\n";
    }
}
