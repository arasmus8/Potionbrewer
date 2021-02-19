package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ReagentSparkleEffect extends AbstractGameEffect {
    private float x;

    private float y;

    private final float vX = MathUtils.random(-4.0F, 4.0F) * Settings.scale;

    private final float vY = MathUtils.random(-4.0F, 16.0F) * Settings.scale;

    private final float aV;

    public ReagentSparkleEffect(float x, float y) {
        float colorTmp = MathUtils.random(0.6F, 1.0F);
        color = new Color(colorTmp - 0.3F, colorTmp, colorTmp + MathUtils.random(-0.1F, 0.1F), 0.0F);
        color.a = 0.0F;
        aV = MathUtils.random(-120.0F, 120.0F);
        this.x = x + MathUtils.random(-32, 32) * Settings.scale;
        this.y = y + MathUtils.random(-60f, 40f) * Settings.scale;
        scale = MathUtils.random(0.25f, 0.5f) * Settings.scale;
        startingDuration = duration = MathUtils.random(1.0f, 3f);
    }

    public void update() {
        rotation += aV * Gdx.graphics.getDeltaTime();
        duration -= Gdx.graphics.getDeltaTime();
        x += vX * Gdx.graphics.getDeltaTime();
        y += vY * Gdx.graphics.getDeltaTime();
        if (duration < 0.0F)
            isDone = true;
        if (duration > startingDuration / 2.0F) {
            float tmp = duration - startingDuration / 2.0F;
            color.a = Interpolation.fade.apply(0.0F, 1.0F, startingDuration / 2.0F - tmp) / 4.0F;
        } else {
            color.a = Interpolation.fade.apply(0.0F, 1.0F, duration / startingDuration / 2.0F) / 4.0F;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(ImageMaster.WOBBLY_ORB_VFX,
                x - 16.0F,
                y - 16.0F,
                16.0F,
                16.0F,
                32.0F,
                32.0F,
                scale * MathUtils.random(1.0F, 1.2F),
                scale / 4.0F,
                0.0F,
                0,
                0,
                32,
                32,
                false,
                false);
        sb.draw(ImageMaster.WOBBLY_ORB_VFX,
                x - 16.0F,
                y - 16.0F,
                16.0F,
                16.0F,
                32.0F,
                32.0F,
                scale * MathUtils.random(1.0F, 1.5F),
                scale / 4.0F,
                90.0F,
                0,
                0,
                32,
                32,
                false,
                false);
    }

    public void dispose() {
    }
}
