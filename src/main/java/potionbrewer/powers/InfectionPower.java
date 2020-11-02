package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;

public class InfectionPower extends AbstractPotionbrewerPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = PotionbrewerMod.makeID(InfectionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int THRESHOLD = 10;
    private static final int DISEASE = 1;

    public InfectionPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.DEBUFF;
        isTurnBased = true;

        loadRegion("infection");

        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(POWER_ID) && amount >= THRESHOLD) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            this.addToBot(new ApplyPowerAction(owner, source, new DiseasePower(owner, source, DISEASE), DISEASE));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        amount += stackAmount;
        if (amount >= THRESHOLD) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            addToBot(new ApplyPowerAction(owner, source, new DiseasePower(owner, source, DISEASE), DISEASE));
        }
    }

    @Override
    public void atEndOfRound() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            stackPower(amount);
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + THRESHOLD + DESCRIPTIONS[1];
    }
    
    @Override
    public AbstractPower makeCopy() {
        return new InfectionPower(owner, source, amount);
    }
}
