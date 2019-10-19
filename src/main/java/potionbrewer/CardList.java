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
        allCards.add(new Biohazard());
        allCards.add(new Brandish());
        allCards.add(new CausticBarrier());
        allCards.add(new FortifiedSerum());
        allCards.add(new QuickStep());
        allCards.add(new Refill());
        allCards.add(new Smokescreen());
        allCards.add(new TestFormula());
        allCards.add(new Toxicity());

        //Uncommon Attacks
        allCards.add(new ChemicalWeapons());
        allCards.add(new EmptyBottle());

        //Uncommon Skills
        allCards.add(new BlindingMist());

        //Uncommon Powers
        allCards.add(new AnotherRound());

        //Rare Attacks
        allCards.add(new BrokenBeakers());

        //Rare Skills
        allCards.add(new Catalyze());

        //Rare Powers
        allCards.add(new AlchemistForm());
        allCards.add(new Bribery());
    }
}
