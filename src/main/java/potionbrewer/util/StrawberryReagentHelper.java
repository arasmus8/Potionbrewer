package potionbrewer.util;

import StrawberrySpireMod.monsters.elite.*;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class StrawberryReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(Accio.ID, Flame.class);
        reagentList.put(AncientClocktower.ID, LaserCore.class);
        reagentList.put(Crucio.ID, Flame.class);
        reagentList.put(ElectricalPylon.ID, LaserCore.class);
        reagentList.put(Imperio.ID, Steel.class);
        reagentList.put(KineticPylon.ID, LaserCore.class);
        reagentList.put(Minicio.ID, Lightning.class);
        reagentList.put(ThermalPylon.ID, LaserCore.class);
        reagentList.put(Zivicio.ID, Lightning.class);
    }
}
