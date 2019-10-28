package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.EndurancePotion;

public class Ichor extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Ichor");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Ichor() {
        super(ORB_ID, img, orbString.NAME, DESC);
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
        return new EndurancePotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddTemporaryHPAction(p, p, 8));
    }

    static {
        ImageMaster.loadRelicImg("Black Blood", "blackBlood.png");
        img = ImageMaster.getRelicImg("Black Blood");
    }
}