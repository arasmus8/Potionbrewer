package potionbrewer.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;
import potionbrewer.orbs.Reagent;
import potionbrewer.orbs.ReagentList;

import static potionbrewer.PotionbrewerMod.makeID;

public class ReagentTwoBlock extends ReagentBlock {
    @Override
    public String key() {
        return makeID("R2B");
    }

    @Override
    protected Reagent getReagent(AbstractCard card) {
        return ReagentList.secondReagent(card.misc);
    }
}
