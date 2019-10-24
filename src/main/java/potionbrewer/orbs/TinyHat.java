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
import com.megacrit.cardcrawl.potions.DuplicationPotion;
import potionbrewer.PotionbrewerMod;

public class TinyHat extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("TinyHat");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public TinyHat() {
        super(ORB_ID, img, orbString.NAME, DESC);
        damages = true;
        damage = 18;
        catalyze = true;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new TinyHat();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new DuplicationPotion();
    }

    @Override
    public void doAoeDamage(AbstractPlayer p, int amount) {
        this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(amount), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SMASH));
    }

    @Override
    public void doDamage(AbstractPlayer p, AbstractMonster m, DamageInfo info) {
        this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.SMASH));
    }

    static {
        ImageMaster.loadRelicImg("Bowler Hat", "bowlerHat.png");
        img = ImageMaster.getRelicImg("Bowler Hat");
    }
}