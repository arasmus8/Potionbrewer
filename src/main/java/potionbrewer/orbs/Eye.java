package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SneckoOil;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.PlayRandomCardAction;

public class Eye extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Eye");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Eye() {
        super(ORB_ID, img, orbString.NAME, DESC);
        catalyze = true;
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Eye();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new SneckoOil();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new PlayRandomCardAction(p.hand));
    }

    static {
        ImageMaster.loadRelicImg("Snecko Eye", "sneckoEye.png");
        img = ImageMaster.getRelicImg("Snecko Eye");
    }
}