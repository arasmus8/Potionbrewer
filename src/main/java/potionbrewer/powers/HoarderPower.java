package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;

public class HoarderPower extends AbstractPotionbrewerPower implements CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(HoarderPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HoarderPower(int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        loadRegion("hoarder");

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.flash();
            PotionbrewerMod.addReagent(PotionbrewerMod.reagentList.randomReagent());
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new HoarderPower(amount);
    }
}
