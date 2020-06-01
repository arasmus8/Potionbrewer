package potionbrewer.util;

import HollowMod.monsters.*;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class HollowknightReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        //BugKnight
        reagentList.put(eliteMossKnight.ID, Bug.class);
        reagentList.put(eliteNosk.ID, Bug.class);
        reagentList.put(eliteStalkingDevout.ID, Bug.class);
        reagentList.put(monsterHuskSentry.ID, Bug.class);
        reagentList.put(monsterHuskWarrior.ID, Bug.class);
        reagentList.put(monsterLittleWeaver.ID, Bug.class);
        reagentList.put(monsterSlobberingHusk.ID, Bug.class);
        reagentList.put(monsterViolentHusk.ID, Bug.class);
        reagentList.put(eventZote.ID, Bug.class);
        //Bosses
        reagentList.put(bossFalseKnight.ID, CityCrest.class);
        reagentList.put(bossNKGrimm.ID, Fireflies.class);
        reagentList.put(bossRadiance.ID, Radiance.class);
    }
}
