package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.BoundlessPotion;

public class Pyramid extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Pyramid");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Pyramid() {
        super(ORB_ID, img, orbString.NAME, DESC);
        multiDamage = true;
        damageTimes = 2;
        blockTimes = 2;
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Pyramid();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new BoundlessPotion();
    }

    static {
        ImageMaster.loadRelicImg("Runic Pyramid", "runicPyramid.png");
        img = ImageMaster.getRelicImg("Runic Pyramid");
    }
}