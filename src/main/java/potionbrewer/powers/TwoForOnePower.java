package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.EntropicBrew;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;

public class TwoForOnePower extends AbstractPower implements PotionTrackingPower, CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(TwoForOnePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TwoForOnePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;

        this.loadRegion("burst");

        this.updateDescription();
    }

    @Override
    public void onUsePotion(AbstractPotion potion) {
        if (PotionbrewerMod.potionIsFromCard) return;
        if (potion.ID.equals(SmokeBomb.POTION_ID)) return;
        if (potion.ID.equals(EntropicBrew.POTION_ID)) return;
        this.flash();
        if (amount > 1) {
            this.addToBot(new ReducePowerAction(owner, owner, this, 1));
        } else {
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
        this.addToBot(new ObtainPotionAction(potion.makeCopy()));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new TwoForOnePower(owner, amount);
    }
}
