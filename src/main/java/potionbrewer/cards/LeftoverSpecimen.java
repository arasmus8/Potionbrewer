package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.ChooseReagentAction;
import potionbrewer.characters.Potionbrewer;

import java.util.ArrayList;

public class LeftoverSpecimen extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(LeftoverSpecimen.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int BLOCK = 3;
    private static final int UPGRADE_BLOCK = 2;
    private static final int COST = 0;

    public LeftoverSpecimen() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        baseBlock = BLOCK;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        ArrayList<AbstractCard> reagentChoices = PotionbrewerMod.reagentList.randomChoice(3);
        this.addToBot(new ChooseReagentAction(reagentChoices));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeBlock(UPGRADE_BLOCK);
            exhaust = false;
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}