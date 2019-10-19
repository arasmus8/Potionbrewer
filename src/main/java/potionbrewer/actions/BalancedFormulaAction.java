package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BalancedFormulaAction extends AbstractGameAction {
    public BalancedFormulaAction() {
        duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            isDone = true;
            AbstractPlayer p = AbstractDungeon.player;

            CardGroup g = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            p.drawPile.group.stream()
                    .filter(c -> c.cost == 0 || c.freeToPlayOnce)
                    .forEach(g::addToRandomSpot);

            if (g.size() > 0) {
                p.drawPile.moveToHand(g.getRandomCard(true), p.drawPile);
            }

            AbstractDungeon.player.hand.refreshHandLayout();

            p.discardPile.group.stream()
                    .filter(c -> c.cost == 0 || c.freeToPlayOnce)
                    .limit(1)
                    .forEach(c -> this.addToBot(new DiscardToHandAction(c)));
        }
    }
}
