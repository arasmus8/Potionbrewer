package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.BlacksmithPotion;

public class LaserCore extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("LaserCore");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public LaserCore() {
        super(ORB_ID, img, orbString.NAME, DESC);
        damages = true;
        damage = (PotionbrewerMod.turnNumber % 2 == 0) ? 0 : 12;
    }

    @Override
    public void applyPowers() {
        damage = (PotionbrewerMod.turnNumber % 2 == 0) ? 0 : 12;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new LaserCore();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new BlacksmithPotion();
    }

    @Override
    public void doDamage(AbstractPlayer p, AbstractMonster m, DamageInfo info) {
        this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void doAoeDamage(AbstractPlayer p, int damage) {
        this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        if (damages) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.SKY)));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.1F));
        }
    }

    static {
        ImageMaster.loadRelicImg("Cracked Core", "crackedOrb.png");
        img = ImageMaster.getRelicImg("Cracked Core");
    }
}