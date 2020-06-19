package service;

import automat.Automat;
import automat.Ticket;
import client.Client;

public class Service {
    private static final int ASCII_CODE_A_CHAR = 65;
    private final int id;
    private final char name;
    private Client currentClient;

    public Service(int id) {
        this.id = id;
        this.name = generateName(id);
        this.currentClient = null;
    }

    public int getId() {
        return id;
    }

    public Ticket getActualClientTicket() {
        if(currentClient == null) {
            return null;
        } else {
            return currentClient.getTicket();
        }
    }

    public void setActualClient(Client newClient) {
        this.currentClient = newClient;
    }

    public void actualizeState() {
        if(currentClient == null) {
            Automat.getInstance().onServiceFree(this);
        } else {
            if(currentClient.getIssueLength() == 0) {
                currentClient = null;
            } else {
                currentClient.actualizeIssueLength();
            }
        }
    }

    public char getName() {
        return name;
    }

    private char generateName(int serviceId) {
        return (char) (ASCII_CODE_A_CHAR + serviceId);
    }
}
