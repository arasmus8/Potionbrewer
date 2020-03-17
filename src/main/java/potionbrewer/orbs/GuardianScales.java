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
import com.megacrit.cardcrawl.potions.LiquidBronze;
import com.megacrit.cardcrawl.powers.ThornsPower;
import potionbrewer.PotionbrewerMod;

public class GuardianScales extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("GuardianScales");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public GuardianScales() {
        super(ORB_ID, img, orbString.NAME, DESC);
        catalyze = true;
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new GuardianScales();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new LiquidBronze();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, 3), 3));
    }

    static {
        ImageMaster.loadRelicImg("Bronze Scales", "bronzeScales.png");
        img = ImageMaster.getRelicImg("Bronze Scales");
    }
}