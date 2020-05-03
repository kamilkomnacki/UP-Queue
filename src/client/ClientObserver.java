package client;

import automat.Ticket;

public interface ClientObserver {
    void callClientToService(int serviceId, Ticket ticket);
}
