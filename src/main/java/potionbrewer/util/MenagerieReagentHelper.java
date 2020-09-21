package potionbrewer.util;

import menagerie.monsters.bosses.*;
import menagerie.monsters.elites.FrozenSoldier;
import menagerie.monsters.elites.Hydra;
import menagerie.monsters.elites.MaskedSummoner;
import menagerie.monsters.elites.VoidReaper;
import menagerie.monsters.normals.*;
import menagerie.monsters.specials.GrandMagus;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class MenagerieReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(BeastMage.ID, Tooth.class);
        reagentList.put(DreadMoth.ID, Bug.class);
        reagentList.put(EntropyWarlock.ID, Ichor.class);
        reagentList.put(FeatherRabbit.ID, Feather.class);
        reagentList.put(GraftedWorm.ID, Slime.class);
        reagentList.put(Hexasnake.ID, Eye.class);
        reagentList.put(KeeperOfTheSacredPool.ID, PureWater.class);
        reagentList.put(MeltingSalamander.ID, Flame.class);
        reagentList.put(MonstrousExperiment.ID, Slime.class);
        reagentList.put(ProwlingAmalgam.ID, Bone.class);
        reagentList.put(RedMage.ID, Iodine.class);
        reagentList.put(SilkyRabbit.ID, Silk.class);
        reagentList.put(StygianBoar.ID, Grimace.class);
        reagentList.put(WhisperingWraith.ID, Saltpeter.class);
        reagentList.put(YoungSuneater.ID, Beak.class);
        reagentList.put(FrozenSoldier.ID, Grimace.class);
        reagentList.put(Hydra.ID, Ink.class);
        reagentList.put(MaskedSummoner.ID, LaserCore.class);
        reagentList.put(VoidReaper.ID, Hand.class);
        reagentList.put(AvatarOfCunning.ID, RunicShape.class);
        reagentList.put(AvatarOfVigor.ID, RunicShape.class);
        reagentList.put(AvatarOfWisdom.ID, RunicShape.class);
        reagentList.put(Chimera.ID, TinyHat.class);
        reagentList.put(Suneater.ID, GuardianScales.class);
        reagentList.put(GrandMagus.ID, Ether.class);
    }
}
