package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.StrengthPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import potionbrewer.PotionbrewerMod;

public class Jaw extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Jaw");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Jaw() {
        super(ORB_ID, img, orbString.NAME, DESC);
        damages = true;
        blocks = true;
        damage = 0;
        block = 0;
        if (AbstractDungeon.isPlayerInDungeon()
                && AbstractDungeon.getCurrMapNode() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
        ) {
            if (GameActionManager.turn % 2 == 0) {
                damage = 8;
            } else {
                block = 5;
            }
        }
    }

    @Override
    public void applyPowers() {
        if (GameActionManager.turn % 2 == 0) {
            damage = 8;
            block = 0;
        } else {
            damage = 0;
            block = 5;
        }
    }

    @Override
    public String getCardDescription(int idx) {
        if (
                AbstractDungeon.isPlayerInDungeon() &&
                        AbstractDungeon.currMapNode != null &&
                        AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                        !AbstractDungeon.getMonsters().areMonstersBasicallyDead()
        ) {
            return getCardDescription(idx, GameActionManager.turn % 2 == 0 ? DESC[3] : DESC[2]);
        } else {
            return descriptions[1];
        }
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Jaw();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new StrengthPotion();
    }

    @Override
    public void doDamage(AbstractPlayer p, AbstractMonster m, DamageInfo info) {
        if (damage > 0) {
            this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void doAoeDamage(AbstractPlayer p, int damage) {
        if (damage > 0) {
            this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void doBlock(AbstractPlayer p, int amount) {
        if (block > 0) {
            addToBot(new GainBlockAction(p, amount));
        }
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        if (damages) {
            this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
        }
    }

    static {
        ImageMaster.loadRelicImg("MawBank", "bank.png");
        img = ImageMaster.getRelicImg("MawBank");
    }
}