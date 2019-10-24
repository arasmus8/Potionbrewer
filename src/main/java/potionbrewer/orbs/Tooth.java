package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.ExplosivePotion;
import potionbrewer.PotionbrewerMod;

public class Tooth extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Tooth");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Tooth() {
        super(ORB_ID, img, orbString.NAME, DESC);
        damages = true;
        damage = 6;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tooth();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new ExplosivePotion();
    }

    @Override
    public void doAoeDamage(AbstractPlayer p, int amount) {
        this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(amount), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void doDamage(AbstractPlayer p, AbstractMonster m, DamageInfo info) {
        this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    static {
        ImageMaster.loadRelicImg("Spear Head", "spearHead.png");
        img = ImageMaster.getRelicImg("Spear Head");
    }
}