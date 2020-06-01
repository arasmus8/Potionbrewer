package potionbrewer.util;

import com.megacrit.cardcrawl.mod.replay.monsters.replay.*;
import com.megacrit.cardcrawl.mod.replay.monsters.replay.eastereggs.BlueRogue;
import com.megacrit.cardcrawl.mod.replay.monsters.replay.eastereggs.GhostMerchant;
import com.megacrit.cardcrawl.mod.replay.monsters.replay.eastereggs.R_Hoarder;
import com.megacrit.cardcrawl.mod.replay.monsters.replay.fadingForest.*;
import com.megacrit.cardcrawl.mod.replay.monsters.replay.hec.Conductor;
import com.megacrit.cardcrawl.mod.replay.monsters.replay.hec.Dynamite;
import com.megacrit.cardcrawl.mod.replay.monsters.replay.hec.HellsEngine;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class ReplayReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(BronzeOrb.ID, Steel.class);
        reagentList.put(GremlinCook.ID, Grimace.class);
        reagentList.put(Jyrdo.ID, Beak.class);
        //elites
        reagentList.put(BlueRogue.ID, Needle.class);
        reagentList.put(GhostMerchant.ID, Chest.class);
        reagentList.put(R_Hoarder.ID, Hand.class);
        reagentList.put(Snechameleon.ID, Jaw.class);
        //boss
        reagentList.put(CaptainAbe.ID, Chest.class);
        reagentList.put(FadingForestBoss.ID, Storybook.class);
        reagentList.put(PondfishBoss.ID, FeyFire.class);
        reagentList.put(Conductor.ID, TrainTicket.class);
        reagentList.put(Dynamite.ID, TrainTicket.class);
        reagentList.put(HellsEngine.ID, TrainTicket.class);
        //Storyteller Summons
        reagentList.put(FF_ComboSlime_L.ID, Slime.class);
        reagentList.put(FF_FungiBeast.ID, Spore.class);
        reagentList.put(FF_GremlinFat.ID, Grimace.class);
        reagentList.put(FF_GremlinThief.ID, Grimace.class);
        reagentList.put(FF_GremlinTsundere.ID, Grimace.class);
        reagentList.put(FF_GremlinWarrior.ID, Grimace.class);
        reagentList.put(FF_GremlinWizard.ID, Grimace.class);
        reagentList.put(FF_GremlinNob.ID, Bile.class);
        reagentList.put(FF_JawWorm.ID, Tooth.class);
        reagentList.put(FF_Lagavulin.ID, Ink.class);
        reagentList.put(FF_LouseDefensive.ID, Silk.class);
        reagentList.put(FF_LouseNormal.ID, Silk.class);
        reagentList.put(FF_Sentry.ID, LaserCore.class);
    }
}
