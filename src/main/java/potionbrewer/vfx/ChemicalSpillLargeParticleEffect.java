package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ChemicalSpillLargeParticleEffect extends AbstractGameEffect {
    private ParticleEffect pe;
    private boolean soundPlayed;

    public ChemicalSpillLargeParticleEffect(float x, float y) {
        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("potionbrewerResources/particles/chemical-splash-large.p"),
                Gdx.files.internal("potionbrewerResources/particles"));
        pe.setPosition(x, y);
        pe.start();
        soundPlayed = false;
    }

    @Override
    public void update() {
        if (!soundPlayed) {
            soundPlayed = true;
            CardCrawlGame.sound.playA("ATTACK_POISON2", MathUtils.random(0.0F, 0.1F));
        }
        pe.update(Gdx.graphics.getDeltaTime());
        if (pe.isComplete()) {
            isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        pe.draw(sb);
    }

    @Override
    public void dispose() {
        pe.dispose();
    }
}
