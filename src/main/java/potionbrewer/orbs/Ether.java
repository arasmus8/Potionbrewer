package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.EnergyPotion;
import potionbrewer.PotionbrewerMod;

public class Ether extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Ether");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Ether() {
        super(ORB_ID, img, orbString.NAME, DESC);
        exhaust = true;
        targeted = false;
        updateDescription();
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Ether();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new EnergyPotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(1));
    }

    static {
        ImageMaster.loadRelicImg("Bottled Tornado", "bottledTornado.png");
        img = ImageMaster.getRelicImg("Bottled Tornado");
    }
}