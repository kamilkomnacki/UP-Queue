package client;

import automat.Ticket;

//TODO: (3) Interfejs obiektu, ktory obserwuje.
/*
* Interfejs dla obiektu obserwatora
*
* */
public interface ClientObserver {
    void callClientToService(int serviceId, Ticket ticket);
}
