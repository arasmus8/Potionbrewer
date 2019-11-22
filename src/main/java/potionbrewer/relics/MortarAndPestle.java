package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class MortarAndPestle extends CustomRelic implements DiscardPotionRelic {

    public static final String ID = PotionbrewerMod.makeID(MortarAndPestle.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MortarAndPestle.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MortarAndPestle.png"));

    public MortarAndPestle() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void onDiscardPotion() {
        AbstractDungeon.player.heal(3, true);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
