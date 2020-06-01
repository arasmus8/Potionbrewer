package potionbrewer.util;

import potionbrewer.orbs.*;
import theAct.monsters.*;

import java.util.HashMap;

public class JungleReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(BabySnecko.ID, Eye.class);
        reagentList.put(CarcassSack.ID, Root.class);
        reagentList.put(Cassacara.ID, Root.class);
        reagentList.put(Flameango.ID, Beak.class);
        reagentList.put(JungleHunters.ID, Bone.class);
        reagentList.put(Lyon.ID, Tooth.class);
        reagentList.put(SlimyTreeVines.ID, Root.class);
        reagentList.put(SneckoCultist.ID, Eye.class);
        reagentList.put(SneckoEgg.ID, Eye.class);
        reagentList.put(SwingingAxe.ID, Steel.class);
        //elites
        reagentList.put(GiantWrat.ID, Sulphur.class);
        reagentList.put(MamaSnecko.ID, Eye.class);
        reagentList.put(Phrog.ID, Ichor.class);
        //boss
        reagentList.put(FunGuy.ID, SuperSpore.class);
    }
}
