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
import com.megacrit.cardcrawl.potions.ColorlessPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;

public class Ink extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Ink");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Ink() {
        super(ORB_ID, img, orbString.NAME, DESC);
        damages = true;
        damage = 1;
        if (AbstractDungeon.isPlayerInDungeon()
                && AbstractDungeon.getCurrMapNode() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
        ) {
            if (PotionbrewerMod.turnNumber > 3) {
                damage = 12;
            }
        }
    }

    @Override
    public String getCardDescription(int idx) {
        if (AbstractDungeon.isPlayerInDungeon()
                && AbstractDungeon.getCurrMapNode() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
        ) {
            return getCardDescription(idx, DESC[2]);
        }
        return getCardDescription(idx, DESC[1]);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Ink();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new ColorlessPotion();
    }

    @Override
    public void doDamage(AbstractPlayer p, AbstractMonster m, DamageInfo info) {
        this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void doAoeDamage(AbstractPlayer p, int damage) {
        this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    static {
        ImageMaster.loadRelicImg("InkBottle", "ink_bottle.png");
        img = ImageMaster.getRelicImg("InkBottle");
    }
}