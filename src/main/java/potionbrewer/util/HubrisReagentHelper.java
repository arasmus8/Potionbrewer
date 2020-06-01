package potionbrewer.util;

import com.evacipated.cardcrawl.mod.hubris.monsters.*;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class HubrisReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(GrandMystic.ID, Ether.class);
        reagentList.put(GrandSnecko.ID, Eye.class);
        reagentList.put(MerchantMonster.ID, Chest.class);
        reagentList.put(MusketHawk.ID, Mechanism.class);
        reagentList.put(NecromanticTotem.ID, Pyramid.class);
    }
}
