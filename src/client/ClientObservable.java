package client;

import automat.Ticket;

/*
*
* Interfejs dla obiektu obserwowanego
*
* */

public interface ClientObservable {
    void attach(ClientObserver clientObserver);

    void detach(ClientObserver clientObserver);

    void notifyClients(int serviceId, Ticket ticket);
}
