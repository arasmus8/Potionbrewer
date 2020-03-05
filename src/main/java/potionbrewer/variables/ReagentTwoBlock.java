package potionbrewer.variables;

import potionbrewer.cards.Prototype;
import potionbrewer.orbs.Reagent;

import static potionbrewer.PotionbrewerMod.makeID;

public class ReagentTwoBlock extends ReagentBlock {
    @Override
    public String key() {
        return makeID("R2B");
    }

    @Override
    protected Reagent getReagent(Prototype card) {
        return card.reagentB;
    }
}
