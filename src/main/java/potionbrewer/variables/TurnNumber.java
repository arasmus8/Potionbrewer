package potionbrewer.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static potionbrewer.PotionbrewerMod.makeID;

public class TurnNumber extends DynamicVariable {

    @Override
    public String key() {
        return makeID("TURN_NUMBER");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        return GameActionManager.turn;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return GameActionManager.turn;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}