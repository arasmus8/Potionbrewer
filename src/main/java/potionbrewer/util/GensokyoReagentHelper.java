package potionbrewer.util;

import Gensokyo.monsters.act1.*;
import Gensokyo.monsters.act1.NormalEnemies.*;
import Gensokyo.monsters.act1.marisaMonsters.*;
import Gensokyo.monsters.act2.Eiki;
import Gensokyo.monsters.act2.Yuyuko;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class GensokyoReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        reagentList.put(Komachi.ID, Meteorite.class);
        reagentList.put(Yukari.ID, Pyramid.class);
        reagentList.put(Kokoro.ID, Mechanism.class);
        reagentList.put(Reimu.ID, RunicShape.class);
        reagentList.put(YinYangOrb.ID, Steel.class);
        reagentList.put(Aya.ID, Bile.class);
        reagentList.put(Cirno.ID, Hand.class);
        reagentList.put(SunflowerFairy.ID, Bug.class);
        reagentList.put(ZombieFairy.ID, Bug.class);
        reagentList.put(GreaterFairy.ID, Bug.class);
        reagentList.put(MaidFairyNormal.ID, Bug.class);
        reagentList.put(Mamizou.ID, RunicShape.class);
        reagentList.put(RedKodama.ID, Slime.class);
        reagentList.put(GreyKodama.ID, Slime.class);
        reagentList.put(YellowKodama.ID, Slime.class);
        reagentList.put(WhiteKodama.ID, Slime.class);
        reagentList.put(VengefulSpirit.ID, Bone.class);
        reagentList.put(LivingMonolith.ID, LaserCore.class);
        reagentList.put(CorruptedTreant.ID, Root.class);
        reagentList.put(Python.ID, Tentacle.class);
        reagentList.put(Gryphon.ID, Slime.class);
        reagentList.put(MoonRabbit.ID, Slime.class);
        reagentList.put(Kitsune.ID, Slime.class);
        reagentList.put(Patchouli.ID, Slime.class);
        reagentList.put(FireOrb.ID, Flame.class);
        reagentList.put(WaterOrb.ID, Slime.class);
        reagentList.put(WoodOrb.ID, Root.class);
        reagentList.put(MetalOrb.ID, Steel.class);
        reagentList.put(EarthOrb.ID, Lightning.class);
        reagentList.put(Sumireko.ID, Feather.class);
        reagentList.put(Yuyuko.ID, RunicShape.class);
        reagentList.put(Eiki.ID, TinyHat.class);
    }
}
