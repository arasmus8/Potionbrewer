package potionbrewer.util;

import infinitespire.monsters.*;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class InfinitespireReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        //elites
        reagentList.put(Nightmare.ID, TwistedRelic.class);
        reagentList.put(Voidling.ID, Ichor.class);
        //bosses
        reagentList.put(LordOfAnnihilation.ID, PhilosopherShard.class);
        reagentList.put(LordOfDawn.ID, PhilosopherShard.class);
        reagentList.put(LordOfFortification.ID, PhilosopherShard.class);
        reagentList.put(MassOfShapes.ID, Pyramid.class);
    }
}
