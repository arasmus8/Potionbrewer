package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class TheFinalStraw extends CustomCard {
    // TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(TheFinalStraw.class.getSimpleName());
    public static final String IMG = makeCardPath("TheFinalStraw.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
    // Must have an image with the same NAME as the card in your image folder!.

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 3;
    // /STAT DECLARATION/

    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DAMAGE = 5;

    public TheFinalStraw() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    private void resetDesc() {
        magicNumber = baseMagicNumber = 0;
        rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {
        resetDesc();
    }

    @Override
    public void triggerAtStartOfTurn() {
        resetDesc();
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (PotionbrewerMod.lastPlayedCardCostZero) {
            magicNumber += 1;
            isMagicNumberModified = true;
            rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SwordBoomerangAction(new DamageInfo(p, damage, damageTypeForTurn), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        AbstractCard copy = super.makeCopy();
        ((TheFinalStraw) copy).resetDesc();
        return copy;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            initializeDescription();
        }
    }
}