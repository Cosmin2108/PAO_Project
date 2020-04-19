package agenda;

import java.util.ArrayList;
import java.util.Arrays;

public class Birthday extends Event {
    private String Whose;
    private String Gift;

    public Birthday(){}

    public Birthday(ArrayList<String> event){
        super(Arrays.asList(event.get(0), event.get(1), event.get(2), event.get(3)));
        setWhose(event.get(4));
        setGift(event.get(5));
    }

    public void setWhose(String whose) {
        Whose = whose;
    }

    public void setGift(String gift) {
        Gift = gift;
    }

    @Override
    public String toString() {
        return super.toString() + "\nWhose birthday: " + this.Whose + "\nGift: " + this.Gift + "\n ";
    }
}
