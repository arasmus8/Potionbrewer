package potionbrewer.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;

public abstract class Reagent extends AbstractOrb {
    public boolean exhaust = false;
    public boolean catalyze = false;
    public boolean aoeDamage = false;
    public boolean multiDamage = false;
    public int damageTimes = 0;
    public boolean damages = false;
    public boolean blocks = false;
    public int blockTimes = 0;
    public boolean targeted = true;

    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;

    protected void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    @Override
    public String toString() {
        return this.ID;
    }

    public Reagent(String id, Texture img, String name) {
        this.ID = id;
        this.name = name;
        this.img = img;
        evokeAmount = baseEvokeAmount = 0;
        passiveAmount = basePassiveAmount = 0;
        updateDescription();
        angle = MathUtils.random(360.0f);
        channelAnimTimer = 0.5f;
    }
    
    @Override
    public void applyFocus() {
        passiveAmount = basePassiveAmount;
        evokeAmount = baseEvokeAmount;
    }
    
    @Override
    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("CARD_EXHAUST"));
    }
    
    @Override
    public void onStartOfTurn() {
    }
    
    @Override
    public void updateAnimation() {
        
        super.updateAnimation();
        angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(cX, cY));
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }
    
    @Override
    public void render(SpriteBatch sb) {
        float size = 128.0F;
        float adjust = 64.0F;
        sb.setColor(this.c);
        sb.draw(img, this.cX - adjust + this.bobEffect.y / 4.0F, this.cY - adjust + this.bobEffect.y / 4.0F, adjust, adjust, size, size, this.scale, this.scale, 0.0F, 0, 0, 128, 128, false, false);

        // renderText(sb);
        this.hb.render(sb);
    }
    
    @Override
    public void triggerEvokeAnimation() {
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(cX, cY));
    }
    
    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("RELIC_DROP_FLAT", 0.1f);
    }

    public abstract Texture getTexture();

    public abstract AbstractPotion getPotion();

    public void doActions(AbstractPlayer p, AbstractMonster m) {
    }

    public String getCardDescription() {
        return "";
    }
}
