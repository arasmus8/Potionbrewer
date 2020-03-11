package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CollectEffect extends AbstractGameEffect {
    private float sX;
    private float sY;
    private float tX;
    private float tY;
    private float x;
    private float y;

    private Texture img;

    private static float DURATION = 0.75f;
    private static float SHRINK_DUR = 0.25f;
    private static float SUCK_DUR = 0.5f;

    public CollectEffect(float sX, float sY, float tX, float tY) {
        duration = startingDuration = DURATION;
        x = tX;
        y = tY;
        this.sX = sX;
        this.sY = sY;
        this.tX = tX;
        this.tY = tY;
        color = new Color(0.91f, 0.8f, 0.27f, 0.8f);
        img = ImageMaster.ORB_PLASMA;
    }

    @Override
    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        if (duration > SUCK_DUR) {
            // shrinking
            float dt = (duration - SUCK_DUR) / SHRINK_DUR;
            x = tX;
            y = tY;
            scale = Interpolation.exp5In.apply(1f, 15f, dt);
        } else {
            // sucking
            float dt = duration / SUCK_DUR;
            x = Interpolation.exp5In.apply(sX, tX, dt);
            y = Interpolation.exp5In.apply(sY, tY, dt);
            scale = Interpolation.elasticIn.apply(0.5f, 1f, dt);
        }
        rotation = MathUtils.sin(2.356f / duration);
        if (duration <= 0f) {
            isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sb.draw(
                img,
                x - img.getWidth() / 2f,
                y - img.getHeight() / 2f,
                img.getWidth() / 2f,
                img.getHeight() / 2f,
                img.getWidth(),
                img.getHeight(),
                scale,
                scale,
                rotation,
                0,
                0,
                img.getWidth(),
                img.getHeight(),
                false,
                false
        );
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
    }

    @Override
    public void dispose() {
    }
}
