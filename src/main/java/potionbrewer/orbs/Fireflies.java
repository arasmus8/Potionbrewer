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
import com.megacrit.cardcrawl.potions.FirePotion;
import com.megacrit.cardcrawl.powers.DexterityPower;
import potionbrewer.PotionbrewerMod;

public class Fireflies extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Fireflies");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Fireflies() {
        super(ORB_ID, img, orbString.NAME, DESC);
        catalyze = true;
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Fireflies();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new FirePotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
    }

    static {
        ImageMaster.loadRelicImg("NeowsBlessing", "lament.png");
        img = ImageMaster.getRelicImg("NeowsBlessing");
    }
}