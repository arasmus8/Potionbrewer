package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;

public class DummyTonicReagent extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Radiance");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public DummyTonicReagent() {
        super(ORB_ID, img, orbString.NAME, DESC);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new DummyTonicReagent();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return PotionbrewerMod.tonicLibrary.getRandomTonic();
    }

    static {
        ImageMaster.loadRelicImg("Toxic Egg 2", "toxicEgg.png");
        img = ImageMaster.getRelicImg("Toxic Egg 2");
    }
}