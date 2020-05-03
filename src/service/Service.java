package service;

import automat.Automat;
import automat.Ticket;
import client.Client;
import client.ClientOrdinal;

public class Service {
    private static final int ASCII_CODE_A_CHAR = 65;
    private int id;
    private char name;
    private Client currentClient;
    private boolean isActive;

    public Service(int id) {
        this.id = id;
        this.name = generateName(id);
        this.isActive = false;
        this.currentClient = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getActualClientTicket() {
        if(currentClient == null) {
            return null;
        } else {
            return currentClient.getTicket();
        }
    }

    public void setActualClient(Client newClient) {
        isActive = true;
        this.currentClient = newClient;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void actualizeState() {
        if(currentClient == null) {
            isActive = false;
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
