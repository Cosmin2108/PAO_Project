package agenda;

public class Meeting extends Event {
    private String Reason; // ex: sedinta, bere, business
    private String Status; // ex: urgent, personal, public,

    public void setStatus(String status) {
        Status = status;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    @Override
    public String toString() {
        return super.toString() + "\nReason: " + this.Reason + "\nStatus: " + this.Status + "\n ";
    }
}
