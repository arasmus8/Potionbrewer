package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FirePotion;
import potionbrewer.PotionbrewerMod;

public class Sulphur extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Sulphur");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Sulphur() {
        super(ORB_ID, img, orbString.NAME);
        blocks = true;
        damages = true;
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Sulphur();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new FirePotion();
    }

    @Override
    public void doActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, 8));
        if (m == null) {
            this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(8), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        } else {
            this.addToBot(new DamageAction(m, new DamageInfo(p, 8, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    @Override
    public String getCardDescription() {
        return super.getCardDescription();
    }

    static {
        ImageMaster.loadRelicImg("Brimstone", "brimstone.png");
        img = ImageMaster.getRelicImg("Brimstone");
    }
}