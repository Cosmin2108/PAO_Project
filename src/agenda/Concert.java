package agenda;

public class Concert extends Event {
    private String Singer;
    private String Entry; // amount of money

    public void setEntry(String entry) {
        Entry = entry;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    @Override
    public String toString() {
        return super.toString() + "\nWho is singing: " + this.Singer + "\nEntry tax: " + this.Entry + "\n ";
    }
}
