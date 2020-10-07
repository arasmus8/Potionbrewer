package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.GameActionManager;
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

public class Prototype extends CustomCard implements CustomSavable<String[]> {
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

    public Reagent reagentA;
    public Reagent reagentB;
    public Reagent reagentC;

    public int damageA;
    public int damageB;
    public int damageC;

    // /STAT DECLARATION/

    private static String buildName(Reagent a, Reagent b, Reagent c) {
        String[] adjs = {"Potent", "Robust", "Dull", "Bubbly", "Stiff", "Diluted", "Congealed", "Fizzy", "Shiny"};
        String[] types = {"Compound", "Solution", "Serum", "Draught", "Elixir", "Brew", "Tincture", "Mixture", "Philter"};
        String chosenAdj = adjs[(ReagentList.indexFromReagent(a) + ReagentList.indexFromReagent(b)) % adjs.length];
        String chosenType = types[(ReagentList.indexFromReagent(b) + ReagentList.indexFromReagent(c)) % types.length];
        String suffix = "#" + (ReagentList.indexFromReagent(a) + ReagentList.indexFromReagent(c));
        return chosenAdj + " " + chosenType + " " + suffix;
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
        if (a != null && b != null && c != null) {
            reagentA = (Reagent) a.makeCopy();
            reagentB = (Reagent) b.makeCopy();
            reagentC = (Reagent) c.makeCopy();
            applyPowers();
        }
    }

    public Prototype() {
        this(null, null, null);
    }

    public void hydrate() {
        if (reagentA == null || reagentB == null || reagentC == null) {
            return;
        }
        name = buildName(reagentA, reagentB, reagentC);
        rawDescription = buildDescription(reagentA, reagentB, reagentC);
        initializeDescription();
        exhaust = reagentA.exhaust || reagentB.exhaust || reagentC.exhaust;
        if (reagentA.targeted || reagentB.targeted || reagentC.targeted) {
            if (reagentA.aoeDamage || reagentB.aoeDamage || reagentC.aoeDamage) {
                target = CardTarget.ALL_ENEMY;
                isMultiDamage = true;
            } else {
                target = CardTarget.ENEMY;
            }
        } else {
            target = CardTarget.SELF;
        }
        if (!reagentA.damages && !reagentB.damages && !reagentC.damages) {
            type = CardType.SKILL;
        }
        if (reagentA instanceof Skull || reagentB instanceof Skull || reagentC instanceof Skull) {
            cardsToPreview = new Reaction();
        }
    }

    private void renderHelper(SpriteBatch sb, Texture img, float drawX, float drawY) {
        Vector2 vec = new Vector2(drawX, drawY);
        vec.scl(this.drawScale * Settings.scale);
        vec.rotate(this.angle);
        float x = this.current_x + vec.x;
        float y = this.current_y + vec.y;
        float scale = this.drawScale * 0.75F;
        sb.draw(img, x - 64.0F, y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, scale, scale, 0.0F, 0, 0, 128, 128, false, false);
    }

    public void renderPortrait(SpriteBatch sb) {
        // first is at 150deg
        renderHelper(sb, reagentA == null ? Reagent.getDefaultTexture() : reagentA.getTexture(), -71.96F, 116.0F);
        // second is at 60deg
        renderHelper(sb, reagentB == null ? Reagent.getDefaultTexture() : reagentB.getTexture(), 80.0F, 116.0F);
        // third is at 270deg
        renderHelper(sb, reagentC == null ? Reagent.getDefaultTexture() : reagentC.getTexture(), 0.0F, 26.0F);
    }

    private void renderLargeHelper(SpriteBatch sb, Texture img, float drawX, float drawY, float cardX, float cardY) {
        Vector2 vec = new Vector2(drawX, drawY);
        vec.scl(Settings.scale);
        vec.rotate(this.angle);
        float x = cardX + vec.x;
        float y = cardY + vec.y;
        float scale = Settings.scale * 1.5F;
        sb.draw(img, x - 64.0F, y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, scale, scale, 0.0F, 0, 0, 128, 128, false, false);
    }

