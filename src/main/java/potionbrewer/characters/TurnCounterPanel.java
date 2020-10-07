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

    public TurnCounterPanel() {
        super(
                TextureLoader.getTexture("potionbrewerResources/images/ui/turnCounter.png"),
                164f,
                278f,
                128f,
                68f
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
            TipHelper.renderGenericTip(hitbox.x + 0f * Settings.scale, hitbox.y + 128f * Settings.scale, TEXT[0], TEXT[1]);
        }
    }
}
