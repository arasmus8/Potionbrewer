package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.BlockPotion;
import com.megacrit.cardcrawl.powers.WeakPower;
import potionbrewer.PotionbrewerMod;

public class Pebble extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Pebble");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Pebble() {
        super(ORB_ID, img, orbString.NAME, DESC);
        blocks = true;
        block = 4;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Pebble();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new BlockPotion();
    }

    @Override
    public void doBlock(AbstractPlayer p, int amount) {
        this.addToBot(new GainBlockAction(p, amount));
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1, false), 1));
    }

    static {
        ImageMaster.loadRelicImg("Oddly Smooth Stone", "smooth_stone.png");
        img = ImageMaster.getRelicImg("Oddly Smooth Stone");
    }
}