package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.FtueTip;
import potionbrewer.PotionbrewerMod;
import potionbrewer.PotionbrewerTipTracker;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.orbs.*;
import potionbrewer.vfx.CollectEffect;

import java.util.HashMap;

import static potionbrewer.PotionbrewerMod.makeCardPath;
import static potionbrewer.PotionbrewerTipTracker.REAGENTS;

public class Collect extends CustomCard {

    public static final String ID = PotionbrewerMod.makeID(Collect.class.getSimpleName());
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final TutorialStrings TUTORIAL_STRINGS = CardCrawlGame.languagePack.getTutorialString(REAGENTS);

    public static final String IMG = makeCardPath("Collect.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 0;

    private static final HashMap<String, Class<? extends Reagent>> monsterReagents = new HashMap<>();

    public Collect() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
        cardsToPreview = new Distill();
    }

    public static AbstractOrb getOrbForMonster(AbstractMonster m) {
        if (monsterReagents.containsKey(m.id)) {
            try {
                Class<? extends Reagent> clz = monsterReagents.get(m.id);
                return clz.newInstance();
            } catch (Exception d) {
                System.out.println("Problem with orb for monster! m=" + m);
            }
        }
        return PotionbrewerMod.reagentList.randomReagent();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new CollectEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY)));
        addToBot(new MakeTempCardInHandAction(new Distill()));
        AbstractOrb orb = getOrbForMonster(m);
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
        if (!PotionbrewerTipTracker.hasShown(REAGENTS)) {
            PotionbrewerTipTracker.neverShowAgain(REAGENTS);
            AbstractDungeon.ftue = new FtueTip(TUTORIAL_STRINGS.LABEL[0], TUTORIAL_STRINGS.TEXT[0], (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, FtueTip.TipType.COMBAT);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    static {
        // Exordium
        monsterReagents.put(AcidSlime_S.ID, Slime.class);
        monsterReagents.put(AcidSlime_M.ID, Slime.class);
        monsterReagents.put(AcidSlime_L.ID, Slime.class);
        monsterReagents.put(SpikeSlime_L.ID, Slime.class);
        monsterReagents.put(SpikeSlime_M.ID, Slime.class);
        monsterReagents.put(SpikeSlime_S.ID, Slime.class);
        monsterReagents.put(ApologySlime.ID, Slime.class);
        monsterReagents.put(Cultist.ID, Beak.class);
        monsterReagents.put(FungiBeast.ID, Spore.class);
        monsterReagents.put(GremlinFat.ID, Grimace.class);
        monsterReagents.put(GremlinThief.ID, Grimace.class);
        monsterReagents.put(GremlinTsundere.ID, Grimace.class);
        monsterReagents.put(GremlinWarrior.ID, Grimace.class);
        monsterReagents.put(GremlinWizard.ID, Grimace.class);
        monsterReagents.put(JawWorm.ID, Tooth.class);
        monsterReagents.put(Looter.ID, Gold.class);
        monsterReagents.put(LouseDefensive.ID, Silk.class);
        monsterReagents.put(LouseNormal.ID, Silk.class);
        monsterReagents.put(SlaverBlue.ID, Bone.class);
        monsterReagents.put(SlaverRed.ID, Bone.class);
        // elites
        monsterReagents.put(GremlinNob.ID, Bile.class);
        monsterReagents.put(Lagavulin.ID, Ink.class);
        monsterReagents.put(Sentry.ID, LaserCore.class);
        // bosses
        monsterReagents.put(Hexaghost.ID, RunicShape.class);
        monsterReagents.put(SlimeBoss.ID, TinyHat.class);
        monsterReagents.put(TheGuardian.ID, GuardianScales.class);

        // City
        monsterReagents.put(BanditBear.ID, Sulphur.class);
        monsterReagents.put(BanditLeader.ID, Wax.class);
        monsterReagents.put(BanditPointy.ID, Hand.class);
        monsterReagents.put(BronzeOrb.ID, Steel.class);
        monsterReagents.put(Byrd.ID, Pebble.class);
        monsterReagents.put(Centurion.ID, Bludgeon.class);
        monsterReagents.put(Chosen.ID, Feather.class);
        monsterReagents.put(Healer.ID, Ether.class);
        monsterReagents.put(Mugger.ID, Gold.class);
        monsterReagents.put(ShelledParasite.ID, Root.class);
        monsterReagents.put(SnakePlant.ID, Root.class);
        monsterReagents.put(Snecko.ID, Eye.class);
        monsterReagents.put(SphericGuardian.ID, Steel.class);
        // elites
        monsterReagents.put(BookOfStabbing.ID, Needle.class);
        monsterReagents.put(GremlinLeader.ID, Horn.class);
        monsterReagents.put(Taskmaster.ID, Skull.class);
        // bosses
        monsterReagents.put(BronzeAutomaton.ID, Mechanism.class);
        monsterReagents.put(Champ.ID, Crown.class);
        monsterReagents.put(TheCollector.ID, FeyFire.class);
        monsterReagents.put(TorchHead.ID, FeyFire.class);

        // Beyond
        monsterReagents.put(Darkling.ID, Ichor.class);
        monsterReagents.put(Exploder.ID, Flame.class);
        monsterReagents.put(Maw.ID, Jaw.class);
        monsterReagents.put(OrbWalker.ID, Flame.class);
        monsterReagents.put(Repulsor.ID, Lightning.class);
        monsterReagents.put(Spiker.ID, Barb.class);
        monsterReagents.put(SpireGrowth.ID, Tentacle.class);
        monsterReagents.put(Transient.ID, Meteorite.class);
        monsterReagents.put(WrithingMass.ID, Root.class);
        // elites
        monsterReagents.put(GiantHead.ID, Clay.class);
        monsterReagents.put(Nemesis.ID, Nethershroud.class);
        monsterReagents.put(Reptomancer.ID, SerpentSkull.class);
        monsterReagents.put(SnakeDagger.ID, Needle.class);
        // bosses
        monsterReagents.put(AwakenedOne.ID, RitualJar.class);
        monsterReagents.put(Deca.ID, Pyramid.class);
        monsterReagents.put(Donu.ID, Pyramid.class);
        monsterReagents.put(TimeEater.ID, Pocketwatch.class);

        // Ending
        // elites
        monsterReagents.put(SpireShield.ID, Tentacle.class);
        monsterReagents.put(SpireSpear.ID, Tentacle.class);
        // boss
        monsterReagents.put(CorruptHeart.ID, PhilosopherShard.class);


        // TheJungle
        monsterReagents.put("theJungle:BabySnecko", Eye.class);
        monsterReagents.put("theJungle:CarcassSack", Root.class);
        monsterReagents.put("theJungle:Cassacara", Root.class);
        monsterReagents.put("theJungle:Flameango", Beak.class);
        monsterReagents.put("theJungle:JungleHunters", Bone.class);
        monsterReagents.put("theJungle:Lyon", Tooth.class);
        monsterReagents.put("theJungle:SlimyTreeVines", Root.class);
        monsterReagents.put("theJungle:SneckoCultist", Eye.class);
        monsterReagents.put("theJungle:SneckoEgg", Eye.class);
        monsterReagents.put("theJungle:SwingingAxe", Steel.class);
        //elites
        monsterReagents.put("theJungle:GiantWrat", Sulphur.class);
        monsterReagents.put("theJungle:MamaSnecko", Eye.class);
        monsterReagents.put("theJungle:Phrog", Ichor.class);
        //boss
        monsterReagents.put("theJungle:FunGuy", SuperSpore.class);

        // TheFactory
        monsterReagents.put("theFactory:BigBot", Steel.class);
        monsterReagents.put("theFactory:DecayingSentinel", Steel.class);
        monsterReagents.put("theFactory:DefectiveSentry", Steel.class);
        monsterReagents.put("theFactory:Experiment01", Meteorite.class);
        monsterReagents.put("theFactory:ExpPersonnel", Slime.class);
        monsterReagents.put("theFactory:Manservantes", Steel.class);
        monsterReagents.put("theFactory:MiniBotBeamer", Flame.class);
        monsterReagents.put("theFactory:MiniBotBuilderBuilder", Lightning.class);
        monsterReagents.put("theFactory:MiniBotDebuff", Steel.class);
        monsterReagents.put("theFactory:MiniBotRepair", Barb.class);
        monsterReagents.put("theFactory:MiniBotVirus", Ether.class);
        monsterReagents.put("theFactory:SentinelSpawn", Silk.class);
        monsterReagents.put("theFactory:ShrapnelHeap", Barb.class);
        monsterReagents.put("theFactory:ShrapnelTosser", Barb.class);
        monsterReagents.put("theFactory:ToyOrb", Steel.class);
        //elites
        monsterReagents.put("theFactory:DrinkBrewer", Clay.class);
        monsterReagents.put("theFactory:Guardian2", GuardianScales.class);
        monsterReagents.put("theFactory:SmogElemental", Sulphur.class);
        //boss
        monsterReagents.put("theFactory:SPIDER", Pyramid.class);

        //ReplayTheSpire
        monsterReagents.put("Replay:BronzeOrb", Steel.class);
        monsterReagents.put("GremlinCook", Grimace.class);
        monsterReagents.put("Jyrdo", Beak.class);
        //elites
        monsterReagents.put("Replay:BlueRogue", Needle.class);
        monsterReagents.put("Replay:GhostMerchant", Chest.class);
        monsterReagents.put("R_Hoarder", Hand.class);
        monsterReagents.put("Replay:Snechameleon", Jaw.class);
        //boss
        monsterReagents.put("CaptainAbe", Chest.class);
        monsterReagents.put("FadingForestBoss", Storybook.class);
        monsterReagents.put("PondfishBoss", FeyFire.class);
        monsterReagents.put("Replay:Conductor", TrainTicket.class);
        monsterReagents.put("Replay:ConductorBomb", TrainTicket.class);
        monsterReagents.put("Replay:Hell Engine", TrainTicket.class);
        //Storyteller Summons
        monsterReagents.put("FF_ComboSlime_L", Slime.class);
        monsterReagents.put("FF_FungiBeast", Spore.class);
        monsterReagents.put("FF_GremlinFat", Grimace.class);
        monsterReagents.put("FF_GremlinThief", Grimace.class);
        monsterReagents.put("FF_GremlinTsundere", Grimace.class);
        monsterReagents.put("FF_GremlinWarrior", Grimace.class);
        monsterReagents.put("FF_GremlinWizard", Grimace.class);
        monsterReagents.put("FF_GremlinNob", Bile.class);
        monsterReagents.put("FF_JawWorm", Tooth.class);
        monsterReagents.put("FF_Lagavulin", Ink.class);
        monsterReagents.put("FF_LouseDefensive", Silk.class);
        monsterReagents.put("FF_LouseNormal", Silk.class);
        monsterReagents.put("FF_Sentry", LaserCore.class);

        //InfiniteSpire
        //elites
        monsterReagents.put("Nightmare", TwistedRelic.class);
        monsterReagents.put("infinitespire:Voidling", Ichor.class);
        //bosses
        monsterReagents.put("LordOfAnnihilation", PhilosopherShard.class);
        monsterReagents.put("LordOfDawn", PhilosopherShard.class);
        monsterReagents.put("LordOfFortification", PhilosopherShard.class);
        monsterReagents.put("infinitespire:MassOfShapes", Pyramid.class);

        //Hubris
        monsterReagents.put("hubris:GrandMystic", Ether.class);
        monsterReagents.put("hubris:GrandSnecko", Eye.class);
        monsterReagents.put("hubris:Merchant", Chest.class);
        monsterReagents.put("hubris:MusketHawk", Mechanism.class);
        monsterReagents.put("hubris:NecromanticTotem", Pyramid.class);

        //Conspire
        monsterReagents.put("conspire:FuzzyDie", RunicShape.class);
        monsterReagents.put("conspire:HeadLouse", Horn.class);
        monsterReagents.put("conspire:HollyBat", Root.class);
        monsterReagents.put("conspire:HypodermicNeedle", Needle.class);
        monsterReagents.put("FuzzyLouseWeak", Silk.class);
        monsterReagents.put("conspire:MimicChest", Chest.class);
        monsterReagents.put("conspire:MirrorImage", Steel.class);
        monsterReagents.put("conspire:MysteriousRune", RunicShape.class);
        monsterReagents.put("conspire:OrnateMirror", Pyramid.class);
        monsterReagents.put("conspire:RoseBush", Root.class);
        monsterReagents.put("conspire:SneckoGhost", Eye.class);

        //MimicMod
        monsterReagents.put("Mimic", Chest.class);

        //StrawberrySpire
        monsterReagents.put("strawberrySpire:Accio", Flame.class);
        monsterReagents.put("strawberrySpire:AncientClocktower", LaserCore.class);
        monsterReagents.put("strawberrySpire:Crucio", Flame.class);
        monsterReagents.put("strawberrySpire:ElectricalPylon", LaserCore.class);
        monsterReagents.put("strawberrySpire:Imperio", Steel.class);
        monsterReagents.put("strawberrySpire:KineticPylon", LaserCore.class);
        monsterReagents.put("strawberrySpire:Minicio", Lightning.class);
        monsterReagents.put("strawberrySpire:ThermalPylon", LaserCore.class);
        monsterReagents.put("strawberrySpire:Zivicio", Lightning.class);

        //BugKnight
        monsterReagents.put("HollowMod:MossKnight", Bug.class);
        monsterReagents.put("HollowMod:Nosk", Bug.class);
        monsterReagents.put("HollowMod:StalkingDevout", Bug.class);
        monsterReagents.put("HollowMod:HuskSentry", Bug.class);
        monsterReagents.put("HollowMod:HuskWarrior", Bug.class);
        monsterReagents.put("HollowMod:LittleWeaver", Bug.class);
        monsterReagents.put("HollowMod:SlobberingHusk", Bug.class);
        monsterReagents.put("HollowMod:ViolentHusk", Bug.class);
        monsterReagents.put("HollowMod:Zote", Bug.class);
        //Bosses
        monsterReagents.put("HollowMod:FalseKnight", CityCrest.class);
        monsterReagents.put("HollowMod:NKGrimm", Fireflies.class);
        monsterReagents.put("HollowMod:Radiance", Radiance.class);

        //Gensokyo
        monsterReagents.put("Gensokyo:Komachi", Meteorite.class);
        monsterReagents.put("Gensokyo:Yukari", Pyramid.class);
        monsterReagents.put("Gensokyo:Kokoro", Mechanism.class);
        monsterReagents.put("Gensokyo:Reimu", RunicShape.class);
        monsterReagents.put("Gensokyo:YinYangOrb", Steel.class);
        monsterReagents.put("Gensokyo:Aya", Bile.class);
        monsterReagents.put("Gensokyo:Cirno", Hand.class);
        monsterReagents.put("Gensokyo:SunflowerFairy", Bug.class);
        monsterReagents.put("Gensokyo:ZombieFairy", Bug.class);
        monsterReagents.put("Gensokyo:GreaterFairy", Bug.class);
        monsterReagents.put("Gensokyo:MaidFairy", Bug.class);
        monsterReagents.put("Gensokyo:Mamizou", RunicShape.class);
        monsterReagents.put("Gensokyo:RedKodama", Slime.class);
        monsterReagents.put("Gensokyo:GreyKodama", Slime.class);
        monsterReagents.put("Gensokyo:YellowKodama", Slime.class);
        monsterReagents.put("Gensokyo:WhiteKodama", Slime.class);
        monsterReagents.put("Gensokyo:VengefulSpirit", Bone.class);
        monsterReagents.put("Gensokyo:LivingMonolith", LaserCore.class);
        monsterReagents.put("Gensokyo:CorruptedTreant", Root.class);
        monsterReagents.put("Gensokyo:Python", Tentacle.class);
        monsterReagents.put("Gensokyo:Gryphon", Slime.class);
        monsterReagents.put("Gensokyo:MoonRabbit", Slime.class);
        monsterReagents.put("Gensokyo:Kitsune", Slime.class);
        monsterReagents.put("Gensokyo:Patchouli", Slime.class);
        monsterReagents.put("Gensokyo:FireOrb", Flame.class);
        monsterReagents.put("Gensokyo:WaterOrb", Slime.class);
        monsterReagents.put("Gensokyo:WoodOrb", Root.class);
        monsterReagents.put("Gensokyo:MetalOrb", Steel.class);
        monsterReagents.put("Gensokyo:EarthOrb", Lightning.class);
        monsterReagents.put("Gensokyo:Sumireko", Feather.class);
        monsterReagents.put("Gensokyo:Yuyuko", RunicShape.class);
        monsterReagents.put("Gensokyo:Eiki", TinyHat.class);


        //Wandering Minibosses
        monsterReagents.put("wanderingMiniBosses:GazeMonster", Meteorite.class);
        monsterReagents.put("wanderingMiniBosses:GremlinKnight", Horn.class);
        monsterReagents.put("wanderingMiniBosses:ImmortalFlame", FeyFire.class);
        monsterReagents.put("wanderingMiniBosses:EternalPrincess", Radiance.class);
        monsterReagents.put("wanderingMiniBosses:Wraith", Meteorite.class);
        monsterReagents.put("wanderingMiniBosses:InkMan", Ink.class);
        monsterReagents.put("wanderingMiniBosses:BanditKing", Skull.class);
        monsterReagents.put("wanderingMiniBosses:ThiefOfABillionGuards", Chest.class);
        monsterReagents.put("wanderingMiniBosses:Timic", Chest.class);

        //Pale of the Ancients
        monsterReagents.put("paleoftheancients:DonuDeca", Pyramid.class);
        monsterReagents.put("paleoftheancients:TheDefect", Mechanism.class);
        monsterReagents.put("paleoftheancients:lightningorb", Mechanism.class);
        monsterReagents.put("paleoftheancients:frostorb", Mechanism.class);
        monsterReagents.put("paleoftheancients:darkorb", Mechanism.class);
        monsterReagents.put("paleoftheancients:plasmaorb", Mechanism.class);
        monsterReagents.put("paleoftheancients:TheVixenBoss", FeyFire.class);
        monsterReagents.put("paleoftheancients:TheShowmanBoss", RitualJar.class);
        monsterReagents.put("paleoftheancients:ShowmanStage", RitualJar.class);
        monsterReagents.put("paleoftheancients:IronCluck", RitualJar.class);
        monsterReagents.put("paleoftheancients:Reimu", PhilosopherShard.class);
        monsterReagents.put("paleoftheancients:YinYangOrb", PhilosopherShard.class);
        monsterReagents.put("paleoftheancients:BardBoss", Pocketwatch.class);
        monsterReagents.put("paleoftheancients:TheSilent", Nethershroud.class);
        monsterReagents.put("paleoftheancients:Thorton", Chest.class);
        monsterReagents.put("paleoftheancients:BASlime", Slime.class);
        monsterReagents.put("paleoftheancients:N", PhilosopherShard.class);
        monsterReagents.put("paleoftheancients:EyeOfSlumber", PhilosopherShard.class);
        monsterReagents.put("paleoftheancients:EyeOfDisremembrance", PhilosopherShard.class);
        monsterReagents.put("paleoftheancients:EyeOfRebirth", PhilosopherShard.class);
        monsterReagents.put("paleoftheancients:TheBandit", CityCrest.class);

    }
}