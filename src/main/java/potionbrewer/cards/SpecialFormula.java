package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.option.ChoosePotion;
import potionbrewer.characters.Potionbrewer;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SpecialFormula extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(SpecialFormula.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int MAGIC = 3;

    public SpecialFormula() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        this.baseMagicNumber = MAGIC;
        this.magicNumber = MAGIC;
        exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = PotionbrewerMod.potionLibrary.getRandomPotionIdList(magicNumber).stream()
                .map((ChoosePotion::new))
                .collect(Collectors.toCollection(ArrayList::new));
        this.addToBot(new ChooseOneAction(choices));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}