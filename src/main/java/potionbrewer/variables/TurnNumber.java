package potionbrewer.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import potionbrewer.PotionbrewerMod;

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
        return PotionbrewerMod.turnNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return PotionbrewerMod.turnNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}