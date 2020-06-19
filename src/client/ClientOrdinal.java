package client;

import automat.Ticket;

import java.util.Random;

/*
* Implementuje ClientObserver, czyli jest obserwatorem.
* */
public class ClientOrdinal implements ClientObserver, Client {
    private Ticket myTicket;
    private Integer actualServicedBy;
    private int issueLength;

    public ClientOrdinal(Ticket myTicket) {
        this.myTicket = myTicket;
        this.issueLength = new Random().nextInt(5) + 1;
        this.actualServicedBy = null;
    }

    public Ticket getTicket() {
        return myTicket;
    }

    @Override
    public void actualizeIssueLength() {
        issueLength--;
    }

    public int getIssueLength() {
        return issueLength;
    }

    @Override
    public String getAppearance() {
        return myTicket.getId() + "(" + issueLength + ") ";
    }

    public Integer getActualServicedBy() {
        return actualServicedBy;
    }

    @Override
    public void callClientToService(int serviceId, Ticket calledTicket) {
        if (calledTicket.equals(myTicket)) {
            actualServicedBy = serviceId;
        }
    }
}
