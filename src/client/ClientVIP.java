package client;

import automat.Ticket;

import java.util.Random;

public class ClientVIP {
    public static final int VIP_IMPORTANCE_MAXIMUM = 7;

    public enum COMPANY {
        UP, GOOGLE, APPLE, SAMSUNG, FACEBOOK, AMAZON, CP_PROJEKT;
    }
    private COMPANY company;

    private int importance;
    private boolean isBoss;
    private boolean isPolitics;
    private final Ticket myTicket;

    public ClientVIP(Ticket ticket, boolean isBoss, boolean isPolitics) {
        Random rand = new Random();
        this.company = COMPANY.values()[rand.nextInt(COMPANY.values().length)];
        this.importance = rand.nextInt(VIP_IMPORTANCE_MAXIMUM);
        this.isBoss = isBoss;
        this.isPolitics = isPolitics;
        this.myTicket = ticket;
    }

    public Ticket getTicket() {
        return myTicket;
    }

    public COMPANY getCompany() {
        return company;
    }

    public int getImportance() {
        return importance;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public boolean isPolitics() {
        return isPolitics;
    }
}
