package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;

public class AcidPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(AcidPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.GREEN.cpy();
    public static final Color HYBRID_COLOR = Color.BROWN.cpy();
    public static final Color SPOTS_COLOR = Color.RED.cpy();

    public AcidPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.BOLT, PotionColor.POISON);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        isThrown = true;
        tips.add(new PowerTip(name, description));
    }
    
    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCreature m = target;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && p != null && m != null) {
            this.addToBot(new RemoveAllBlockAction(m, p));
            this.addToBot(new DamageAction(m, new DamageInfo(p, potency, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.POISON));
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new AcidPotion();
    }
    
    @Override
    public int getPotency(final int potency) {
        if (AbstractDungeon.player == null) {
            return 5;
        } else {
            return AbstractDungeon.player.hasRelic("SacredBark") ? 10 : 5;
        }
    }
}
