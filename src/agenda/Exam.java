package agenda;

public class Exam extends Event{
    private String Subject;

    public void setSubject(String subject) {
        Subject = subject;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSubject exam: " + this.Subject;
    }
}
