package potionbrewer.util;

import potionbrewer.orbs.*;
import wanderingMiniBosses.monsters.banditking.BanditKing;
import wanderingMiniBosses.monsters.eternalPrincess.EternalPrincess;
import wanderingMiniBosses.monsters.eternalPrincess.Wraith;
import wanderingMiniBosses.monsters.gazemonster.GazeMonster;
import wanderingMiniBosses.monsters.gremlinknight.GremlinKnight;
import wanderingMiniBosses.monsters.immortalflame.ImmortalFlame;
import wanderingMiniBosses.monsters.inkman.InkMan;
import wanderingMiniBosses.monsters.thiefOfABillion.ThiefOfABillionGuards;
import wanderingMiniBosses.monsters.timic.Timic;

import java.util.HashMap;

public class WanderingMinibossesReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(GazeMonster.ID, Meteorite.class);
        reagentList.put(GremlinKnight.ID, Horn.class);
        reagentList.put(ImmortalFlame.ID, FeyFire.class);
        reagentList.put(EternalPrincess.ID, Radiance.class);
        reagentList.put(Wraith.ID, Meteorite.class);
        reagentList.put(InkMan.ID, Ink.class);
        reagentList.put(BanditKing.ID, Skull.class);
        reagentList.put(ThiefOfABillionGuards.ID, Chest.class);
        reagentList.put(Timic.ID, Chest.class);
    }
}
