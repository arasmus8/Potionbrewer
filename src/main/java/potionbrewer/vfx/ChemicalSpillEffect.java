package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ChemicalSpillEffect extends AbstractGameEffect {
    private static TextureAtlas.AtlasRegion poisonAttackImage;
    private float originX;
    private float originY;
    private float targetX;
    private float targetY;
    private float bounceHeight;
    private float x;
    private float y;
    private float yOffset;
    private boolean soundPlayed = false;
    private float smallParticleTimer = 0f;
    private static Color color = new Color(0.33f, 1f, 64f, 0.8f);
    private static Color color2 = new Color(0.65f, 1f, 84f, 0.7f);

    private static float DURATION = 0.5f;
    private static float HALF_DURATION = 0.25f;
    private static float VERTICAL_OFFSET = 400f;

    public ChemicalSpillEffect(float originX, float originY, float targetX, float targetY) {
        if (poisonAttackImage == null) {
            poisonAttackImage = ImageMaster.ATK_POISON;
        }
        this.originX = originX;
        this.originY = originY;
        this.targetX = targetX;
        this.targetY = targetY;
        if (this.originY > this.targetY) {
            bounceHeight = VERTICAL_OFFSET * Settings.scale;
        } else {
            bounceHeight = this.targetY - this.originY + VERTICAL_OFFSET * Settings.scale;
        }
        duration = DURATION;
    }

    @Override
    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        x = Interpolation.linear.apply(targetX, originX, duration / DURATION);
        y = Interpolation.linear.apply(targetY, originY, duration / DURATION);
        if (duration > HALF_DURATION) {
            color.a = Interpolation.exp5In.apply(1.0F, 0.0F, (duration - HALF_DURATION) / HALF_DURATION);
            color2.a = Interpolation.exp5In.apply(1.0F, 0.0F, (duration - HALF_DURATION) / HALF_DURATION);
            yOffset = Interpolation.circleIn.apply(bounceHeight, 0.0F, (duration - HALF_DURATION) / HALF_DURATION) * Settings.scale;
        } else {
            yOffset = Interpolation.circleOut.apply(0.0F, bounceHeight, duration / HALF_DURATION) * Settings.scale;
        }

        smallParticleTimer -= Gdx.graphics.getDeltaTime();
        if (smallParticleTimer <= 0f) {
            AbstractDungeon.effectsQueue.add(new ChemicalSpillSmallParticleEffect(x, y + yOffset));
            smallParticleTimer = MathUtils.random(0.02f, 0.06f);
        }

        if (targetX > originX) {
            rotation -= Gdx.graphics.getDeltaTime() * 1000.0F;
        } else {
            rotation += Gdx.graphics.getDeltaTime() * 1000.0F;
        }

        scale = Interpolation.pow2Out.apply(1f, 0.25f, duration / DURATION) * Settings.scale;

        if (duration <= 0f) {
            // trigger final splash effect
            isDone = true;
            AbstractDungeon.effectsQueue.add(new ChemicalSpillLargeParticleEffect(targetX, targetY));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        sb.setColor(color);
        sb.draw(poisonAttackImage,
                x - (float) (poisonAttackImage.packedWidth / 2),
                y - (float) (poisonAttackImage.packedHeight / 2) + yOffset,
                (float) poisonAttackImage.packedWidth / 2.0F,
                (float) poisonAttackImage.packedHeight / 2.0F,
                (float) poisonAttackImage.packedWidth,
                (float) poisonAttackImage.packedHeight,
                scale,
                scale,
                rotation);
        sb.setColor(color2);
        sb.draw(poisonAttackImage,
                x - (float) (poisonAttackImage.packedWidth / 2),
                y - (float) (poisonAttackImage.packedHeight / 2) + yOffset,
                (float) poisonAttackImage.packedWidth / 2.0F,
                (float) poisonAttackImage.packedHeight / 2.0F,
                (float) poisonAttackImage.packedWidth,
                (float) poisonAttackImage.packedHeight,
                scale + MathUtils.random(0.01f, 0.02f),
                scale + MathUtils.random(0.01f, 0.02f),
                rotation);
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void dispose() {
    }
}
