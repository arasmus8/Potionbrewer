package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
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

    private static final float DAMAGE_REDUCE = 2.0F;
    private static final int INFECTION_MULTIPLIER = 1;
    private static final int DAMAGE_PER_STACK = 3;

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
    public void atStartOfTurn() {
        this.flash();
        DamageInfo info = new DamageInfo(source, amount * DAMAGE_PER_STACK, DamageInfo.DamageType.HP_LOSS);
        this.addToBot(new DamageAction(owner, info, AttackEffect.POISON));
        this.addToBot(new RemoveSpecificPowerAction(owner, source, this));
        int infectionAmount = amount * INFECTION_MULTIPLIER;
        this.addToBot(new ApplyPowerAction(owner, source, new InfectionPower(owner, source, infectionAmount), infectionAmount));
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage / DAMAGE_REDUCE;
        } else {
            return damage;
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount * DAMAGE_PER_STACK + DESCRIPTIONS[1] + amount * INFECTION_MULTIPLIER + DESCRIPTIONS[2];
    }
    
    @Override
    public AbstractPower makeCopy() {
        return new DiseasePower(owner, source, amount);
    }
}
