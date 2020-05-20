package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.tonics.SwiftTonic;

public class Iodine extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Iodine");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Iodine() {
        super(ORB_ID, img, orbString.NAME, DESC);
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Iodine();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new SwiftTonic();
    }

    @Override
    public void doBlock(AbstractPlayer p, int amount) {
        this.addToBot(new GainBlockAction(p, amount));
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, 1));
    }

    static {
        ImageMaster.loadRelicImg("Blood of Gifted", "bloodOfGifted.png");
        img = ImageMaster.getRelicImg("Blood of Gifted");
    }
}