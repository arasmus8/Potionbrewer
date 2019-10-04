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
import com.megacrit.cardcrawl.monsters.ending.*;
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
    
    public static final String IMG = makeCardPath("Skill.png");
    
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    
    private static final int COST = 0;
    private static final int VULNERABLE = 1;
    private static final int UPGRADE_VULNERABLE = 2;

    private static HashMap<String, Reagent> monsterReagents = new HashMap<>();

    public Collect() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = VULNERABLE;
        this.magicNumber = VULNERABLE;
    }

    public AbstractOrb getOrbForMonster(AbstractMonster m) {
        if(monsterReagents.containsKey(m.id)) {
            return monsterReagents.get(m.id);
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
        monsterReagents.put(AcidSlime_S.ID, new Ichor());
        monsterReagents.put(AcidSlime_M.ID, new Ichor());
        monsterReagents.put(AcidSlime_L.ID, new Ichor());
        monsterReagents.put(SpikeSlime_L.ID, new Ichor());
        monsterReagents.put(SpikeSlime_M.ID, new Ichor());
        monsterReagents.put(SpikeSlime_S.ID, new Ichor());
        monsterReagents.put(ApologySlime.ID, new Ichor());
        monsterReagents.put(Cultist.ID, new Feather());
        monsterReagents.put(FungiBeast.ID, new Spore());
        monsterReagents.put(GremlinFat.ID, new Grimace());
        monsterReagents.put(GremlinThief.ID, new Grimace());
        monsterReagents.put(GremlinTsundere.ID, new Grimace());
        monsterReagents.put(GremlinWarrior.ID, new Grimace());
        monsterReagents.put(GremlinWizard.ID, new Grimace());
        monsterReagents.put(JawWorm.ID, new Tooth());
        monsterReagents.put(Looter.ID, new Gold());
        monsterReagents.put(LouseDefensive.ID, new Silk());
        monsterReagents.put(LouseNormal.ID, new Silk());
        monsterReagents.put(SlaverBlue.ID, new Bone());
        monsterReagents.put(SlaverRed.ID, new Bone());
        // elites
        monsterReagents.put(GremlinNob.ID, new Bile());
        monsterReagents.put(Lagavulin.ID, new Eye());
        monsterReagents.put(Sentry.ID, new Steel());
        // bosses
        monsterReagents.put(Hexaghost.ID, new RunicShape());
        monsterReagents.put(SlimeBoss.ID, new TinyHat());
        monsterReagents.put(TheGuardian.ID, new PowerCore());

        // City
        monsterReagents.put(BanditBear.ID, new Sulphur());
        monsterReagents.put(BanditLeader.ID, new Wax());
        monsterReagents.put(BanditPointy.ID, new Hand());
        monsterReagents.put(BronzeOrb.ID, new Steel());
        monsterReagents.put(Byrd.ID, new Feather());
        monsterReagents.put(Centurion.ID, new Steel());
        monsterReagents.put(Chosen.ID, new Feather());
        monsterReagents.put(Healer.ID, new Ether());
        monsterReagents.put(Mugger.ID, new Gold());
        monsterReagents.put(ShelledParasite.ID, new Root());
        monsterReagents.put(SnakePlant.ID, new Root());
        monsterReagents.put(Snecko.ID, new Eye());
        monsterReagents.put(SphericGuardian.ID, new Steel());
        // elites
        monsterReagents.put(BookOfStabbing.ID, new Needle());
        monsterReagents.put(GremlinLeader.ID, new Horn());
        monsterReagents.put(Taskmaster.ID, new Skull());
        // bosses
        monsterReagents.put(BronzeAutomaton.ID, new Mechanism());
        monsterReagents.put(Champ.ID, new Crown());
        monsterReagents.put(TheCollector.ID, new FeyFire());
        monsterReagents.put(TorchHead.ID, new FeyFire());

        // Beyond
        monsterReagents.put(Darkling.ID, new Ichor());
        monsterReagents.put(Exploder.ID, new Flame());
        monsterReagents.put(Maw.ID, new Tooth());
        monsterReagents.put(OrbWalker.ID, new Flame());
        monsterReagents.put(Repulsor.ID, new Lightning());
        monsterReagents.put(Spiker.ID, new Barb());
        monsterReagents.put(SpireGrowth.ID, new Tentacle());
        monsterReagents.put(Transient.ID, new Meteorite());
        monsterReagents.put(WrithingMass.ID, new Root());
        // elites
        monsterReagents.put(GiantHead.ID, new Clay());
        monsterReagents.put(Nemesis.ID, new Nethershroud());
        monsterReagents.put(Reptomancer.ID, new SerpentSkull());
        monsterReagents.put(SnakeDagger.ID, new Needle());
        // bosses
        monsterReagents.put(AwakenedOne.ID, new Feather());
        monsterReagents.put(Deca.ID, new Mechanism());
        monsterReagents.put(Donu.ID, new RunicShape());
        monsterReagents.put(TimeEater.ID, new Tentacle());

        // Ending
        monsterReagents.put(Eyes.ID, new Eye());
        // elites
        monsterReagents.put(SpireShield.ID, new Tentacle());
        monsterReagents.put(SpireSpear.ID, new Tentacle());
        // boss
        monsterReagents.put(CorruptHeart.ID, new PhilosopherShard());
    }
}