package potionbrewer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import potionbrewer.cards.*;

import java.util.ArrayList;

public class CardList {
    public static final ArrayList<AbstractCard> allCards;

    static {
        allCards = new ArrayList<>();
        allCards.add(new PotionbrewerDefend());
        allCards.add(new PotionbrewerStrike());
        allCards.add(new Collect());
        allCards.add(new ChemicalSpill());
        allCards.add(new Prototype());
        allCards.add(new Distill());
        allCards.add(new ReagentCard());
        allCards.add(new FlashPowder());
        allCards.add(new Smack());
        allCards.add(new UnstableFormula());
    }
}
