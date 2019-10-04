package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.AttackPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeOrbPath;

public class Bile extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Bile");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Bile() {
        super(ORB_ID, img, orbString.NAME);
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Bile();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new AttackPotion();
    }

    static {
        ImageMaster.loadRelicImg("Blood Vial", "blood_vial.png");
        img = ImageMaster.getRelicImg("Blood Vial");
    }
}