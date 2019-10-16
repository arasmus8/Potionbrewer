package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import potionbrewer.PotionbrewerMod;

public class Ichor extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Ichor");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Ichor() {
        super(ORB_ID, img, orbString.NAME, DESC);
        catalyze = true;
        blocks = true;
        block = 10;
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Ichor();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new SmokeBomb();
    }

    @Override
    public void doBlock(AbstractPlayer p, int block) {
        this.addToBot(new GainBlockAction(p, block));
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Black Blood", "blackBlood.png");
        img = ImageMaster.getRelicImg("Black Blood");
    }
}