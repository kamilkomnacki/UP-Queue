package client;

import automat.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Adapter dla klasy ClientVIP.
* Implementuje ClientObserver, czyli jest obserwatorem
* */
public class ClientVIPAdapter implements Client, ClientObserver {
    private ClientVIP clientVIP;
    private int issueLength;
    private Integer actualServicedBy;

    public ClientVIPAdapter(ClientVIP clientVIP) {
        this.clientVIP = clientVIP;
        this.actualServicedBy = null;
        this.issueLength = generateIssueLength();
    }

    private int generateIssueLength() {
        List<Integer> issueLengthIngrediends = new ArrayList<>();
        issueLengthIngrediends.add(clientVIP.getImportance());
        issueLengthIngrediends.add(clientVIP.getCompany().name().length());
        issueLengthIngrediends.add(clientVIP.isBoss() ? 1 : 0);
        issueLengthIngrediends.add(clientVIP.isPolitics() ? 1 : 0);
        this.issueLength = issueLengthIngrediends.stream().mapToInt(Integer::intValue).sum();
        return issueLength;
    }

    @Override
    public int getIssueLength() {
        return issueLength;
    }

    @Override
    public String getAppearance() {
        return "!" + clientVIP.getTicket().getId() + "(" + getIssueLength() + ")! ";
    }

    @Override
    public Ticket getTicket() {
        return clientVIP.getTicket();
    }

    @Override
    public void actualizeIssueLength() {
        issueLength--;
    }

    @Override
    public Integer getActualServicedBy() {
        return actualServicedBy;
    }

    @Override
    public void callClientToService(int serviceId, Ticket calledTicket) {
        if (calledTicket.equals(clientVIP.getTicket())) {
            this.actualServicedBy = serviceId;
        }
    }
}