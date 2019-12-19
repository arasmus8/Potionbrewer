package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class PaperSwan extends CustomRelic {

    public static final String ID = PotionbrewerMod.makeID(PaperSwan.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PaperSwan.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PaperSwan.png"));

    public PaperSwan() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
