package potionbrewer.variables;

import potionbrewer.cards.Prototype;
import potionbrewer.orbs.Reagent;

import static potionbrewer.PotionbrewerMod.makeID;

public class ReagentOneDamage extends ReagentDamage {
    @Override
    public String key() {
        return makeID("R1D");
    }

    @Override
    protected Reagent getReagent(Prototype card) {
        return card.reagentA;
    }
}