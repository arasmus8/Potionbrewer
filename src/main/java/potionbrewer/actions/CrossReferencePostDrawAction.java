package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class CrossReferencePostDrawAction extends AbstractGameAction {

    public CrossReferencePostDrawAction() {
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        isDone = true;

        ArrayList<AbstractCard> list = new ArrayList<>(AbstractDungeon.player.hand.group);
        CrossReferenceAction.crossReferenceCards.forEach(c -> list.removeIf(l -> l.uuid.equals(c.uuid)));
        list.forEach(c -> c.setCostForTurn(0));
    }
}
