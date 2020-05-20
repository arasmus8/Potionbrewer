package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.PowerTip;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class RamsHorn extends CustomRelic {
    public static final String ID = PotionbrewerMod.makeID(RamsHorn.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("RamsHorn.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("RamsHorn.png"));

    public RamsHorn() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.SOLID);
        tips.clear();
        tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
