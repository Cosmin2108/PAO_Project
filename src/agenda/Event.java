package agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Event {

    private String Name;
    private LocalDate Date;
    private String Where;
    private String Time;

    public Event(){ }

    public Event(List<String> event){
        setName(event.get(0));
        setDate(LocalDate.parse(event.get(1)));
        setWhere(event.get(2));
        setTime(event.get(3));
    }

    public void setName(String name){
        this.Name = name;
    }

    public void setDate(LocalDate date){
        this.Date = date;
    }

    public void setWhere(String location){
        this.Where = location;
    }

    public void setTime(String time){
        this.Time = time;
    }

    public LocalDate getDate() {
        return Date;
    }

    public String getName() {
        return Name;
    }

    public String getTime() {
        return Time;
    }

    public String getWhere() {
        return Where;
    }

    @Override
    public String toString() {
        return "Event name: " + this.Name + "\nDate: " + this.Date + "\nLocation: " + this.Where + "\nWhen: " + this.Time;
    }
}
