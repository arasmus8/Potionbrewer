package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
        if (AbstractDungeon.isPlayerInDungeon()
                && AbstractDungeon.getCurrMapNode() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
        ) {
            if (PotionbrewerMod.turnNumber % 2 == 0) {
                damages = true;
                damage = 8;
            } else {
                blocks = true;
                block = 5;
                targeted = false;
            }
        } else {
            damages = true;
            blocks = true;
        }
    }

    @Override
    public String getCardDescription(int idx) {
        if (AbstractDungeon.isPlayerInDungeon()
                && AbstractDungeon.getCurrMapNode() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
        ) {
            return getCardDescription(idx, PotionbrewerMod.turnNumber % 2 == 0 ? DESC[3] : DESC[2]);
        }
        return getCardDescription(idx, DESC[1]);
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
        this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void doAoeDamage(AbstractPlayer p, int damage) {
        this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void doBlock(AbstractPlayer p, int amount) {
        addToBot(new GainBlockAction(p, amount));
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