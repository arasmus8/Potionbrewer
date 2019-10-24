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
import com.megacrit.cardcrawl.potions.HeartOfIron;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import potionbrewer.PotionbrewerMod;

public class Crown extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Crown");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Crown() {
        super(ORB_ID, img, orbString.NAME, DESC);
        exhaust = true;
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Crown();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new HeartOfIron();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, 6), 6));
    }

    static {
        ImageMaster.loadRelicImg("Busted Crown", "crown.png");
        img = ImageMaster.getRelicImg("Busted Crown");
    }
}