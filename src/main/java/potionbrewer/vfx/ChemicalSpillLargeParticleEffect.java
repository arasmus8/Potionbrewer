package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ChemicalSpillLargeParticleEffect extends AbstractGameEffect {
    private ParticleEffect pe;

    public ChemicalSpillLargeParticleEffect(float x, float y) {
        CardCrawlGame.sound.play("ATTACK_POISON", 1.3f);
        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("potionbrewerResources/particles/chemical-splash-large.p"),
                Gdx.files.internal("potionbrewerResources/particles"));
        pe.setPosition(x, y);
        pe.start();
    }

    @Override
    public void update() {
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
