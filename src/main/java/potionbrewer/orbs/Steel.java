package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.EssenceOfSteel;
import potionbrewer.PotionbrewerMod;

public class Steel extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Steel");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Steel() {
        super(ORB_ID, img, orbString.NAME, DESC);
        blocks = true;
        block = 10;
        targeted = false;
        catalyze = true;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Steel();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new EssenceOfSteel();
    }

    @Override
    public void doBlock(AbstractPlayer p, int amount) {
        this.addToBot(new GainBlockAction(p, amount));
    }

    static {
        ImageMaster.loadRelicImg("Orichalcum", "orichalcum.png");
        img = ImageMaster.getRelicImg("Orichalcum");
    }
}