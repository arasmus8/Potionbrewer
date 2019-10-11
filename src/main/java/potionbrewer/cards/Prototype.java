package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.orbs.Reagent;
import potionbrewer.orbs.ReagentList;
import potionbrewer.patches.PotionTracker;

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
    private static String buildDescription(Reagent a, Reagent b, Reagent c) {
        if (a == null || b == null || c == null) {
            return CARD_STRINGS.DESCRIPTION;
        }
        StringBuilder s = new StringBuilder();
        s.append(a.getCardDescription());
        s.append(" NL ");
        s.append(b.getCardDescription());
        s.append(" NL ");
        s.append(c.getCardDescription());
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
        name = a.name + " " + b.name + " " + c.name;
        if (upgraded) {
            name += "+";
        }
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
    }

    private void renderReagent(SpriteBatch sb, Texture img, float offsetX, float offsetY) {
        float drawX = this.current_x - 70.0F;
        float drawY = this.current_y - 30.0F;
        float scale = 0.8F;
        sb.draw(img, drawX + offsetX, drawY + 72.0F + offsetY, 64.0F, 64.0F, 128.0F, 128.0F, this.drawScale * Settings.scale * scale, this.drawScale * Settings.scale * scale, this.angle, 0, 0, 128, 128, false, false);
    }

    public void renderPortrait(SpriteBatch sb) {
        Reagent a = ReagentList.firstReagent(misc);
        if (a != null) {
            renderReagent(sb, a.getTexture(), 0, -80);
        }
        Reagent b = ReagentList.secondReagent(misc);
        if (b != null) {
            renderReagent(sb, b.getTexture(), -50, -20);
        }
        Reagent c = ReagentList.thirdReagent(misc);
        if (c != null) {
            renderReagent(sb, c.getTexture(), 50, -20);
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

    private void performActions(Reagent r, AbstractPlayer p, AbstractMonster m, int damageTimes, int blockTimes, boolean aoeDamage) {
        if (r.damages) {
            for (int i = 0; i < damageTimes; i++) {
                r.doActions(p, aoeDamage ? null : m);
            }
        } else if (r.blocks) {
            for (int i = 0; i < blockTimes; i++) {
                r.doActions(p, m);
            }
        } else {
            r.doActions(p, aoeDamage ? null : m);
        }
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
        performActions(a, p, m, damageTimes, blockTimes, aoeDamage);
        performActions(b, p, m, damageTimes, blockTimes, aoeDamage);
        performActions(c, p, m, damageTimes, blockTimes, aoeDamage);
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean catalyze = false;
        if (misc > 0) {
            Reagent a = ReagentList.firstReagent(misc);
            Reagent b = ReagentList.secondReagent(misc);
            Reagent c = ReagentList.secondReagent(misc);
            if (a != null && b != null && c != null && (a.catalyze || b.catalyze || c.catalyze)) {
                catalyze = true;
            }
        }
        if (catalyze && AbstractDungeon.player != null && PotionTracker.potionsUsedThisTurn.get(AbstractDungeon.player) > 0) {
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