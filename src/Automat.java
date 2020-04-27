import java.util.*;

public class Automat implements ClientObservable {
    private static int ticketId;
    private List<ClientObserver> clients;
    private List<Ticket> tickets;
    private Map<Service, Ticket> serviceToClient;

    private Automat() {
        clients = new ArrayList<>();
        tickets = new ArrayList<>();
        serviceToClient = new LinkedHashMap<>();
    }

    public static Automat getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addService(Service service) {
        serviceToClient.put(service, null);
    }

    @Override
    public void attach(ClientObserver clientObserver) {
        clients.add(clientObserver);
    }

    @Override
    public void detach(ClientObserver clientObserver) {
        clients.remove(clientObserver);
    }

    @Override
    public void notifyClients(int serviceId, Ticket ticket) {
        clients.forEach(c -> {
            c.callClientToService(serviceId, ticket);
        });
    }

    public Ticket getTicket() {
        Ticket t = new Ticket(ticketId++);
        tickets.add(t);
        return t;
    }

    public String printClients() {
        StringBuilder sb = new StringBuilder();
        serviceToClient.forEach((service, ticket) -> {
            sb.append("(")
                    .append(ticket != null ? ticket.getId() : " ")
                    .append(")-> ")
                    .append(service.getName())
                    .append("   ");
        });
        return String.valueOf(sb);
    }

    public void onServiceFree(Service service) {
        Ticket t = null;
        if (!tickets.isEmpty()) {
            t = tickets.remove(0);
            notifyClients(service.getId(), t);
        }
        serviceToClient.put(service, t);
    }

    public Set<Service> getServices() {
        return serviceToClient.keySet();
    }

    private static class SingletonHolder {
        private static final Automat INSTANCE = new Automat();
    }
}
