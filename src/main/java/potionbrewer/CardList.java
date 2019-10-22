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
        allCards.add(new Backhand());
        allCards.add(new BalancedFormula());
        allCards.add(new ChemicalWeapons());
        allCards.add(new Discombobulate());
        allCards.add(new EmptyBottle());
        allCards.add(new ExothermicReaction());
        allCards.add(new ExplosiveReaction());
        allCards.add(new FlareUp());
        allCards.add(new FollowUp());
        allCards.add(new IronFist());
        allCards.add(new TestSubject());
        allCards.add(new WizBang());

        //Uncommon Skills
        allCards.add(new AcidCloud());
        allCards.add(new Alacrity());
        allCards.add(new BlindingMist());
        allCards.add(new ChemicalShroud());
        allCards.add(new ChokingPowder());
        allCards.add(new CrossReference());
        allCards.add(new DigDeep());
        allCards.add(new Experimentation());
        allCards.add(new FuelCell());
        allCards.add(new HazardousMaterials());
        allCards.add(new LeftoverSpecimen());
        allCards.add(new MicrobeSpray());

        //Uncommon Powers
        allCards.add(new AnotherRound());
        allCards.add(new ChainReaction());
        allCards.add(new PortableLab());
        allCards.add(new PrivateReserves());

        //Rare Attacks
        allCards.add(new BeatDown());
        allCards.add(new BrokenBeakers());
        allCards.add(new RefinedProcess());
        allCards.add(new ResearchResults());
        allCards.add(new TheFinalStraw());
        allCards.add(new Transmute());

        //Rare Skills
        allCards.add(new Accelerant());
        allCards.add(new Catalyze());
        allCards.add(new HazardousWaste());
        allCards.add(new IronFlesh());
        allCards.add(new SpecialFormula());

        //Rare Powers
        allCards.add(new AlchemistForm());
        allCards.add(new Bribery());
    }
}
