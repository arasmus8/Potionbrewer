package potionbrewer.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.ChooseReagentAction;

import java.util.ArrayList;

public class ReagentCard extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(ReagentCard.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 0;

// /STAT DECLARATION/

    public ReagentCard() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> reagentChoices = PotionbrewerMod.reagentList.randomChoice(3);

        this.addToBot(new ChooseReagentAction(reagentChoices));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
    }
}
