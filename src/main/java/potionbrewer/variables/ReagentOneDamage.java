package potionbrewer.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;
import potionbrewer.orbs.Reagent;
import potionbrewer.orbs.ReagentList;

import static potionbrewer.PotionbrewerMod.makeID;

public class ReagentOneDamage extends ReagentDamage {
    @Override
    public String key() {
        return makeID("R1D");
    }

    @Override
    protected Reagent getReagent(AbstractCard card) {
        return ReagentList.firstReagent(card.misc);
    }
}