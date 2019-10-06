package potionbrewer.potions.tonics;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.AcidPotion;

public class FireTonic extends AbstractPotion {

    public static final String ID = PotionbrewerMod.makeID(FireTonic.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.GREEN.cpy();
    public static final Color HYBRID_COLOR = Color.BROWN.cpy();
    public static final Color SPOTS_COLOR = Color.RED.cpy();

    public FireTonic() {
        super(NAME, ID, PotionRarity.COMMON, PotionSize.BOTTLE, PotionColor.FIRE);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        this.isThrown = true;
        this.targetRequired = true;
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature m) {
        AbstractPlayer p = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && p != null && m != null) {
            DamageInfo info = new DamageInfo(AbstractDungeon.player, this.potency, DamageInfo.DamageType.THORNS);
            info.applyEnemyPowersOnly(m);
            this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public int getPotency(int i) {
        int p = 6;
        if( AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark") ) {
            p *= 2;
        }
        return p;
    }

    @Override
    public AbstractPotion makeCopy() {
        return null;
    }
}
