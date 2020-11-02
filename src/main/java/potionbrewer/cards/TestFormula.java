package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import java.util.ArrayList;

public class TestFormula extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(TestFormula.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int MAGIC = 2;
    private static final int UPGRADED_MAGIC = 2;

    public TestFormula() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        baseMagicNumber = MAGIC;
        magicNumber = baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster target) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            ArrayList<AbstractCard> tonicChoices = PotionbrewerMod.tonicLibrary.getRandomChoices(magicNumber);
            this.addToBot(new ChooseOneAction(tonicChoices));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADED_MAGIC);
            initializeDescription();
        }
    }
}
