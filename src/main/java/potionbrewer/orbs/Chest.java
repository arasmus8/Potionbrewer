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
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.MidasPotion;

public class Chest extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Chest");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Chest() {
        super(ORB_ID, img, orbString.NAME, DESC);
        damages = true;
        damage = (AbstractDungeon.player != null) ? AbstractDungeon.player.gold / 10 : 0;
    }

    @Override
    public void applyPowers() {
        damage = (AbstractDungeon.player != null) ? AbstractDungeon.player.gold / 10 : 0;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Chest();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new MidasPotion();
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
            for (int i = 0; i < 10; ++i) {// 39
                AbstractDungeon.effectList.add(new GainPennyEffect(p, p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, false));
            }
        }
    }

    static {
        ImageMaster.loadRelicImg("Tiny Chest", "tinyChest.png");
        img = ImageMaster.getRelicImg("Tiny Chest");
    }
}