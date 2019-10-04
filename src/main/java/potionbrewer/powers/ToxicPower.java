package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makePowerPath;

public class ToxicPower extends AbstractPower implements CloneablePowerInterface, HealthBarRenderPower {
    public AbstractCreature source;
    
    public static final String POWER_ID = PotionbrewerMod.makeID(ToxicPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));
    
    public ToxicPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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

    private int damageAmount() {
        int curr = owner.currentHealth;
        if(curr<1) {
            return 0;
        } else if(curr<5) {
            return 1;
        } else {
            return curr / 5;
        }
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            this.addToBot(new PoisonLoseHpAction(this.owner, this.source, this.damageAmount(), AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (amount <= 0) {
            this.addToBot(new RemoveSpecificPowerAction(owner, source, this));
        }
        this.addToBot(new ReducePowerAction(owner, source, ToxicPower.POWER_ID, 1));
    }
    
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + "20%" + DESCRIPTIONS[1];
    }
    
    @Override
    public AbstractPower makeCopy() {
        return new ToxicPower(owner, source, amount);
    }

    @Override
    public int getHealthBarAmount() {
        return this.damageAmount();
    }

    @Override
    public Color getColor() {
        return Color.SALMON;
    }
}
