package potionbrewer.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.cards.Prototype;
import potionbrewer.orbs.Reagent;

public abstract class ReagentDamage extends DynamicVariable {

    protected abstract Reagent getReagent(Prototype card);

    @Override
    public boolean isModified(AbstractCard card) {
        if (AbstractDungeon.getCurrRoom().phase.equals(AbstractRoom.RoomPhase.COMBAT) && card instanceof Prototype) {
            return value(card) != baseValue(card);
        }
        return false;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof Prototype) {
            Prototype p = (Prototype) card;
            Reagent reagent = getReagent(p);
            if (reagent != null && reagent.damages) {
                return reagent.damage;
            }
        }
        return card.baseDamage;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}