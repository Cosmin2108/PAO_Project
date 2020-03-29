package agenda;

public class Birthday extends Event {
    private String Whose;
    private String Gift;

    public Birthday(){}

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
