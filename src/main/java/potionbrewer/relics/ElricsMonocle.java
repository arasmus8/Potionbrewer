package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;


public class ElricsMonocle extends CustomRelic implements ClickableRelic {

    public static final String ID = PotionbrewerMod.makeID(ElricsMonocle.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ElricsMonocle.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ElricsMonocle.png"));

    public ElricsMonocle() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (!this.grayscale && !AbstractDungeon.getCurrRoom().isBattleEnding()) {
                grayscale = true;
                int cardCount = AbstractDungeon.player.hand.size();
                addToTop(new ExhaustAction(AbstractDungeon.player.hand.size(), true, true));
                for (int i = 0; i < cardCount; i++) {
                    AbstractCard c = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();
                    c.upgrade();
                    addToBot(new MakeTempCardInHandAction(c));
                }
            }
        }
    }

    @Override
    public void onVictory() {
        grayscale = false;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}