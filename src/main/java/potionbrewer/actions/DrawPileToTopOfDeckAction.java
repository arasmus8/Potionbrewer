package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class DrawPileToTopOfDeckAction extends AbstractGameAction {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPileToTopOfDeckAction");
        TEXT = uiStrings.TEXT;
    }

    private AbstractPlayer p;

    public DrawPileToTopOfDeckAction(AbstractCreature source) {
        this.p = AbstractDungeon.player;
        this.setValues(null, source, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
        } else {
            if (this.duration == Settings.ACTION_DUR_FASTER) {
                if (this.p.drawPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                if (this.p.drawPile.size() <= this.amount) {
                    this.isDone = true;
                    return;
                }

                if (this.p.drawPile.group.size() > this.amount) {
                    AbstractDungeon.gridSelectScreen.open(this.p.drawPile, 1, TEXT[0], false, false, false, false);
                    this.tickDuration();
                    return;
                }
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    this.p.drawPile.removeCard(c);
                    this.p.hand.moveToDeck(c, false);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }
}
