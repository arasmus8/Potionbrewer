package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.StrengthPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeOrbPath;

public class Horn extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Horn");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Horn() {
        super(ORB_ID, img, orbString.NAME);
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Horn();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new StrengthPotion();
    }

    static {
        ImageMaster.loadRelicImg("Gremlin Horn", "gremlinHorn.png");
        img = ImageMaster.getRelicImg("Gremlin Horn");
    }
}