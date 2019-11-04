package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class SalesContract extends CustomRelic {

    public static final String ID = PotionbrewerMod.makeID(SalesContract.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SalesContract.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SalesContract.png"));

    public SalesContract() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }

    public static final int GOLD_AMOUNT = 20;

    /* See SalesContractPatches */

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
