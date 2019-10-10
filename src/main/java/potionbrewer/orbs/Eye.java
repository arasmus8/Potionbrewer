package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SneckoOil;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.PlayRandomCardAction;
import potionbrewer.patches.PotionTracker;

public class Eye extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Eye");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Eye() {
        super(ORB_ID, img, orbString.NAME);
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
        return new Eye();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new SneckoOil();
    }

    @Override
    public void doActions(AbstractPlayer p, AbstractMonster m) {
        if (PotionTracker.potionsUsedThisTurn.get(p) > 0) {
            this.addToBot(new PlayRandomCardAction(p.hand));
        }
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Snecko Eye", "sneckoEye.png");
        img = ImageMaster.getRelicImg("Snecko Eye");
    }
}