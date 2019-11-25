package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.StunPotion;

public class Storybook extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Storybook");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Storybook() {
        super(ORB_ID, img, orbString.NAME, DESC);
        exhaust = true;
        updateDescription();
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Storybook();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new StunPotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new StunMonsterPower(m, 1), 1));
    }

    static {
        ImageMaster.loadRelicImg("Enchiridion", "enchiridion.png");
        img = ImageMaster.getRelicImg("Enchiridion");
    }
}