package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class BunsenBurner extends CustomRelic {

    public static final String ID = PotionbrewerMod.makeID(BunsenBurner.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BunsenBurner.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BunsenBurner.png"));

    public BunsenBurner() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
