package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;

public class AccelerantPower extends AbstractPotionbrewerPower implements CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(AccelerantPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final boolean upgraded;

    public AccelerantPower(boolean upgraded) {
        name = NAME;
        ID = POWER_ID;
        isTurnBased = true;

        this.owner = AbstractDungeon.player;
        this.amount = -1;
        this.loadRegion("corruption");
        this.upgraded = upgraded;

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            int countToExhaust = AbstractDungeon.player.hand.size();
            if (upgraded) {
                countToExhaust /= 2;
            }
            addToTop(new ExhaustAction(countToExhaust, true, true));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AccelerantPower(upgraded);
    }
}
