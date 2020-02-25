package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.BottledMiracle;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import potionbrewer.PotionbrewerMod;

public class Radiance extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Radiance");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Radiance() {
        super(ORB_ID, img, orbString.NAME, DESC);
        targeted = false;
        updateDescription();
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Radiance();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new BottledMiracle();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new ApplyPowerAction(p, p, new VigorPower(p, 12), 12));
    }

    static {
        ImageMaster.loadRelicImg("Sundial", "sundial.png");
        img = ImageMaster.getRelicImg("Sundial");
    }
}