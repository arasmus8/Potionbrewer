package potionbrewer.util;

import Gensokyo.monsters.act1.*;
import Gensokyo.monsters.act1.NormalEnemies.*;
import Gensokyo.monsters.act1.marisaMonsters.*;
import Gensokyo.monsters.act2.*;
import Gensokyo.monsters.act2.NormalEnemies.*;
import Gensokyo.monsters.act3.*;
import Gensokyo.monsters.act3.NormalEnemies.*;
import Gensokyo.monsters.act3.Shinki.*;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class GensokyoReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        // act 1
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
        // act 2
        reagentList.put(Eiki.ID, TinyHat.class);
        reagentList.put(AngelMirror.ID, Steel.class);
        reagentList.put(BigMudSlime.ID, Ichor.class);
        reagentList.put(Chomper.ID, Sulphur.class);
        reagentList.put(CosmicMonolith.ID, Steel.class);
        reagentList.put(Gloop.ID, Slime.class);
        reagentList.put(SlimeBunny.ID, Slime.class);
        reagentList.put(Swordslinger.ID, Bludgeon.class);
        reagentList.put(TanukiDog.ID, Feather.class);
        reagentList.put(Wraith.ID, Ether.class);
        reagentList.put(Byakuren.ID, FeyFire.class);
        reagentList.put(Kaguya.ID, Mechanism.class);
        reagentList.put(Koishi.ID, Skull.class);
        reagentList.put(Kune.ID, Eye.class);
        reagentList.put(Miko.ID, Crown.class);
        reagentList.put(Mirror.ID, TinyHat.class);
        reagentList.put(Reisen.ID, Pebble.class);
        reagentList.put(Tenshi.ID, Horn.class);
        // act 3
        reagentList.put(AncientGuardian.ID, Flame.class);
        reagentList.put(AtlasGolem.ID, Lightning.class);
        reagentList.put(Dawnsword.ID, Jaw.class);
        reagentList.put(Duskaxe.ID, Barb.class);
        reagentList.put(FeralBat.ID, Jaw.class);
        reagentList.put(LoudBat.ID, Bug.class);
        reagentList.put(MadBoulder.ID, Steel.class);
        reagentList.put(Rafflesia.ID, Root.class);
        reagentList.put(SeedOfUnknown.ID, Ichor.class);
        reagentList.put(Sharpion.ID, Barb.class);
        reagentList.put(VampireBat.ID, Feather.class);
        reagentList.put(Alice.ID, Pyramid.class);
        reagentList.put(AliceEvent1.ID, Pyramid.class);
        reagentList.put(AliceEvent2.ID, Pyramid.class);
        reagentList.put(AliceEvent3.ID, Pyramid.class);
        reagentList.put(Doll.ID, Pyramid.class);
        reagentList.put(Sariel.ID, Pocketwatch.class);
        reagentList.put(Shinki.ID, Pocketwatch.class);
        reagentList.put(SarielEvent1.ID, Pocketwatch.class);
        reagentList.put(SarielEvent2.ID, Pocketwatch.class);
        reagentList.put(SarielEvent3.ID, Pocketwatch.class);
        reagentList.put(Yumeko.ID, RitualJar.class);
        reagentList.put(YumekoEvent1.ID, RitualJar.class);
        reagentList.put(YumekoEvent2.ID, RitualJar.class);
        reagentList.put(YumekoEvent3.ID, RitualJar.class);
        reagentList.put(Yuyuko.ID, Clay.class);
        reagentList.put(BlueSoul.ID, Clay.class);
        reagentList.put(PurpleSoul.ID, Clay.class);
        reagentList.put(Doremy.ID, Nethershroud.class);
        reagentList.put(Flandre.ID, Pyramid.class);
        reagentList.put(Kasen.ID, SerpentSkull.class);
        reagentList.put(Kume.ID, Tentacle.class);
        reagentList.put(Marisa.ID, Meteorite.class);
        reagentList.put(Mokou.ID, RitualJar.class);
        reagentList.put(Remilia.ID, Pyramid.class);
    }
}
