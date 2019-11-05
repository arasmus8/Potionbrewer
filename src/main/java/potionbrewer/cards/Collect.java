package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.Eyes;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.orbs.*;

import java.util.HashMap;

import static potionbrewer.PotionbrewerMod.makeCardPath;

public class Collect extends CustomCard {
    
    public static final String ID = PotionbrewerMod.makeID(Collect.class.getSimpleName());
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("ReagentCard.png");
    
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    
    private static final int COST = 0;
    private static final int VULNERABLE = 1;
    private static final int UPGRADE_VULNERABLE = 1;

    private static final HashMap<String, Class<? extends Reagent>> monsterReagents = new HashMap<>();

    public Collect() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = VULNERABLE;
        this.magicNumber = VULNERABLE;
        exhaust = true;
        cardsToPreview = new Distill();
    }

    public static AbstractOrb getOrbForMonster(AbstractMonster m) {
        if(monsterReagents.containsKey(m.id)) {
            try {
                Class<? extends Reagent> clz = monsterReagents.get(m.id);
                return clz.newInstance();
            } catch (Exception d) {
                System.out.println("Problem with orb for monster! m=" + m);
            }
        }
        return ReagentList.randomReagent();
    }

    /*
    @Override
    public void render(SpriteBatch sb) {
        if(AbstractDungeon.player != null) {
            AbstractMonster m = (AbstractMonster) ReflectionHacks.getPrivate(AbstractDungeon.player, AbstractPlayer.class, "hoveredMonster");
            if (m != null) {
                Reagent r = (Reagent) getOrbForMonster(m);
                TipHelper.renderGenericTip(m.hb.x, m.hb.y, "Collect: " + r.name, r.description);
            }
        }
        super.render(sb);
    }
    */

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new Distill()));
        AbstractOrb orb = getOrbForMonster(m);
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_VULNERABLE);
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
        monsterReagents.put(Cultist.ID, Feather.class);
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
        monsterReagents.put(TheGuardian.ID, PowerCore.class);

        // City
        monsterReagents.put(BanditBear.ID, Sulphur.class);
        monsterReagents.put(BanditLeader.ID, Wax.class);
        monsterReagents.put(BanditPointy.ID, Hand.class);
        monsterReagents.put(BronzeOrb.ID, Steel.class);
        monsterReagents.put(Byrd.ID, Feather.class);
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
        monsterReagents.put(Eyes.ID, Eye.class);
        // elites
        monsterReagents.put(SpireShield.ID, Tentacle.class);
        monsterReagents.put(SpireSpear.ID, Tentacle.class);
        // boss
        monsterReagents.put(CorruptHeart.ID, PhilosopherShard.class);


        // TheJungle
        monsterReagents.put("theJungle:BabySnecko", Eye.class);
        monsterReagents.put("theJungle:CarcassSack", Root.class);
        monsterReagents.put("theJungle:Cassacara", Root.class);
        monsterReagents.put("theJungle:Flameango", Feather.class);
        monsterReagents.put("theJungle:FunGuy", Spore.class);
        monsterReagents.put("theJungle:GiantWrat", Sulphur.class);
        monsterReagents.put("theJungle:JungleHunters", Bone.class);
        monsterReagents.put("theJungle:Lyon", Tooth.class);
        monsterReagents.put("theJungle:MamaSnecko", Eye.class);
        monsterReagents.put("theJungle:Phrog", Slime.class);
        monsterReagents.put("theJungle:SlimyTreeVines", Root.class);
        monsterReagents.put("theJungle:SneckoCultist", Eye.class);
        monsterReagents.put("theJungle:SneckoEgg", Eye.class);
        monsterReagents.put("theJungle:SwingingAxe", Steel.class);

        // TheFactory
        monsterReagents.put("theFactory:BigBot", Steel.class);
        monsterReagents.put("theFactory:DecayingSentinel", Steel.class);
        monsterReagents.put("theFactory:DefectiveSentry", Steel.class);
        monsterReagents.put("theFactory:DrinkBrewer", Steel.class);
        monsterReagents.put("theFactory:Experiment01", Meteorite.class);
        monsterReagents.put("theFactory:ExpPersonnel", Slime.class);
        monsterReagents.put("theFactory:Guardian2", PowerCore.class);
        monsterReagents.put("theFactory:Manservantes", Steel.class);
        monsterReagents.put("theFactory:MiniBotBeamer", Flame.class);
        monsterReagents.put("theFactory:MiniBotBuilderBuilder", Lightning.class);
        monsterReagents.put("theFactory:MiniBotDebuff", Steel.class);
        monsterReagents.put("theFactory:MiniBotRepair", Barb.class);
        monsterReagents.put("theFactory:MiniBotVirus", Ether.class);
        monsterReagents.put("theFactory:SentinelSpawn", Silk.class);
        monsterReagents.put("theFactory:ShrapnelHeap", Barb.class);
        monsterReagents.put("theFactory:ShrapnelTosser", Barb.class);
        monsterReagents.put("theFactory:SmogElemental", Sulphur.class);
        monsterReagents.put("theFactory:SPIDER", Silk.class);
        monsterReagents.put("theFactory:ToyOrb", Steel.class);

    }
}