package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.cards.Prototype;

import java.util.function.Consumer;

public class LabJournalAction extends AbstractGameAction {
    public LabJournalAction() {
        duration = startDuration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        isDone = true;
        AbstractPlayer p = AbstractDungeon.player;
        Consumer<AbstractCard> reduceCostToZero = card -> {
            card.costForTurn = 0;
            card.cost = 0;
            card.isCostModified = true;
        };
        p.discardPile.group.stream()
                .filter(card -> card instanceof Prototype)
                .forEach(reduceCostToZero);
        p.drawPile.group.stream()
                .filter(card -> card instanceof Prototype)
                .forEach(reduceCostToZero);
        p.hand.group.stream()
                .filter(card -> card instanceof Prototype)
                .forEach(reduceCostToZero);
        p.exhaustPile.group.stream()
                .filter(card -> card instanceof Prototype)
                .forEach(reduceCostToZero);
    }
}
