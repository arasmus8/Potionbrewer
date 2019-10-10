package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlayRandomCardAction extends AbstractGameAction {
    private AbstractCard card;

    public PlayRandomCardAction(CardGroup group) {
        duration = Settings.ACTION_DUR_XFAST;
        card = group.getRandomCard(true);
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_XFAST) {
            this.isDone = true;
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            if (m != null) {
                card.freeToPlayOnce = true;
                AbstractDungeon.actionManager.addToTop(new NewQueueCardAction(card, m, false, true));
            }
        }
    }
}
