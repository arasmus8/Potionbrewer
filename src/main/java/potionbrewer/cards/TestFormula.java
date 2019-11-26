package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class TestFormula extends CustomCard {

// TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(TestFormula.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
// Must have an image with the same NAME as the card in your image folder!

// /TEXT DECLARATION/

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int MAGIC = 2;
    private static final int UPGRADED_MAGIC = 2;

// /STAT DECLARATION/


    public TestFormula() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
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
