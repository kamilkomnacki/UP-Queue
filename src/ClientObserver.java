public interface ClientObserver {
    void callClientToService(int serviceId, Ticket ticket);
}
