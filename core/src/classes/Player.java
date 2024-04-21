package classes;

public class Player {
    private int health;
    private String name;
    private boolean status;

    public Player(int health, String name, boolean status) {
        this.health = health;
        this.name = name;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void setDead() {
        this.status = true;
    }

    public boolean getStatus() {
        return status;
    }
}
