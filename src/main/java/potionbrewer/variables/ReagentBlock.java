package potionbrewer.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.cards.Prototype;
import potionbrewer.orbs.Reagent;

public abstract class ReagentBlock extends DynamicVariable {

    protected abstract Reagent getReagent(AbstractCard card);

    @Override
    public boolean isModified(AbstractCard card) {
        if (AbstractDungeon.getCurrRoom().phase.equals(AbstractRoom.RoomPhase.COMBAT) && card instanceof Prototype) {
            Prototype p = (Prototype) card;
            Reagent reagent = getReagent(card);
            if (reagent != null && reagent.blocks) {
                p.applyPowersToBlockDynamic(reagent.block);
                return reagent.block != p.block;
            }
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof Prototype) {
            Prototype p = (Prototype) card;
            Reagent reagent = getReagent(card);
            if (reagent != null && reagent.blocks) {
                p.applyPowersToBlockDynamic(reagent.block);
                return p.block;
            }
        }
        return card.block;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof Prototype) {
            Reagent reagent = getReagent(card);
            if (reagent != null && reagent.blocks) {
                return reagent.block;
            }
        }
        return card.baseBlock;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}