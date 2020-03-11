package potionbrewer.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.SmokeBlurEffect;

public class BlindingSprayEffect extends AbstractGameEffect {
    private float x;
    private float y;

    public BlindingSprayEffect(float x, float y) {
        this.x = x;
        this.y = y;
        duration = 0.2F;
    }

    public void update() {
        if (duration == 0.2F) {
            CardCrawlGame.sound.play("SPORE_CLOUD_RELEASE");

            for (int i = 0; i < 30; ++i) {
                AbstractDungeon.effectsQueue.add(new SmokeBlurEffect(x, y));
            }
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            CardCrawlGame.sound.play("DEBUFF_1");
            isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
