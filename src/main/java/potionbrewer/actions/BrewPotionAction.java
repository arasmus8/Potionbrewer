package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.PotionbrewerMod;
import potionbrewer.orbs.Reagent;
import potionbrewer.powers.BrewPotionPower;
import potionbrewer.relics.BunsenBurner;

public class BrewPotionAction extends AbstractGameAction {
    private static final int TURNS = 3;
    private boolean initialize = true;

    public BrewPotionAction() {
        duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        if (initialize) {
            initialize = false;
            isDone = true;
            if (!PotionbrewerMod.reagents.isEmpty()) {
                Reagent reagent = PotionbrewerMod.popReagent();
                int turns = TURNS;
                AbstractPlayer p = AbstractDungeon.player;
                if (p.hasRelic(BunsenBurner.ID)) {
                    turns -= 1;
                }
                this.addToTop(new ApplyPowerAction(p, p, new BrewPotionPower(p, turns, reagent.getPotion(), (Reagent) reagent.makeCopy())));
            }
        }
    }
}
