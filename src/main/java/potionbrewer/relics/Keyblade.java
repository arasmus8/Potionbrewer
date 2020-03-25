package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class Keyblade extends CustomRelic {

    public static final String ID = PotionbrewerMod.makeID(Keyblade.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Keyblade.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Keyblade.png"));

    public Keyblade() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.SOLID);
    }

    private int keyCount() {
        int c = 0;
        if (Settings.hasEmeraldKey) c += 1;
        if (Settings.hasRubyKey) c += 1;
        if (Settings.hasSapphireKey) c += 1;
        counter = c;
        return c;
    }

    @Override
    public void onEquip() {
        keyCount();
    }

    public void onKeyObtain() {
        keyCount();
    }

    @Override
    public void atBattleStart() {
        flash();
        int keyCount = keyCount();
        if (keyCount > 0) {
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, keyCount), keyCount));
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }

    @Override
    public boolean canSpawn() {
        return Settings.isFinalActAvailable;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
