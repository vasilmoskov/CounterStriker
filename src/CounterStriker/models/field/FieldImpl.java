package CounterStriker.models.field;

import CounterStriker.models.guns.Gun;
import CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static CounterStriker.common.OutputMessages.*;

public class FieldImpl implements Field {

    @Override
    public String start(Collection<Player> players) {

        List<Player> terrorists = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("Terrorist"))
                .collect(Collectors.toList());

        List<Player> counterTerrorists = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("CounterTerrorist"))
                .collect(Collectors.toList());

        while (true) {
            for (Player terrorist : terrorists) {
                if (terrorist.isAlive()) {
                    for (Player counterTerrorist : counterTerrorists) {
                        if (counterTerrorist.isAlive()) {
                            int damage = terrorist.getGun().fire();
                            counterTerrorist.takeDamage(damage);
                        }
                    }
                }
            }

            for (Player counterTerrorist : counterTerrorists) {
                if (counterTerrorist.isAlive()) {
                    for (Player terrorist : terrorists) {
                        if (terrorist.isAlive()) {
                            int damage = counterTerrorist.getGun().fire();
                            terrorist.takeDamage(damage);
                        }
                    }
                }
            }

            if (terrorists.stream().filter(t -> t.isAlive()).count() == 0) {
                return COUNTER_TERRORIST_WINS;
            }

            if (counterTerrorists.stream().filter(ct -> ct. isAlive()).count() == 0) {
                return TERRORIST_WINS;
            }
        }
    }
}
