package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.patches.PotionTracker;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class IronFist extends CatalyzeCard {
// TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(IronFist.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
// Must have an image with the same NAME as the card in your image folder!.

// /TEXT DECLARATION/

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC_AMT = 2;
// /STAT DECLARATION/

    public IronFist() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC;
        magicNumber = baseMagicNumber;
    }

    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractPower strength = p.getPower("Strength");

        if (strength != null && PotionTracker.potionsUsedThisTurn.get(p) > 0) {
            strength.amount *= this.magicNumber;
        }

        super.applyPowers();
        if (strength != null && PotionTracker.potionsUsedThisTurn.get(p) > 0) {
            strength.amount /= this.magicNumber;
        }

    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractPower strength = p.getPower("Strength");

        if (strength != null && PotionTracker.potionsUsedThisTurn.get(p) > 0) {
            strength.amount *= this.magicNumber;
        }

        super.calculateCardDamage(mo);
        if (strength != null && PotionTracker.potionsUsedThisTurn.get(p) > 0) {
            strength.amount /= this.magicNumber;
        }

    }

    @Override
    public void catalyzeActions(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
        }
    }

    @Override
    public void useActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            name = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_MAGIC_AMT);
            initializeDescription();
        }
    }
}

