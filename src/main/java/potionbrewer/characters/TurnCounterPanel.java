package potionbrewer.characters;

import basemod.ClickableUIElement;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeID;

public class TurnCounterPanel extends ClickableUIElement {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID(TurnCounterPanel.class.getSimpleName()));
    public static final String[] TEXT = uiStrings.TEXT;

    private static final float WIDTH = 128f;
    private static final float HEIGHT = 68f;

    public TurnCounterPanel() {
        super(
                TextureLoader.getTexture("potionbrewerResources/images/ui/turnCounter.png"),
                Settings.WIDTH - (WIDTH * 1.125f) * Settings.xScale,
                Settings.HEIGHT - (HEIGHT * 3f) * Settings.yScale,
                WIDTH * Settings.xScale,
                HEIGHT * Settings.yScale
        );
    }

    @Override
    protected void onHover() {
    }

    @Override
    protected void onUnhover() {
    }

    @Override
    protected void onClick() {
    }

    @Override
    public void render(SpriteBatch sb) {
        if (CardCrawlGame.isInARun() &&
                AbstractDungeon.currMapNode != null &&
                AbstractDungeon.getCurrRoom() != null &&
                AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            super.render(sb);
            String msg = Integer.toString(GameActionManager.turn);
            AbstractDungeon.player.getEnergyNumFont().getData().setScale(1f);
            FontHelper.renderFontCentered(sb,
                    AbstractDungeon.player.getEnergyNumFont(),
                    msg,
                    hitbox.x + hitbox.width / 2f + 16f * Settings.scale,
                    hitbox.y + hitbox.height / 2f,
                    Color.CYAN);
            if (hitbox.hovered && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp) {
                TipHelper.renderGenericTip(hitbox.x - WIDTH * 1.5f * Settings.xScale, hitbox.y - 40f * Settings.yScale, TEXT[0], TEXT[1]);
            }
        }
    }
}
