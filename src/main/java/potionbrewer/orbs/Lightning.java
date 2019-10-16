package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.ExplosivePotion;
import potionbrewer.PotionbrewerMod;

public class Lightning extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Lightning");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Lightning() {
        super(ORB_ID, img, orbString.NAME, DESC);
        aoeDamage = true;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Lightning();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new ExplosivePotion();
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Bottled Lightning", "bottledLightning.png");
        img = ImageMaster.getRelicImg("Bottled Lightning");
    }
}