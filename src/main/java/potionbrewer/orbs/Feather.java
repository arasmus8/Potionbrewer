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
import com.megacrit.cardcrawl.potions.GamblersBrew;
import com.megacrit.cardcrawl.powers.StrengthPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeOrbPath;

public class Feather extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Feather");
    private static final Texture IMG = TextureLoader.getTexture(makeOrbPath("default_orb.png"));
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Feather() {
        super(ORB_ID, img, orbString.NAME, DESC);
        exhaust = true;
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Feather();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new GamblersBrew();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
    }

    static {
        ImageMaster.loadRelicImg("Eternal Feather", "eternal_feather.png");
        img = ImageMaster.getRelicImg("Eternal Feather");
    }
}
