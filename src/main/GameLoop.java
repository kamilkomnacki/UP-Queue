package main;

import automat.Automat;
import client.Client;
import client.ClientOrdinal;
import client.ClientVIP;
import client.ClientVIPAdapter;
import service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static main.KeyListener.*;

class GameLoop extends TimerTask {

    private boolean isInstructionRun;
    private static final int NUMBER_OF_SERVICES = 5;
    private List<Client> clients;

    public GameLoop() {
        this.clients = new ArrayList<>();
        this.isInstructionRun = false;
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
            if (isInstructionRun) {
                if (readInput() == Keys._1_NEXT) {
                    isInstructionRun = false;
                    printScreen();
                } else {
                    Screen.printInstruction();
                }
            } else {
                switch (readInput()) {
                    case _0_END:
                        return;
                    case _1_NEXT:
                        nextRound();
                        printScreen();
                        break;
                    case _2_NEW_CLIENT_ORDINAL:
                        newClient(false);
                        printScreen();
                        break;
                    case _3_NEW_CLIENT_VIP:
                        newClient(true);
                        printScreen();
                        break;
                    case _8_INSTRUCTION_:
                        isInstructionRun = true;
                        Screen.printInstruction();
                        break;
                    case _9_RESTART:
                        clients = new ArrayList<>();
                        Automat.getInstance().restart();
                        initServices();
                        Automat.getInstance().getServices().forEach(Service::actualizeState);
                        printScreen();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void newClient(boolean isVIP) {
        Client client;
        if (isVIP) {
            client = new ClientVIPAdapter(
                    new ClientVIP(
                            Automat.getInstance().getTicket(true),
                            isBossClient(),
                            isPoliticsClient()));
            Automat.getInstance().attach((ClientVIPAdapter) client);
            clients.add(0, client);
        } else {
            client = new ClientOrdinal(Automat.getInstance().getTicket(false));
            Automat.getInstance().attach((ClientOrdinal) client);
            clients.add(client);
        }

    }

    private void nextRound() {
        clients.stream()
                .filter(c -> c.getIssueLength() == 0)
                .forEach(c -> {
                    if (c instanceof ClientVIPAdapter) {
                        Automat.getInstance().detach((ClientVIPAdapter) c);
                    } else {
                        Automat.getInstance().detach((ClientOrdinal) c);
                    }
                });
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
        Screen.print(clients);
    }

    private boolean isPoliticsClient() {
        return clients.size() / 3 == 0;
    }

    private boolean isBossClient() {
        return clients.size() / 2 == 0;
    }
}