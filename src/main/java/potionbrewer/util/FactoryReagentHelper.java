package potionbrewer.util;

import myAct.monsters.*;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class FactoryReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(BigBot.ID, Steel.class);
        reagentList.put(DecayingSentinel.ID, Steel.class);
        reagentList.put(DefectiveSentry.ID, Steel.class);
        reagentList.put(Experiment01.ID, Meteorite.class);
        reagentList.put(ExpPersonnel.ID, Slime.class);
        reagentList.put(Manservantes.ID, Steel.class);
        reagentList.put(MiniBotBeamer.ID, Flame.class);
        reagentList.put(MiniBotBuilderBuilder.ID, Lightning.class);
        reagentList.put(MiniBotDebuff.ID, Steel.class);
        reagentList.put(MiniBotRepair.ID, Barb.class);
        reagentList.put(MiniBotVirus.ID, Ether.class);
        reagentList.put(SentinelSpawn.ID, Silk.class);
        reagentList.put(ShrapnelHeap.ID, Barb.class);
        reagentList.put(ShrapnelTosser.ID, Barb.class);
        reagentList.put(ToyOrb.ID, Steel.class);
        //elites
        reagentList.put(DrinkBrewer.ID, Clay.class);
        reagentList.put(Guardian2.ID, GuardianScales.class);
        reagentList.put(SmogElemental.ID, Sulphur.class);
        //boss
        reagentList.put(SPIDER.ID, Pyramid.class);
    }
}
