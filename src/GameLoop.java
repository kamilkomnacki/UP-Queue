import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

class GameLoop extends TimerTask {
    private static final int NUMBER_OF_SERVICES = 5;
    private List<Client> clients;

    public GameLoop() {
        this.clients = new ArrayList<>();
        initServices();
    }

    private void initServices() {
        for (int i = 0; i < NUMBER_OF_SERVICES; i++) {
            Automat.getInstance().addService(new Service(i));
        }
    }

    public void run() {
        System.out.flush();
        printScreen();
        while (true) {
            switch (KeyListener.readInput()) {
                case _0_END:
                    return;
                case _1_NEXT:
                    nextRound();
                    break;
                case _2_NEW_CLIENT:
                    newClient();
                    break;
                case _9_RESTART:

                    clients = new ArrayList<>();
                    Automat.getInstance().restart();
                    initServices();
                    Automat.getInstance().getServices().forEach(Service::actualizeState);
                    break;
                default:
                    break;
            }
            printScreen();
        }
    }

    private void newClient() {
        Client client = new Client(Automat.getInstance().getTicket());
        clients.add(client);
        Automat.getInstance().attach(client);
    }

    private void nextRound() {
        clients.removeIf(c -> c.getIssueLength() == 0);

        Automat.getInstance().getServices().forEach(service ->
                service.actualizeState()
        );

        clients.forEach(c -> {
            Integer actualServiceId = c.getActualServicedBy();
            if (actualServiceId != null) {
                Automat.getInstance().getServices().stream()
                        .filter(service -> service.getId() == actualServiceId)
                        .forEach(service -> service.setActualClient(c));
            }
        });

    }

    private void printScreen() {
        Screen.printHeader();

        Screen.printQueue(clients.stream().filter(c -> c.getActualServicedBy() == null).collect(Collectors.toList()));

        Screen.printAutomat(Automat.getInstance().printClients());

        Automat.getInstance().getServices().forEach(service -> {
            String issueLength;
            if (clients.stream()
                    .anyMatch(c -> c.getActualServicedBy() != null && c.getActualServicedBy() == service.getId())
            ) {
                issueLength = String.valueOf(clients.stream()
                        .filter(c -> c.getActualServicedBy() == service.getId())
                        .findFirst()
                        .get()
                        .getIssueLength());
            } else {
                issueLength = "";
            }

            Screen.printService(
                    service.getName(),
                    service.getActualClientTicket(),
                    issueLength
            );
        });
    }
}