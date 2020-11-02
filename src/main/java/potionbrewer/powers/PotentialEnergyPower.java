package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import potionbrewer.PotionbrewerMod;

public class PotentialEnergyPower extends AbstractPotionbrewerPower implements BetterOnApplyPowerPower, CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(PotentialEnergyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PotentialEnergyPower(AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        loadRegion("potential_energy");

        updateDescription();
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature target, AbstractCreature source) {
        return true;
    }

    @Override
    public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (power.ID.equals(StrengthPower.POWER_ID) || power.ID.equals(DexterityPower.POWER_ID)) {
            if (target.equals(owner) && stackAmount > 0) {
                power.amount += amount;
                return stackAmount + amount;
            }
        }
        return stackAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new PotentialEnergyPower(owner, amount);
    }
}
