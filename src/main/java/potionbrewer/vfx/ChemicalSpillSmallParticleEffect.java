package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ChemicalSpillSmallParticleEffect extends AbstractGameEffect {
    private ParticleEffect pe;

    public ChemicalSpillSmallParticleEffect(float x, float y) {
        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("potionbrewerResources/particles/chemical-splash-small.p"),
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
    }
}
