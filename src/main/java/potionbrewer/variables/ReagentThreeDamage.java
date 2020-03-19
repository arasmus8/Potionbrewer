package potionbrewer.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;
import potionbrewer.cards.Prototype;
import potionbrewer.orbs.Reagent;

import static potionbrewer.PotionbrewerMod.makeID;

public class ReagentThreeDamage extends ReagentDamage {
    @Override
    public String key() {
        return makeID("R3D");
    }

    @Override
    protected Reagent getReagent(Prototype card) {
        return card.reagentC;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof Prototype) {
            Prototype p = (Prototype) card;
            return p.damageC;
        }
        return card.damage;
    }
}