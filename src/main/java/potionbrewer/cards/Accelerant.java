package potionbrewer.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.powers.AccelerantPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.characters.Potionbrewer.Enums.COLOR_CYAN;

public class Accelerant extends AbstractPotionbrewerCard {

    public static final String ID = PotionbrewerMod.makeID(Accelerant.class.getSimpleName());
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = COLOR_CYAN;

    private static final int COST = 0;

    public Accelerant() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cardsToDraw = BaseMod.MAX_HAND_SIZE - p.hand.size();
        addToBot(new DrawCardAction(p, cardsToDraw));
        this.addToBot(new ApplyPowerAction(p, p, new AccelerantPower(upgraded)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
