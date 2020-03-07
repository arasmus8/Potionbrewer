package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class PotionbrewerVictoryEffect extends AbstractGameEffect {
    private static AtlasRegion img;
    private static AtlasRegion img2;

    private static int COLUMNS = 7;
    private static int ROWS = 5;

    public PotionbrewerVictoryEffect() {
        renderBehind = true;
        Texture texture = ImageMaster.loadImage("potionbrewerResources/images/char/potionbrewer/orb/new2.png");
        img2 = new AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        texture = ImageMaster.loadImage("potionbrewerResources/images/char/potionbrewer/orb/new3.png");
        img = new AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        scale = 1.5F * Settings.scale;
        color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
    }

    public void update() {
        color.a = MathHelper.slowColorLerpSnap(color.a, 1.0F);
        duration += Gdx.graphics.getDeltaTime();
    }

    private void renderHelper(SpriteBatch sb, int index) {
        float cX = Settings.WIDTH / 2f;
        float cY = Settings.HEIGHT / 2f;
        // float x = Interpolation.linear.apply(100f, Settings.WIDTH - 100f, (index % COLUMNS) / (float)(COLUMNS - 1));
        float x = Interpolation.linear.apply(cX - 400f, cX + 400f, (index % COLUMNS) / (float) (COLUMNS - 1));
        x += 12f * MathUtils.sin(duration * 0.92f + index / 13f);
        x *= Settings.scale;
        // float y = Interpolation.linear.apply(100f, Settings.HEIGHT - 100f, Math.floorDiv(index, COLUMNS) / (float)ROWS);
        float y = Interpolation.linear.apply(cY + 350f, cY - 350f, Math.floorDiv(index, COLUMNS) / (float) ROWS);
        y += 4f * MathUtils.sin(duration + index / 13f);
        y *= Settings.scale;
        sb.draw(getImg(index),
                x,
                y,
                (float) img.packedWidth / 2.0F - x,
                (float) img.packedHeight / 2.0F - y,
                (float) img.packedWidth,
                (float) img.packedHeight,
                scale,
                scale,
                1.0f);
    }

    private AtlasRegion getImg(int input) {
        if (input % 4 == 0) {
            return img2;
        } else {
            return img;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(color);

        for (int i = 0; i < COLUMNS * ROWS; ++i) {
            renderHelper(sb, i);
        }

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
