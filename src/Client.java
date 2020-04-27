import java.util.Random;

public class Client implements ClientObserver{
    private int id;
    private Ticket myTicket;
    private Integer actualServicedBy;
    private int issueLength;

    public Client(Ticket myTicket) {
        this.myTicket = myTicket;
        this.issueLength = new Random().nextInt(5) + 1;
        actualServicedBy = null;
    }

    public Ticket getTicket() {
        return myTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIssueLength() {
        return issueLength;
    }

    public void setIssueLength(int issueLength) {
        this.issueLength = issueLength;
    }

    @Override
    public String toString() {
        return myTicket.getId() + "(" + issueLength + ") ";
    }

    @Override
    public void callClientToService(int serviceId, Ticket calledTicked) {
        if (calledTicked.equals(myTicket)) {
            actualServicedBy = serviceId;
        }
    }

    public Integer getActualServicedBy() {
        return actualServicedBy;
    }
}
