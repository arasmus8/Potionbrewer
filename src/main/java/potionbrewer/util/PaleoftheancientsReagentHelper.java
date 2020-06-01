package potionbrewer.util;

import paleoftheancients.bandit.monsters.TheBandit;
import paleoftheancients.bard.monsters.BardBoss;
import paleoftheancients.donudeca.monsters.DonuDeca;
import paleoftheancients.finarubossu.monsters.EyeOfDisremembrance;
import paleoftheancients.finarubossu.monsters.EyeOfRebirth;
import paleoftheancients.finarubossu.monsters.EyeOfSlumber;
import paleoftheancients.finarubossu.monsters.N;
import paleoftheancients.helpers.BASlime;
import paleoftheancients.ironcluck.monsters.IronCluck;
import paleoftheancients.reimu.monsters.Reimu;
import paleoftheancients.reimu.monsters.YinYangOrb;
import paleoftheancients.thedefect.monsters.TheDefectBoss;
import paleoftheancients.thedefect.monsters.orbs.Dark;
import paleoftheancients.thedefect.monsters.orbs.Frost;
import paleoftheancients.thedefect.monsters.orbs.Lightning;
import paleoftheancients.thedefect.monsters.orbs.Plasma;
import paleoftheancients.theshowman.monsters.ShowmanStage;
import paleoftheancients.theshowman.monsters.TheShowmanBoss;
import paleoftheancients.thesilent.monsters.TheSilentBoss;
import paleoftheancients.thevixen.monsters.TheVixenBoss;
import paleoftheancients.thorton.monsters.Thorton;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class PaleoftheancientsReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(DonuDeca.ID, Pyramid.class);
        reagentList.put(TheDefectBoss.ID, Mechanism.class);
        reagentList.put(Lightning.ID, Mechanism.class);
        reagentList.put(Frost.ID, Mechanism.class);
        reagentList.put(Dark.ID, Mechanism.class);
        reagentList.put(Plasma.ID, Mechanism.class);
        reagentList.put(TheVixenBoss.ID, FeyFire.class);
        reagentList.put(TheShowmanBoss.ID, RitualJar.class);
        reagentList.put(ShowmanStage.ID, RitualJar.class);
        reagentList.put(IronCluck.ID, RitualJar.class);
        reagentList.put(Reimu.ID, PhilosopherShard.class);
        reagentList.put(YinYangOrb.ID, PhilosopherShard.class);
        reagentList.put(BardBoss.ID, Pocketwatch.class);
        reagentList.put(TheSilentBoss.ID, Nethershroud.class);
        reagentList.put(Thorton.ID, Chest.class);
        reagentList.put(BASlime.ID, Slime.class);
        reagentList.put(N.ID, PhilosopherShard.class);
        reagentList.put(EyeOfSlumber.ID, PhilosopherShard.class);
        reagentList.put(EyeOfDisremembrance.ID, PhilosopherShard.class);
        reagentList.put(EyeOfRebirth.ID, PhilosopherShard.class);
        reagentList.put(TheBandit.ID, Chest.class);
    }
}
