package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.cards.option.ChoosePotion;

public class PlayPotionCardAction extends AbstractGameAction {

    private AbstractMonster target;

    public PlayPotionCardAction(AbstractMonster target) {
        duration = Settings.ACTION_DUR_MED;
        this.target = target;
    }

    @Override
    public void update() {
        this.isDone = true;
        AbstractCard tmp = new ChoosePotion();
        AbstractDungeon.player.limbo.addToBottom(tmp);
        tmp.current_y = -200.0F * Settings.scale;
        tmp.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.scale;
        tmp.target_y = (float) Settings.HEIGHT / 2.0F;
        tmp.targetAngle = 0.0F;
        tmp.lighten(false);
        tmp.drawScale = 0.12F;
        tmp.targetDrawScale = 0.75F;
        tmp.applyPowers();
        tmp.purgeOnUse = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, target, 0, true, true), true);
    }
}
