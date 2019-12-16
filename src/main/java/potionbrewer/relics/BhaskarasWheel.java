package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.Reaction;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class BhaskarasWheel extends CustomRelic {
    private static final int NUM_TURNS = 3;

    public static final String ID = PotionbrewerMod.makeID(BhaskarasWheel.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BhaskarasWheel.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BhaskarasWheel.png"));

    public BhaskarasWheel() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void atTurnStart() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            ++this.counter;
        }

        if (this.counter == 2) {
            this.counter = 0;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new MakeTempCardInDrawPileAction(new Reaction(), 1, true, true));
        }
    }

    public void onEquip() {
        counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + NUM_TURNS + DESCRIPTIONS[1];
    }
}
