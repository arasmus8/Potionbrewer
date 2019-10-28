package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.PrototypeAction;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.orbs.Reagent;
import potionbrewer.orbs.ReagentList;
import potionbrewer.orbs.Skull;
import potionbrewer.patches.PotionTracker;
import potionbrewer.powers.NoCatalyzePower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class Prototype extends CustomCard {
    // TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(Prototype.class.getSimpleName());
    public static final String IMG = makeCardPath("Prototype.png");
    // Must have an image with the same NAME as the card in your image folder!.
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    // /STAT DECLARATION/

    private static String buildName(final boolean upgraded, Reagent a, Reagent b, Reagent c) {
        String[] adjs = {"Potent", "Robust", "Concentrated", "Full-Bodied", "Stiff", "Diluted", "Congealed", "Fizzy", "Shimmering"};
        String[] types = {"Compound", "Solution", "Serum", "Draught", "Elixir", "Brew", "Tincture", "Mixture", "Philter"};
        String chosenAdj = adjs[(ReagentList.indexFromReagent(a) + ReagentList.indexFromReagent(b)) % adjs.length];
        String chosenType = types[(ReagentList.indexFromReagent(b) + ReagentList.indexFromReagent(c)) % types.length];
        String suffix = "#" + (ReagentList.indexFromReagent(a) + ReagentList.indexFromReagent(c));
        return chosenAdj + " " + chosenType + " " + suffix;
        // return a.name + "/" + b.name + "/" + c.name + (upgraded ? " +" : "");
    }

    private static String buildDescription(Reagent a, Reagent b, Reagent c) {
        if (a == null || b == null || c == null) {
            return CARD_STRINGS.DESCRIPTION;
        }
        StringBuilder s = new StringBuilder();
        s.append(a.getCardDescription(1));
        s.append(" NL ");
        s.append(b.getCardDescription(2));
        s.append(" NL ");
        s.append(c.getCardDescription(3));
        if (a.exhaust || b.exhaust || c.exhaust) {
            s.append(" NL Exhaust.");
        }
        return s.toString();
    }

    public Prototype(Reagent a, Reagent b, Reagent c) {
        super(ID, CARD_STRINGS.NAME, IMG, COST, buildDescription(a, b, c), TYPE, COLOR, RARITY, TARGET);
        isMultiDamage = true;
        if (a == null || b == null || c == null) {
            misc = 0;
        } else {
            misc = ReagentList.buildMisc(a, b, c);
            hydrate();
        }
    }

    public Prototype() {
        this(null, null, null);
    }

    public void hydrate() {
        Reagent a = ReagentList.firstReagent(misc);
        Reagent b = ReagentList.secondReagent(misc);
        Reagent c = ReagentList.thirdReagent(misc);
        if (a == null || b == null || c == null) {
            return;
        }
        name = buildName(upgraded, a, b, c);
        rawDescription = buildDescription(a, b, c);
        initializeDescription();
        exhaust = a.exhaust || b.exhaust || c.exhaust;
        if (a.targeted || b.targeted || c.targeted) {
            if (a.aoeDamage || b.aoeDamage || c.aoeDamage) {
                target = CardTarget.ALL_ENEMY;
                isMultiDamage = true;
            } else {
                target = CardTarget.ENEMY;
            }
        } else {
            target = CardTarget.SELF;
        }
        if (!a.damages && !b.damages && !c.damages) {
            type = CardType.SKILL;
        }
        if (a instanceof Skull || b instanceof Skull || c instanceof Skull) {
            cardsToPreview = new Reaction();
        }
    }

    private void renderHelper(SpriteBatch sb, Texture img, float drawX, float drawY) {
        Vector2 vec = new Vector2(drawX, drawY);
        vec.scl(this.drawScale * Settings.scale);
        vec.rotate(this.angle);
        float x = this.current_x + vec.x;
        float y = this.current_y + vec.y;
        float scale = this.drawScale * 0.7F;
        sb.draw(img, x - 64.0F, y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, scale, scale, 0.0F, 0, 0, 128, 128, false, false);
    }

    public void renderPortrait(SpriteBatch sb) {
        Reagent a = ReagentList.firstReagent(misc);
        if (a != null) {
            // first is at 150deg
            renderHelper(sb, a.getTexture(), -71.96F, 116.0F);
        } else {
            renderHelper(sb, Reagent.getDefaultTexture(), -71.96F, 116.0F);
        }
        Reagent b = ReagentList.secondReagent(misc);
        if (b != null) {
            // second is at 60deg
            renderHelper(sb, b.getTexture(), 80.0F, 116.0F);
        } else {
            renderHelper(sb, Reagent.getDefaultTexture(), 80.0F, 116.0F);
        }
        Reagent c = ReagentList.thirdReagent(misc);
        if (c != null) {
            // third is at 270deg
            renderHelper(sb, c.getTexture(), 0.0F, 26.0F);
        } else {
            renderHelper(sb, Reagent.getDefaultTexture(),0.0F, 26.0F);
        }
    }

    private int calcDamageTimes(Reagent r) {
        if (r.multiDamage) {
            if (r.damageTimes < 0) {
                return PotionbrewerMod.turnNumber;
            }
            return r.damageTimes;
        }
        return 0;
    }

    private int calcBlockTimes(Reagent a, Reagent b, Reagent c) {
        if (a.blockTimes > 0) return a.blockTimes;
        if (b.blockTimes > 0) return b.blockTimes;
        if (c.blockTimes > 0) return c.blockTimes;
        return 1;
    }

    public void applyPowersDynamic(int damage) {
        this.baseDamage = damage;
        this.applyPowers();
    }

    public void applyPowersToBlockDynamic(int block) {
        this.baseBlock = block;
        this.applyPowersToBlock();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Reagent a = ReagentList.firstReagent(misc);
        Reagent b = ReagentList.secondReagent(misc);
        Reagent c = ReagentList.thirdReagent(misc);
        if (a == null || b == null || c == null) {
            return;
        }
        int damageTimes = 0;
        damageTimes += calcDamageTimes(a);
        damageTimes += calcDamageTimes(b);
        damageTimes += calcDamageTimes(c);
        if (damageTimes == 0) {
            damageTimes = 1;
        }
        boolean aoeDamage = a.aoeDamage || b.aoeDamage || c.aoeDamage;
        int blockTimes = calcBlockTimes(a, b, c);
        this.addToBot(new PrototypeAction(this, a, p, m, damageTimes, blockTimes, aoeDamage));
        this.addToBot(new PrototypeAction(this, b, p, m, damageTimes, blockTimes, aoeDamage));
        this.addToBot(new PrototypeAction(this, c, p, m, damageTimes, blockTimes, aoeDamage));
    }

    @Override
    public void triggerWhenDrawn() {
        hydrate();
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean catalyze = false;
        if (misc > 0) {
            Reagent a = ReagentList.firstReagent(misc);
            Reagent b = ReagentList.secondReagent(misc);
            Reagent c = ReagentList.thirdReagent(misc);
            if (a != null && b != null && c != null && (a.catalyze || b.catalyze || c.catalyze)) {
                catalyze = true;
            }
        }
        if ( catalyze &&
                AbstractDungeon.player != null &&
                PotionTracker.potionsUsedThisTurn.get(AbstractDungeon.player) > 0 &&
                !AbstractDungeon.player.hasPower(NoCatalyzePower.POWER_ID)
        ) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        Prototype c = new Prototype();
        c.misc = misc;
        c.hydrate();
        return c;
    }
}