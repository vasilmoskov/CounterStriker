package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

import static CounterStriker.common.ExceptionMessages.*;

public abstract class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    protected PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setGun(gun);
        this.isAlive = true; //check
    }

    @Override
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void takeDamage(int points) {

        if (points <= armor) {
            this.armor -= points;
        } else {
            int toBeTakenFromHealth = points - this.armor;
            this.armor = 0;
            this.health -= toBeTakenFromHealth;
        }

        if (this.health <= 0) {
            this.health = 0;
            this.isAlive = false;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s: %s", this.getClass().getSimpleName(), getUsername())).append(System.lineSeparator())
                .append(String.format("--Health: %d", getHealth())).append(System.lineSeparator())
                .append(String.format("--Armor: %d", getArmor())).append(System.lineSeparator())
                .append(String.format("--Gun: %s", getGun().getName()));

        return builder.toString().trim();
    }
}
