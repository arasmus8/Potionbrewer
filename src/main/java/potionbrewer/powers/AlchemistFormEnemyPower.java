package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegrowPower;
import potionbrewer.PotionbrewerMod;

public class AlchemistFormEnemyPower extends AbstractPotionbrewerPower implements CloneablePowerInterface, NonStackablePower {
    public static final String POWER_ID = PotionbrewerMod.makeID(AlchemistFormEnemyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int goldGain;

    public AlchemistFormEnemyPower(AbstractCreature owner, final int goldGain) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.goldGain = goldGain;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("alchemy");

        updateDescription();
    }

    @Override
    public void onDeath() {
        if (owner.hasPower(RegrowPower.POWER_ID)) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                return;
            }
        }
        AbstractDungeon.player.gainGold(goldGain);
        CardCrawlGame.sound.play("GOLD_GAIN");
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + goldGain + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AlchemistFormEnemyPower(owner, goldGain);
    }
}
