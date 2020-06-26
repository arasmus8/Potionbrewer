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

    public TheFinalStraw() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 0;
    }

    private void resetDesc() {
        magicNumber = baseMagicNumber = 0;
        if (upgraded) {
            rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        } else {
            rawDescription = CARD_STRINGS.DESCRIPTION;
        }
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (upgraded) {
            magicNumber = PotionbrewerMod.zeroCostCardsThisCombat;
            rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        } else {
            magicNumber = PotionbrewerMod.zeroCostCardsThisTurn;
            rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }
        isMagicNumberModified = magicNumber > baseMagicNumber;
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        resetDesc();
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
            initializeDescription();
        }
    }
}