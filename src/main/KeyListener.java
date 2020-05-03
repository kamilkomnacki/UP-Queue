package main;

import java.util.Scanner;

public class KeyListener {
    public enum Keys {
        _1_NEXT, _2_NEW_CLIENT_ORDINAL, _3_NEW_CLIENT_VIP, _8_INSTRUCTION_, _9_RESTART, _0_END, NULL
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
                        return Keys._2_NEW_CLIENT_ORDINAL;
                    case 3:
                        return Keys._3_NEW_CLIENT_VIP;
                    case 8:
                        return Keys._8_INSTRUCTION_;
                    case 9:
                        return Keys._9_RESTART;
                    default:
                        return Keys.NULL;
                }
            }
            System.out.println("readInput: " + input);
        }
        return Keys.NULL;
    }
}
