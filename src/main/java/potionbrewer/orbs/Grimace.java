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
import com.megacrit.cardcrawl.potions.SteroidPotion;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import potionbrewer.PotionbrewerMod;

public class Grimace extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Grimace");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Grimace() {
        super(ORB_ID, img, orbString.NAME);
        targeted = false;
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Grimace();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new SteroidPotion();
    }

    @Override
    public void doActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 3), 3));
        this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 3), 3));
    }

    @Override
    public String getCardDescription() {
        return super.getCardDescription();
    }

    static {
        ImageMaster.loadRelicImg("GremlinMask", "gremlinMask.png");
        img = ImageMaster.getRelicImg("GremlinMask");
    }
}