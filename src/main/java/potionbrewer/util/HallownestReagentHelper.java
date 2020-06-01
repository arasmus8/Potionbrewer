package potionbrewer.util;

import Hallownest.monsters.CityofTearsEnemies.*;
import Hallownest.monsters.GreenpathEnemies.*;
import Hallownest.monsters.KingdomsEdgeEnemies.*;
import potionbrewer.orbs.*;

import java.util.HashMap;

public class HallownestReagentHelper {
    public static void setupReagentsForCollect(
            HashMap<String, Class<? extends Reagent>> reagentList
    ) {
        //Greenpath
        reagentList.put(BossBrokenVessel.ID, RunicShape.class);
        reagentList.put(BossFalseKnight.ID, CityCrest.class);
        reagentList.put(BossHornet.ID, GuardianScales.class);
        reagentList.put(eliteCrystalGuardian.ID, Ink.class);
        reagentList.put(eliteMossKnight.ID, Bile.class);
        reagentList.put(eliteVengeflyKing.ID, LaserCore.class);
        reagentList.put(minionBabyAspid.ID, Saltpeter.class);
        reagentList.put(minionBlob.ID, Slime.class);
        reagentList.put(minionFly.ID, Iodine.class);
        reagentList.put(monsterAspidMother.ID, Spore.class);
        reagentList.put(monsterBaldur.ID, Silk.class);
        reagentList.put(monsterCrawlid.ID, Beak.class);
        reagentList.put(monsterFoolEater.ID, Tooth.class);
        reagentList.put(monsterHuskGuard.ID, Jaw.class);
        reagentList.put(monsterHuskWarrior.ID, Bone.class);
        reagentList.put(monsterMossCharger.ID, Root.class);
        reagentList.put(monsterMossCreep.ID, Grimace.class);
        reagentList.put(monsterMosskin.ID, Spore.class);
        reagentList.put(monsterOoma.ID, Slime.class);
        reagentList.put(monsterTikTik.ID, Bug.class);
        reagentList.put(monsterUuma.ID, Slime.class);
        //CityOfTears
        reagentList.put(BossGrimm.ID, Fireflies.class);
        reagentList.put(BossMantisLord.ID, Crown.class);
        reagentList.put(BossSoulMaster.ID, FeyFire.class);
        reagentList.put(eliteNosk.ID, Skull.class);
        reagentList.put(eliteSoulWarrior.ID, Needle.class);
        reagentList.put(eliteWatcherKnight.ID, Horn.class);
        reagentList.put(monsterCowardlyHusk.ID, Hand.class);
        reagentList.put(monsterFlukebot.ID, Wax.class);
        reagentList.put(monsterFlukemon.ID, Wax.class);
        reagentList.put(monsterFluketop.ID, Wax.class);
        reagentList.put(monsterGluttonousHusk.ID, Sulphur.class);
        reagentList.put(monsterGrimmkinMaster.ID, Flame.class);
        reagentList.put(monsterGrimmkinNovice.ID, Feather.class);
        reagentList.put(monsterHuskSentry.ID, Wax.class);
        reagentList.put(monsterMantisWarrior.ID, Bludgeon.class);
        reagentList.put(monsterMantisYouth.ID, Pebble.class);
        reagentList.put(monsterMistake.ID, Ether.class);
        reagentList.put(monsterMisterMushroom.ID, Spore.class);
        reagentList.put(monsterShrumalOgre.ID, SuperSpore.class);
        //Kingdom'sEdge
        reagentList.put(BossGreyPrinceZote.ID, RitualJar.class);
        reagentList.put(BossHollowKnight.ID, Pyramid.class);
        reagentList.put(BossRadiance.ID, Radiance.class);
        reagentList.put(eliteCollector.ID, Nethershroud.class);
        reagentList.put(eliteHiveKnight.ID, SerpentSkull.class);
        reagentList.put(eliteStalkingDevout.ID, Clay.class);
        reagentList.put(EventZote.ID, Lightning.class);
        reagentList.put(minionHoppingZoteling.ID, Saltpeter.class);
        reagentList.put(minionVolatileZoteling.ID, Saltpeter.class);
        reagentList.put(minionWingedZoteling.ID, Saltpeter.class);
        reagentList.put(monsterElderHu.ID, Root.class);
        reagentList.put(monsterGreatHopper.ID, Tentacle.class);
        reagentList.put(monsterHeavyFool.ID, Barb.class);
        reagentList.put(monsterHiveGuardian.ID, Meteorite.class);
        reagentList.put(monsterHiveSoldier.ID, Needle.class);
        reagentList.put(monsterHornedHusk.ID, Barb.class);
        reagentList.put(monsterKingsmould.ID, Ether.class);
        reagentList.put(monsterLittleHopper.ID, Bug.class);
        reagentList.put(monsterLittleWeaver.ID, Bug.class);
        reagentList.put(monsterMarmu.ID, Root.class);
        reagentList.put(monsterPrimalAspid.ID, Ichor.class);
        reagentList.put(monsterShieldedFool.ID, Steel.class);
        reagentList.put(monsterSibling.ID, Ichor.class);
        reagentList.put(monsterSlobberingHusk.ID, Slime.class);
        reagentList.put(monsterSturdyFool.ID, Steel.class);
        reagentList.put(monsterViolentHusk.ID, Flame.class);
        reagentList.put(monsterWanderingHusk.ID, Barb.class);
        reagentList.put(monsterWingmould.ID, Lightning.class);
        reagentList.put(monsterXero.ID, Tentacle.class);
    }
}
