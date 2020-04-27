public interface ClientObservable {
    void attach(ClientObserver clientObserver);

    void detach(ClientObserver clientObserver);

    void notifyClients(int serviceId, Ticket ticket);
}
