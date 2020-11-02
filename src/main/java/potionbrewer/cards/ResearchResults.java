package potionbrewer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.ResearchResultsAction;
import potionbrewer.cards.option.ChoosePotion;
import potionbrewer.characters.Potionbrewer;

public class ResearchResults extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(ResearchResults.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = -1;

    public ResearchResults() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        cardsToPreview = new ChoosePotion(null);
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ResearchResultsAction(p, m, freeToPlayOnce, energyOnUse));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

