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
import com.megacrit.cardcrawl.potions.AttackPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.powers.BilePower;

public class Bile extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Bile");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    private static final int AMOUNT = 3;

    public Bile() {
        super(ORB_ID, img, orbString.NAME, DESC);
        targeted = false;
        updateDescription();
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Bile();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new AttackPotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new BilePower(p, AMOUNT), AMOUNT));
    }

    static {
        ImageMaster.loadRelicImg("Blood Vial", "blood_vial.png");
        img = ImageMaster.getRelicImg("Blood Vial");
    }
}