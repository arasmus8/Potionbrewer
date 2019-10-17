package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayRandomCardAction extends AbstractGameAction {
    private CardGroup group;

    public PlayRandomCardAction(CardGroup group) {
        duration = Settings.ACTION_DUR_XFAST;
        this.group = group;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_XFAST) {
            this.isDone = true;
            CardGroup filtered = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            group.group.stream().filter(c -> !c.freeToPlayOnce && c.costForTurn >= -1).forEach(filtered::addToTop);
            if(filtered.size() < 1) {
                return;
            }
            AbstractCard c = filtered.getRandomCard(true);
            c.freeToPlayOnce = true;
            switch(c.target) {
                case SELF_AND_ENEMY:
                case ENEMY:
                    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, AbstractDungeon.getRandomMonster()));
                    break;
                case SELF:
                case ALL:
                case ALL_ENEMY:
                case NONE:
                default:
                    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, null));
            }
        }
    }
}
