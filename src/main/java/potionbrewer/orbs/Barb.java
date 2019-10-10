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
import com.megacrit.cardcrawl.potions.LiquidBronze;
import potionbrewer.PotionbrewerMod;

public class Barb extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Barb");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Barb() {
        super(ORB_ID, img, orbString.NAME);
        damages = true;
        blocks = true;
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Barb();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion(){
        return new LiquidBronze();
    }

    @Override
    public void doActions(AbstractPlayer p, AbstractMonster m) {
        int BLOCK_AMT = 3;
        this.addToBot(new GainBlockAction(p, BLOCK_AMT));
        int DMG_AMT = 4;
        if (m == null) {
            this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(DMG_AMT), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        } else {
            this.addToBot(new DamageAction(m, new DamageInfo(p, DMG_AMT, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Shuriken", "shuriken.png");
        img = ImageMaster.getRelicImg("Shuriken");
    }
}