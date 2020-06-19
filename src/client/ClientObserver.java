package client;

import automat.Ticket;
/*
* Interfejs dla obiektu obserwatora
*
* */
public interface ClientObserver {
    void callClientToService(int serviceId, Ticket ticket);
}
