package potionbrewer.util;

import conspire.monsters.*;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class ConspireReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(FuzzyDie.ID, RunicShape.class);
        reagentList.put(HeadLouse.ID, Horn.class);
        reagentList.put(HollyBat.ID, Root.class);
        reagentList.put(HypodermicNeedle.ID, Needle.class);
        reagentList.put(LouseWeak.ID, Silk.class);
        reagentList.put(MimicChest.ID, Chest.class);
        reagentList.put(MirrorImage.ID, Steel.class);
        reagentList.put(MysteriousRune.ID, RunicShape.class);
        reagentList.put(OrnateMirror.ID, Pyramid.class);
        reagentList.put(RoseBush.ID, Root.class);
        reagentList.put(SneckoGhost.ID, Eye.class);
    }
}
