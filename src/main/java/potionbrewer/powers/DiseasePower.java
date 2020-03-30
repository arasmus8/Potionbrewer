package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makePowerPath;

public class DiseasePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = PotionbrewerMod.makeID(DiseasePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("disease_84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("disease_32.png"));

    private static final float DAMAGE_REDUCE = 0.5F;
    private static final int INFECTION_MULTIPLIER = 1;

    public DiseasePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.DEBUFF;
        isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        
        updateDescription();
    }

    @Override
    public void atEndOfRound() {
        this.flash();
        this.addToBot(new ReducePowerAction(owner, source, this, 1));
        int infectionAmount = amount * INFECTION_MULTIPLIER;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            this.addToBot(new ApplyPowerAction(m, source, new InfectionPower(m, source, infectionAmount), infectionAmount));
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * DAMAGE_REDUCE;
        } else {
            return damage;
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount * INFECTION_MULTIPLIER + DESCRIPTIONS[1];
    }
    
    @Override
    public AbstractPower makeCopy() {
        return new DiseasePower(owner, source, amount);
    }
}
