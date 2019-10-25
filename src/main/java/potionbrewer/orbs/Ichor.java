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
import com.megacrit.cardcrawl.potions.SmokeBomb;
import potionbrewer.PotionbrewerMod;

public class Ichor extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Ichor");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Ichor() {
        super(ORB_ID, img, orbString.NAME, DESC);
        damages = true;
        damage = 3;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Ichor();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new SmokeBomb();
    }

    @Override
    public void doAoeDamage(AbstractPlayer p, int amount) {
        this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(amount), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void doDamage(AbstractPlayer p, AbstractMonster m, DamageInfo info) {
        this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    static {
        ImageMaster.loadRelicImg("Black Blood", "blackBlood.png");
        img = ImageMaster.getRelicImg("Black Blood");
    }
}