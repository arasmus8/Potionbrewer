package potionbrewer.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;
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

    @Override
    public int value(AbstractCard card) {
        if (card instanceof Prototype) {
            Prototype p = (Prototype) card;
            return p.damageA;
        }
        return card.damage;
    }
}