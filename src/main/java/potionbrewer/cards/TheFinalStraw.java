package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

public class TheFinalStraw extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(TheFinalStraw.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 3;

    private static final int DAMAGE = 10;

    public TheFinalStraw() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 0;
    }

    private void resetDesc() {
        magicNumber = baseMagicNumber = 0;
        if (upgraded) {
            rawDescription = UPGRADE_DESCRIPTION;
        } else {
            rawDescription = DESCRIPTION;
        }
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (upgraded) {
            magicNumber = PotionbrewerMod.zeroCostCardsThisCombat;
            rawDescription = UPGRADE_DESCRIPTION + EXTENDED_DESCRIPTION[0];
        } else {
            magicNumber = PotionbrewerMod.zeroCostCardsThisTurn;
            rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
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
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}