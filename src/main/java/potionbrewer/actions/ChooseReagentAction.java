package potionbrewer.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.SkipCardButton;

import java.util.ArrayList;

public class ChooseReagentAction extends AbstractGameAction {
    private final ArrayList<AbstractCard> choices;

    public ChooseReagentAction(ArrayList<AbstractCard> choices) {
        duration = Settings.ACTION_DUR_FAST;
        this.choices = choices;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.chooseOneOpen(this.choices);
            ReflectionHacks.setPrivate(AbstractDungeon.cardRewardScreen,
                    CardRewardScreen.class,
                    "skippable",
                    true);
            SkipCardButton skipButton = (SkipCardButton) ReflectionHacks.getPrivate(AbstractDungeon.cardRewardScreen,
                    CardRewardScreen.class,
                    "skipButton");
            skipButton.show();
        }
        this.tickDuration();
    }
}
