package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;

public class BriberyPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(BriberyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int goldCost;

    public BriberyPower(final int reduceAmount, final int goldCost) {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = reduceAmount;
        this.goldCost = goldCost;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("thievery");

        updateDescription();
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && AbstractDungeon.player.gold >= goldCost) {
            this.addToBot(new SFXAction("EVENT_PURCHASE"));
            this.flashWithoutSound();
            AbstractDungeon.player.loseGold(goldCost);
            return damageAmount - amount;
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + goldCost + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new BriberyPower(amount, goldCost);
    }
}
