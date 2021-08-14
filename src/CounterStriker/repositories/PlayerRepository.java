package CounterStriker.repositories;

import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static CounterStriker.common.ExceptionMessages.*;

public class PlayerRepository implements Repository<Player> {
    private Collection<Player> models;

    public PlayerRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Player> getModels() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Player player) {
        if (player == null) {
            throw new NullPointerException(INVALID_PLAYER_REPOSITORY);
        }

        this.models.add(player);
    }

    @Override
    public boolean remove(Player player) {
        return this.models.remove(player);
    }

    @Override
    public Player findByName(String name) {
        return this.models.stream()
                .filter(p -> p.getUsername().equals(name))
                .findFirst()
                .orElse(null);
    }
}
