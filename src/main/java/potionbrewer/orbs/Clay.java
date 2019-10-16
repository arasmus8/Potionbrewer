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
import com.megacrit.cardcrawl.powers.TheBombPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.FreezingPotion;

public class Clay extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Clay");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Clay() {
        super(ORB_ID, img, orbString.NAME, DESC);
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Clay();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new FreezingPotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new TheBombPower(p, 2, 30), 2));
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Self Forming Clay", "clay.png");
        img = ImageMaster.getRelicImg("Self Forming Clay");
    }
}