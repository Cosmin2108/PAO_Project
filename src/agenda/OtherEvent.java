package agenda;

public class OtherEvent extends Event {

    public String Description;

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDescription: " + this.Description + "\n";
    }
}
