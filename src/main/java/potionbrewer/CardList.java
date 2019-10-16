package potionbrewer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import potionbrewer.cards.*;

import java.util.ArrayList;

public class CardList {
    public static final ArrayList<AbstractCard> allCards;

    static {
        allCards = new ArrayList<>();
        // Starting Cards
        allCards.add(new PotionbrewerDefend());
        allCards.add(new PotionbrewerStrike());
        allCards.add(new Collect());
        allCards.add(new ChemicalSpill());

        // Special Cards
        allCards.add(new Prototype());
        allCards.add(new Distill());
        allCards.add(new ReagentCard());

        // Common Attacks
        allCards.add(new BlindingSpray());
        allCards.add(new Contingency());
        allCards.add(new EyeGouge());
        allCards.add(new FlashPowder());
        allCards.add(new OneTwoPunch());
        allCards.add(new Smack());
        allCards.add(new TimeBomb());
        allCards.add(new UnstableFormula());
        allCards.add(new VolatileSolution());

        //Common Skills
        allCards.add(new Brandish());
        allCards.add(new QuickStep());
        allCards.add(new Smokescreen());
        allCards.add(new TestFormula());
    }
}
