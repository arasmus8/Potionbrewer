package potionbrewer.util;

import elementarium.monsters.bosses.FirePhoenix;
import elementarium.monsters.bosses.GoldenDragon;
import elementarium.monsters.bosses.GolemEmperor;
import elementarium.monsters.bosses.IcePhoenix;
import elementarium.monsters.elites.ElementalPortal;
import elementarium.monsters.elites.Firelord;
import elementarium.monsters.elites.FlameHerald;
import elementarium.monsters.elites.WarGolem;
import elementarium.monsters.normals.*;
import elementarium.monsters.specials.GoldenAngel;
import elementarium.monsters.specials.GoldenEagle;
import elementarium.monsters.specials.GoldenLion;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class ElementariumReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(FirePhoenix.ID, FeyFire.class);
        reagentList.put(GoldenDragon.ID, Mechanism.class);
        reagentList.put(GolemEmperor.ID, Crown.class);
        reagentList.put(IcePhoenix.ID, FeyFire.class);
        reagentList.put(ElementalPortal.ID, Ink.class);
        reagentList.put(Firelord.ID, Horn.class);
        reagentList.put(FlameHerald.ID, Needle.class);
        reagentList.put(WarGolem.ID, Skull.class);
        reagentList.put(Cyclone.ID, Ether.class);
        reagentList.put(Hydrostalker.ID, Feather.class);
        reagentList.put(LivingStormcloud.ID, Ether.class);
        reagentList.put(MudGolem.ID, Root.class);
        reagentList.put(OpulentOffering.ID, Gold.class);
        reagentList.put(OrbOfFire.ID, Flame.class);
        reagentList.put(RubyGolem.ID, Pebble.class);
        reagentList.put(ShimmeringMirage.ID, Ether.class);
        reagentList.put(StoneGolem.ID, Steel.class);
        reagentList.put(TarGolem.ID, Eye.class);
        reagentList.put(VoidBeast.ID, Ether.class);
        reagentList.put(VoidCorruption.ID, Ether.class);
        reagentList.put(GoldenAngel.ID, Gold.class);
        reagentList.put(GoldenEagle.ID, Gold.class);
        reagentList.put(GoldenLion.ID, Gold.class);
    }
}
