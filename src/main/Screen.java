package main;

import automat.Automat;
import automat.Ticket;
import client.Client;

import java.util.List;
import java.util.stream.Collectors;

public class Screen {
    private static void printSeparator() {
        System.out.println("-----------------------------------------------------------");
    }

    private static void printHeader() {
        newScreen();
        System.out.println("-------------------KOLEJKA W ZUSIE-------------------------");
        System.out.println("-------------------UP 2019/2020 WP-------------------------");
        System.out.println("-------------------Kamil  Komnacki-------------------------");
        System.out.println("-----------------------------------------------------------");
        printSeparator();
    }

    private static void newScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    private static void printMenu() {
        System.out.println("-Przyciski: 1 - Następna tura ----------2 - Nowa osoba-----");
        System.out.println("-Przyciski: 8 - Instrukcja -------------3 - Nowa osoba VIP-");
        System.out.println("----------- 9 - Restart-----------------0 - Zakończ--------");
        printSeparator();
    }

    private static void printQueue(List<String> clients) {
        System.out.print("Aktualna kolejka: ");
        clients.forEach(System.out::print);
        System.out.println();
    }

    private static void printAutomat(String automat) {
        printSeparator();
        System.out.println("Automat: " + automat);
        printSeparator();
    }

    private static void printService(char nameOfService, Ticket actualTicket, String lengthOfIssue) {
        System.out.println(String.format("Okienko %s: %s%s",
                nameOfService,
                actualTicket == null ? "" : actualTicket.getId(),
                "("+lengthOfIssue+")"));
    }

    public static void print(List<Client> clients) {
        printHeader();
        printMenu();

        Screen.printQueue(clients.stream().
                filter(c -> c.getActualServicedBy() == null)
                .map(Client::getAppearance)
                .collect(Collectors.toList()));

        Screen.printAutomat(Automat.getInstance().printClients());

        Automat.getInstance().getServices().forEach(service -> {
            String issueLength;
            if (clients.stream()
                    .anyMatch(c -> c.getActualServicedBy() != null && c.getActualServicedBy() == service.getId())
            ) {
                issueLength = String.valueOf(clients.stream()
                        .filter(c ->
                            c.getActualServicedBy() != null &&
                            c.getActualServicedBy() == service.getId())
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

    public static void printInstruction() {
        printHeader();

        System.out.println("-Instrukcja: ----------------------------------------------");
        printSeparator();
        System.out.println("Program przedstawia problem 'producent-konsumer' na ");
        System.out.println("przykładzie kolejki w ZUS (Zakład Ubezpieczeń Społecznych).");
        System.out.println("W programie występują dwa typy klientów: ");
        System.out.println("Klienci normalni, oznaczeni cyfrą, kolejkowani metodą FIFO");
        System.out.println("Klienci VIP, oznaczeni wykrzyknikami i cyfrą, są ");
        System.out.println("kolejkowani metodą LIFO (wpychają się na początek kolejki)");
        printSeparator();
        System.out.println("Przy każdym kliencie w nawiasie wyświetlona jest dłogość ");
        System.out.println("sprawy z jaką przyszedł do ZUSU. Gdy jest obsługiwany,");
        System.out.println("z każdą turą przy okienku długość sprawy dekrementowana ");
        System.out.println("jest o 1.");
        printSeparator();
        System.out.println("(Aby wrócic do gry, wybierz 1...)");

    }
}
