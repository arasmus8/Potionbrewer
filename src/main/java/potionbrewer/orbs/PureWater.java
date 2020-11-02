package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.RagePower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.tonics.BlockTonic;

public class PureWater extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("PureWater");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public PureWater() {
        super(ORB_ID, img, orbString.NAME, DESC);
        targeted = false;
        blocks = true;
        block = 3;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new PureWater();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new BlockTonic();
    }

    @Override
    public void doBlock(AbstractPlayer p, int amount) {
        this.addToBot(new ApplyPowerAction(p, p, new RagePower(p, 3), 3));
    }

    static {
        ImageMaster.loadRelicImg("PureWater", "clean_water.png");
        img = ImageMaster.getRelicImg("PureWater");
    }
}