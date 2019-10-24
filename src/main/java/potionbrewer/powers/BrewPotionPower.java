package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;

public class BrewPotionPower extends AbstractPower implements NonStackablePower, CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(BrewPotionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final AbstractPotion potion;

    /*
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("disease_84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("disease_32.png"));
    */

    public BrewPotionPower(final AbstractCreature owner, final int turns, final AbstractPotion potion) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = turns;
        this.potion = potion;
        type = PowerType.BUFF;
        this.updateDescription();
        this.loadRegion("time");
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + potion.name;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (amount <= 1) {
                this.flash();
                this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
                this.addToBot(new ObtainPotionAction(potion));
            } else {
                this.addToBot(new ReducePowerAction(owner, owner, this, 1));
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new BrewPotionPower(owner, amount, potion.makeCopy());
    }
}
