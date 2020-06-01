package potionbrewer.util;

import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.*;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class SpireReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        // Exordium
        reagentList.put(AcidSlime_S.ID, Slime.class);
        reagentList.put(AcidSlime_M.ID, Slime.class);
        reagentList.put(AcidSlime_L.ID, Slime.class);
        reagentList.put(SpikeSlime_L.ID, Slime.class);
        reagentList.put(SpikeSlime_M.ID, Slime.class);
        reagentList.put(SpikeSlime_S.ID, Slime.class);
        reagentList.put(ApologySlime.ID, Slime.class);
        reagentList.put(Cultist.ID, Beak.class);
        reagentList.put(FungiBeast.ID, Spore.class);
        reagentList.put(GremlinFat.ID, Grimace.class);
        reagentList.put(GremlinThief.ID, Grimace.class);
        reagentList.put(GremlinTsundere.ID, Grimace.class);
        reagentList.put(GremlinWarrior.ID, Grimace.class);
        reagentList.put(GremlinWizard.ID, Grimace.class);
        reagentList.put(JawWorm.ID, Tooth.class);
        reagentList.put(Looter.ID, Gold.class);
        reagentList.put(LouseDefensive.ID, Silk.class);
        reagentList.put(LouseNormal.ID, Silk.class);
        reagentList.put(SlaverBlue.ID, Bone.class);
        reagentList.put(SlaverRed.ID, Bone.class);
        // elites
        reagentList.put(GremlinNob.ID, Bile.class);
        reagentList.put(Lagavulin.ID, Ink.class);
        reagentList.put(Sentry.ID, LaserCore.class);
        // bosses
        reagentList.put(Hexaghost.ID, RunicShape.class);
        reagentList.put(SlimeBoss.ID, TinyHat.class);
        reagentList.put(TheGuardian.ID, GuardianScales.class);

        // City
        reagentList.put(BanditBear.ID, Sulphur.class);
        reagentList.put(BanditLeader.ID, Wax.class);
        reagentList.put(BanditPointy.ID, Hand.class);
        reagentList.put(BronzeOrb.ID, Steel.class);
        reagentList.put(Byrd.ID, Pebble.class);
        reagentList.put(Centurion.ID, Bludgeon.class);
        reagentList.put(Chosen.ID, Feather.class);
        reagentList.put(Healer.ID, Ether.class);
        reagentList.put(Mugger.ID, Gold.class);
        reagentList.put(ShelledParasite.ID, Root.class);
        reagentList.put(SnakePlant.ID, Root.class);
        reagentList.put(Snecko.ID, Eye.class);
        reagentList.put(SphericGuardian.ID, Steel.class);
        // elites
        reagentList.put(BookOfStabbing.ID, Needle.class);
        reagentList.put(GremlinLeader.ID, Horn.class);
        reagentList.put(Taskmaster.ID, Skull.class);
        // bosses
        reagentList.put(BronzeAutomaton.ID, Mechanism.class);
        reagentList.put(Champ.ID, Crown.class);
        reagentList.put(TheCollector.ID, FeyFire.class);
        reagentList.put(TorchHead.ID, FeyFire.class);

        // Beyond
        reagentList.put(Darkling.ID, Ichor.class);
        reagentList.put(Exploder.ID, Flame.class);
        reagentList.put(Maw.ID, Jaw.class);
        reagentList.put(OrbWalker.ID, Flame.class);
        reagentList.put(Repulsor.ID, Lightning.class);
        reagentList.put(Spiker.ID, Barb.class);
        reagentList.put(SpireGrowth.ID, Tentacle.class);
        reagentList.put(Transient.ID, Meteorite.class);
        reagentList.put(WrithingMass.ID, Root.class);
        // elites
        reagentList.put(GiantHead.ID, Clay.class);
        reagentList.put(Nemesis.ID, Nethershroud.class);
        reagentList.put(Reptomancer.ID, SerpentSkull.class);
        reagentList.put(SnakeDagger.ID, Needle.class);
        // bosses
        reagentList.put(AwakenedOne.ID, RitualJar.class);
        reagentList.put(Deca.ID, Pyramid.class);
        reagentList.put(Donu.ID, Pyramid.class);
        reagentList.put(TimeEater.ID, Pocketwatch.class);

        // Ending
        // elites
        reagentList.put(SpireShield.ID, Tentacle.class);
        reagentList.put(SpireSpear.ID, Tentacle.class);
        // boss
        reagentList.put(CorruptHeart.ID, PhilosopherShard.class);
    }
}
