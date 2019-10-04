package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class PlaceholderRelic extends CustomRelic {
    
    public static final String ID = PotionbrewerMod.makeID("PlaceholderRelic");
    
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    
    public PlaceholderRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }
    
    @Override
    public void atBattleStartPreDraw() {
        flash();
    }
    
    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster += 1;
    }
    
    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster -= 1;
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
