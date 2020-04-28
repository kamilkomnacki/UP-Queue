import java.util.List;

public class Screen {
    private static void printSeparator() {
        System.out.println("-------------------------------------------------------");
    }

    public static void printHeader() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("-------------------KOLEJKA W ZUSIE---------------------");
        printSeparator();
        System.out.println("-Przyciski: 1 - Następna tura ----------2 - Nowa osoba-");
        System.out.println("----------- 9 - Restart-----------------0 - Zakończ----");
        printSeparator();
    }

    public static void printQueue(List<Client> clients) {
        System.out.print("Aktualna kolejka: ");
        clients.forEach(c -> {
            System.out.print(c.toString());
        });
        System.out.println();
    }

    public static void printAutomat(String automat) {
        printSeparator();
        System.out.println("Automat: " + automat);
        printSeparator();
    }
    public static void printService(char nameOfService, Ticket actualTicket, String lengthOfIssue) {
        System.out.println(String.format("Okienko %s: %s%s",
                nameOfService,
                actualTicket == null ? "" : actualTicket.getId(),
                "("+lengthOfIssue+")"));
    }
}
