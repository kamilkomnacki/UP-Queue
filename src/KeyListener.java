import java.util.Scanner;

public class KeyListener {
    enum Keys {
        _1_NEXT, _2_NEW_CLIENT, _9_RESTART, _0_END, NULL
    }

    public static Keys readInput() {
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()) {
            String input = scanner.nextLine();
            if (input != null && !input.isEmpty() && Character.isDigit(input.charAt(0))) {
                int inputDigit = (input.charAt(0) - 48);
                switch (inputDigit) {
                    case 0:
                        return Keys._0_END;
                    case 1:
                        return Keys._1_NEXT;
                    case 2:
                        return Keys._2_NEW_CLIENT;
                    case 9:
                        return Keys._9_RESTART;
                    default:
                        return Keys.NULL;
                }
            }
            System.out.println("readInput: " + input);
        }
        return Keys._2_NEW_CLIENT;
    }
}
