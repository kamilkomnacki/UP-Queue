package automat;

import client.ClientObservable;
import client.ClientObserver;
import service.Service;

import java.util.*;

// TODO: (1) SINGLETON
// TODO: (3) Obserwowany
/*
 *
 * Jest obiektem typu singletonem.
 * Jest obiektem obserwowanym przez obiekty implementujące ClientObserver.
 * */
public class Automat implements ClientObservable {
    private static int ticketId;
    private List<ClientObserver> clients;
    private List<Ticket> tickets;
    private Map<Service, Ticket> serviceToClient;

    private Automat() {
        init();
    }

    private void init() {
        ticketId = 0;
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

    public void restart() {
        init();
    }

    public Ticket getTicket(boolean isVIP) {
        Ticket t = new Ticket(ticketId++);
        if (isVIP) {
            tickets.add(0, t);
        } else {
            tickets.add(t);
        }
        return t;
    }

    public String printClients() {
        StringBuilder sb = new StringBuilder();
        serviceToClient.forEach((service, ticket) -> {
            if (ticket != null) {
                sb.append("[")
                        .append(ticket.getId())
                        .append("]-> ")
                        .append(service.getName())
                        .append("   ");
            }
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
