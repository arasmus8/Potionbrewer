package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.vfx.BlindingSprayEffect;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class BlindingSpray extends CustomCard {
    // TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(BlindingSpray.class.getSimpleName());
    public static final String IMG = makeCardPath("BlindingSpray.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
    // Must have an image with the same NAME as the card in your image folder!.

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int MAGIC = 1;
    private static final int UPGRADED_MAGIC = 1;
    // /STAT DECLARATION/

    public BlindingSpray() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC;
        magicNumber = baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new BlindingSprayEffect(m.hb.cX, m.hb.cY)));
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADED_MAGIC);
            initializeDescription();
        }
    }
}