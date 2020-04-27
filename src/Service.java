public class Service {
    private int id;
    private char name;
    private Client currentClient;
    private boolean isActive;

    public Service(int id) {
        this.id = id;
        this.name = generateName(id);
        this.isActive = false;
        this.currentClient = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getActualClientTicket() {
        if(currentClient == null) {
            return null;
        } else {
            return currentClient.getTicket();
        }
    }

    public void setActualClient(Client newClient) {
        isActive = true;
        this.currentClient = newClient;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void actualizeState() {
        if(currentClient == null) {
            isActive = false;
            Automat.getInstance().onServiceFree(this);
        } else {
            if(currentClient.getIssueLength() == 0) {
                currentClient = null;
            } else {
                currentClient.setIssueLength(currentClient.getIssueLength() - 1);
            }
        }
    }

    public char getName() {
        return name;
    }

    private char generateName(int serviceId) {
        return (char) (65 + serviceId);
    }
}
