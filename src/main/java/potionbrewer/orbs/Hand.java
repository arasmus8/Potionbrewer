package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.BloodPotion;
import potionbrewer.PotionbrewerMod;

public class Hand extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Hand");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Hand() {
        super(ORB_ID, img, orbString.NAME, DESC);
        multiDamage = true;
        damageTimes = 2;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Hand();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new BloodPotion();
    }

    static {
        ImageMaster.loadRelicImg("Mummified Hand", "mummifiedHand.png");
        img = ImageMaster.getRelicImg("Mummified Hand");
    }
}