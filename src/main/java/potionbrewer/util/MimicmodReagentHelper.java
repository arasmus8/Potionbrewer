package potionbrewer.util;

import basemod.ReflectionHacks;
import mimicmod.monsters.Mimic;
import potionbrewer.orbs.Chest;
import potionbrewer.orbs.Reagent;

import java.util.HashMap;

public class MimicmodReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        String id = (String) ReflectionHacks.getPrivateStatic(Mimic.class, "ID");
        reagentList.put(id, Chest.class);
    }
}
