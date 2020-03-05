package potionbrewer.variables;

import potionbrewer.cards.Prototype;
import potionbrewer.orbs.Reagent;

import static potionbrewer.PotionbrewerMod.makeID;

public class ReagentThreeBlock extends ReagentBlock {
    @Override
    public String key() {
        return makeID("R3B");
    }

    @Override
    protected Reagent getReagent(Prototype card) {
        return card.reagentC;
    }
}
