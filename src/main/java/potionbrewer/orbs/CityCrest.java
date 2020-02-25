package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.BoundlessPotion;

public class CityCrest extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("CityCrest");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public CityCrest() {
        super(ORB_ID, img, orbString.NAME, DESC);
        damages = true;
        damage = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new CityCrest();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new BoundlessPotion();
    }

    @Override
    public void doDamage(AbstractPlayer p, AbstractMonster m, DamageInfo info) {
        this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void doAoeDamage(AbstractPlayer p, int damage) {
        this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    static {
        ImageMaster.loadRelicImg("StoneCalendar", "calendar.png");
        img = ImageMaster.getRelicImg("StoneCalendar");
    }
}