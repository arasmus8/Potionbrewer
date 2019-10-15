package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.orbs.*;

import java.util.HashMap;

import static potionbrewer.PotionbrewerMod.makeCardPath;

public class Collect extends AbstractDynamicCard {
    
    public static final String ID = PotionbrewerMod.makeID(Collect.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("ReagentCard.png");
    
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    
    private static final int COST = 0;
    private static final int VULNERABLE = 1;
    private static final int UPGRADE_VULNERABLE = 2;

    private static final HashMap<String, Class<? extends Reagent>> monsterReagents = new HashMap<>();

    public Collect() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = VULNERABLE;
        this.magicNumber = VULNERABLE;
        exhaust = true;
    }

    public AbstractOrb getOrbForMonster(AbstractMonster m) {
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
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
        AbstractOrb orb = getOrbForMonster(m);
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_VULNERABLE);
            this.initializeDescription();
        }
    }

    static {
        // Exordium
        monsterReagents.put(AcidSlime_S.ID, Ichor.class);
        monsterReagents.put(AcidSlime_M.ID, Ichor.class);
        monsterReagents.put(AcidSlime_L.ID, Ichor.class);
        monsterReagents.put(SpikeSlime_L.ID, Ichor.class);
        monsterReagents.put(SpikeSlime_M.ID, Ichor.class);
        monsterReagents.put(SpikeSlime_S.ID, Ichor.class);
        monsterReagents.put(ApologySlime.ID, Ichor.class);
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
        monsterReagents.put(Lagavulin.ID, Eye.class);
        monsterReagents.put(Sentry.ID, Steel.class);
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
        monsterReagents.put(Centurion.ID, Steel.class);
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
        monsterReagents.put(Maw.ID, Tooth.class);
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
        monsterReagents.put(AwakenedOne.ID, Feather.class);
        monsterReagents.put(Deca.ID, Mechanism.class);
        monsterReagents.put(Donu.ID, RunicShape.class);
        monsterReagents.put(TimeEater.ID, Tentacle.class);

        // Ending
        monsterReagents.put(Eyes.ID, Eye.class);
        // elites
        monsterReagents.put(SpireShield.ID, Tentacle.class);
        monsterReagents.put(SpireSpear.ID, Tentacle.class);
        // boss
        monsterReagents.put(CorruptHeart.ID, PhilosopherShard.class);
    }
}