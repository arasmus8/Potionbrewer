package potionbrewer.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;
import potionbrewer.orbs.Reagent;
import potionbrewer.orbs.ReagentList;

import static potionbrewer.PotionbrewerMod.makeID;

public class ReagentThreeDamage extends ReagentDamage {
    @Override
    public String key() {
        return makeID("R3D");
    }

    @Override
    protected Reagent getReagent(AbstractCard card) {
        return ReagentList.thirdReagent(card.misc);
    }
}