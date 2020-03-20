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
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.FreezingPotion;

public class Clay extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Clay");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Clay() {
        super(ORB_ID, img, orbString.NAME, DESC);
        damages = true;
        damage = 3;
        if (AbstractDungeon.isPlayerInDungeon()
                && AbstractDungeon.getCurrMapNode() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
                && PotionbrewerMod.turnNumber > 4
        ) {
            damage = 25;
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (PotionbrewerMod.turnNumber > 4) {
            damage = 25;
        } else {
            damage = 1;
        }
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Clay();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new FreezingPotion();
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
        ImageMaster.loadRelicImg("Self Forming Clay", "clay.png");
        img = ImageMaster.getRelicImg("Self Forming Clay");
    }
}