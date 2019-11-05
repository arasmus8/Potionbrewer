package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegrowPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makePowerPath;

public class AlchemistFormEnemyPower extends AbstractPower implements CloneablePowerInterface, NonStackablePower {
    public static final String POWER_ID = PotionbrewerMod.makeID(AlchemistFormEnemyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("alchemy84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("alchemy32.png"));

    private int goldGain;

    public AlchemistFormEnemyPower(AbstractCreature owner, final int goldGain) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.goldGain = goldGain;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

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
