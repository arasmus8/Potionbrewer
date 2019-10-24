package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PoisonPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.Reaction;

public class Skull extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Skull");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Skull() {
        super(ORB_ID, img, orbString.NAME, DESC);
        targeted = false;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Skull();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new PoisonPotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(new Reaction(), 1, true, true));
    }

    static {
        ImageMaster.loadRelicImg("Red Skull", "red_skull.png");
        img = ImageMaster.getRelicImg("Red Skull");
    }
}