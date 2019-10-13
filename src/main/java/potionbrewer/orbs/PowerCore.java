package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.LiquidBronze;
import potionbrewer.PotionbrewerMod;
import potionbrewer.patches.PotionTracker;

public class PowerCore extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("PowerCore");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public PowerCore() {
        super(ORB_ID, img, orbString.NAME);
        blocks = true;
        catalyze = true;
        targeted = false;
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new PowerCore();
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
    public void doBlock(AbstractPlayer p, int amount) {
            this.addToBot(new GainBlockAction(p, amount));
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Nuclear Battery", "battery.png");
        img = ImageMaster.getRelicImg("Nuclear Battery");
    }
}