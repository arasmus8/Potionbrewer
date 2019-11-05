package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import potionbrewer.orbs.Reagent;
import potionbrewer.powers.BrewPotionPower;
import potionbrewer.relics.BunsenBurner;

public class BrewPotionAction extends AbstractGameAction {
    private AbstractOrb orb;
    private static final int TURNS = 3;

    public BrewPotionAction() {
        duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST && !AbstractDungeon.player.orbs.isEmpty()) {
            this.orb = AbstractDungeon.player.orbs.get(0);
            AbstractPlayer p = AbstractDungeon.player;
            if (this.orb instanceof EmptyOrbSlot) {
                this.isDone = true;
            } else if (this.orb instanceof Reagent) {
                this.isDone = true;
                Reagent reagent = (Reagent) this.orb;
                p.evokeOrb();
                int turns = TURNS;
                if (p.hasRelic(BunsenBurner.ID)) {
                    turns -= 1;
                }
                this.addToTop(new ApplyPowerAction(p, p, new BrewPotionPower(p, turns, reagent.getPotion())));
            }
        }

        this.tickDuration();// 30
    }
}
