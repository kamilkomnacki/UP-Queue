package client;

import automat.Ticket;

public interface Client {
    int getIssueLength();

    String getAppearance();

    Ticket getTicket();

    void actualizeIssueLength();

    Integer getActualServicedBy();
}
