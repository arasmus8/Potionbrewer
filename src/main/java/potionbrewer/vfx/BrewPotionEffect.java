package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import potionbrewer.powers.BrewPotionPower;


public class BrewPotionEffect extends AbstractGameEffect {
    private static Texture jar;
    private static Texture liquid;
    private static Color semiTransparent = new Color(1, 1, 1, 0.8f);
    private static Color mostlyTransparent = new Color(1, 1, 1, 0.2f);

    private boolean isInitialized = false;
    private float x;
    private float y;
    private final float[] locations = {0f, -12f, 12f, -8f, 8f, -13f, 13f};
    private float[] xOffsets;
    private float[] yOffsets;
    private float angle;

    public BrewPotionEffect() {
        scale = 0.4f * Settings.scale;
        x = AbstractDungeon.player.hb.x + AbstractDungeon.player.hb.width / 9f;
        y = AbstractDungeon.player.hb.y + 20f;
    }

    @Override
    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
        if (!isInitialized) {
            isInitialized = true;
            for (int i = 0; i < 30; ++i) {
                AbstractDungeon.effectsQueue.add(new BrewPotionSmokeEffect(x, y));
            }
        }
        int reagentCount = BrewPotionPower.reagents.size();
        if (reagentCount == 0) {
            isDone = true;
        } else {
            angle = MathUtils.clamp(angle + dt, 0f, 360f);
            float rad = MathUtils.sin(dt) * 10f;
            xOffsets = new float[reagentCount];
            yOffsets = new float[reagentCount];
            for (int i = 0; i < reagentCount; i++) {
                xOffsets[i] = locations[i] + MathUtils.cos(angle + i * 13f) * rad * 4f * Settings.scale;
                yOffsets[i] = MathUtils.cos(angle + i * 17f) * rad * 16f * Settings.scale;
                if (i > 2) {
                    yOffsets[i] -= 12f;
                }
            }
        }
    }

    private void drawTexture(SpriteBatch sb, Texture t, float x, float y, float s, float rot) {
        sb.draw(
                t,
                x - t.getWidth() / 2f,
                y - t.getHeight() / 2f,
                t.getWidth() / 2f,
                t.getHeight() / 2f,
                t.getWidth(),
                t.getHeight(),
                s,
                s,
                rot,
                0,
                0,
                t.getWidth(),
                t.getHeight(),
                false,
                false
        );
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        drawTexture(sb, liquid, x, y, scale, 0f);
        sb.setColor(semiTransparent);
        for (int i = 0; i < BrewPotionPower.reagents.size(); i++) {
            drawTexture(sb, BrewPotionPower.reagents.get(i).getTexture(), x + xOffsets[i] * Settings.scale, y + yOffsets[i] * Settings.scale, scale * 0.7f, (float) (0.1 * i));
        }
        sb.setColor(mostlyTransparent);
        drawTexture(sb, liquid, x, y, scale, 0f);
        sb.setColor(Color.WHITE);
        drawTexture(sb, jar, x, y, scale, 0f);
    }

    @Override
    public void dispose() {
    }

    static {
        jar = ImageMaster.loadImage("potionbrewerResources/images/char/potionbrewer/orb/new4.png");
        liquid = ImageMaster.loadImage("potionbrewerResources/images/char/potionbrewer/orb/liquid.png");
    }
}
