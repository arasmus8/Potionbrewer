package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@Deprecated
public class TriggerCatalyzeGlowCheckAction extends AbstractGameAction {

    public TriggerCatalyzeGlowCheckAction() {
        duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_XFAST) {
            this.isDone = true;
            CardGroup hand = AbstractDungeon.player.hand;
            for(AbstractCard c : hand.group) {
                c.triggerOnGlowCheck();
            }
        }
    }
}