    public void renderLargePortrait(SpriteBatch sb) {
        float xPos = (float) Settings.WIDTH / 2.0F;
        float yPos = (float) Settings.HEIGHT / 2.0F + 136.0F * Settings.scale;
        renderLargeHelper(sb, reagentA == null ? Reagent.getDefaultTexture() : reagentA.getTexture(), -120F, 75F, xPos, yPos);
        renderLargeHelper(sb, reagentB == null ? Reagent.getDefaultTexture() : reagentB.getTexture(), 120F, 75F, xPos, yPos);
        renderLargeHelper(sb, reagentC == null ? Reagent.getDefaultTexture() : reagentC.getTexture(), 0.0F, -75F, xPos, yPos);
    }

    private int calcDamageTimes(Reagent r) {
        if (r.multiDamage) {
            if (r.damageTimes < 0) {
                return GameActionManager.turn;
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

    public void applyPowersToBlockDynamic(int block) {
        this.baseBlock = block;
        this.applyPowersToBlock();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Reagent a = reagentA;
        Reagent b = reagentB;
        Reagent c = reagentC;
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
        this.addToBot(new PrototypeAction(this, a, p, m, damageTimes, blockTimes, aoeDamage, damageA));
        this.addToBot(new PrototypeAction(this, b, p, m, damageTimes, blockTimes, aoeDamage, damageB));
        this.addToBot(new PrototypeAction(this, c, p, m, damageTimes, blockTimes, aoeDamage, damageC));
    }

    @Override
    public void triggerAtStartOfTurn() {
        applyPowers();
    }

    @Override
    public void applyPowers() {
        reagentA.applyPowers();
        baseDamage = reagentA.damage;
        super.applyPowers();
        damageA = damage;

        reagentB.applyPowers();
        baseDamage = reagentB.damage;
        super.applyPowers();
        damageB = damage;

        reagentC.applyPowers();
        baseDamage = reagentC.damage;
        super.applyPowers();
        damageC = damage;

        hydrate();
        baseDamage = damage = 0;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        reagentA.applyPowers();
        baseDamage = reagentA.damage;
        super.calculateCardDamage(mo);
        damageA = damage;

        reagentB.applyPowers();
        baseDamage = reagentB.damage;
        super.calculateCardDamage(mo);
        damageB = damage;

        reagentC.applyPowers();
        baseDamage = reagentC.damage;
        super.calculateCardDamage(mo);
        damageC = damage;

        hydrate();
        baseDamage = damage = 0;
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean catalyze = false;
        if (reagentA != null && reagentB != null && reagentC != null) {
            if (reagentA.catalyze || reagentB.catalyze || reagentC.catalyze) {
                catalyze = true;
            }
        }
        if (catalyze &&
                AbstractDungeon.player != null &&
                PotionTracker.potionsUsedThisTurn.get(AbstractDungeon.player) > 0 &&
                !AbstractDungeon.player.hasPower(NoCatalyzePower.POWER_ID)
        ) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new Prototype(reagentA, reagentB, reagentC);
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        Prototype p = (Prototype) super.makeStatEquivalentCopy();
        if (reagentA != null && reagentB != null && reagentC != null) {
            p.reagentA = reagentA;
            p.reagentB = reagentB;
            p.reagentC = reagentC;
            p.hydrate();
        }
        return p;
    }

    @Override
    public String[] onSave() {
        String[] ids = new String[3];
        ids[0] = reagentA.ID;
        ids[1] = reagentB.ID;
        ids[2] = reagentC.ID;
        return ids;
    }

    @Override
    public void onLoad(String[] ids) {
        if (ids.length == 3) {
            reagentA = ReagentList.fromId(ids[0]);
            reagentB = ReagentList.fromId(ids[1]);
            reagentC = ReagentList.fromId(ids[2]);
            hydrate();
        }
    }
}