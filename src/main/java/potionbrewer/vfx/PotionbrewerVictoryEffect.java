package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class PotionbrewerVictoryEffect extends AbstractGameEffect {
    private ParticleEffect bubbles1;
    private ParticleEffect bubbles2;
    private float timer1 = 0f;
    private float timer2 = 5f;
    private boolean initialized2 = false;

    public PotionbrewerVictoryEffect() {
        renderBehind = true;
        color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
        bubbles1 = new ParticleEffect();
        bubbles1.load(Gdx.files.internal("potionbrewerResources/particles/victory-bubbles.p"), Gdx.files.internal("potionbrewerResources/particles"));
        bubbles1.setPosition(0f, 0f);
        bubbles2 = new ParticleEffect();
        bubbles2.load(Gdx.files.internal("potionbrewerResources/particles/victory-bubbles.p"), Gdx.files.internal("potionbrewerResources/particles"));
        bubbles2.setPosition(0f, 0f);
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
        timer1 -= dt;
        timer2 -= dt;
        if (timer1 < 0f) {
            bubbles1.start();
        }
        if (timer2 < 0f) {
            initialized2 = true;
            bubbles2.start();
        }
        bubbles1.update(dt);
        bubbles2.update(dt);
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        bubbles1.draw(sb);
        if (initialized2) {
            bubbles2.draw(sb);
        }
    }

    public void dispose() {
        bubbles1.dispose();
        bubbles2.dispose();
    }
}
