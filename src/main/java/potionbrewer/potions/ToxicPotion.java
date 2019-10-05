package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.GreedAction;
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
import potionbrewer.powers.ToxicPower;

public class ToxicPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(ToxicPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.FOREST.cpy();
    public static final Color HYBRID_COLOR = Color.SALMON.cpy();
    public static final Color SPOTS_COLOR = Color.GOLDENROD.cpy();

    public ToxicPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.ANCIENT);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        isThrown = true;
        this.targetRequired = true;
        tips.add(new PowerTip(name, description));
    }
    
    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCreature m = target;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && p != null && m != null) {
            this.addToBot(new ApplyPowerAction(m, p, new ToxicPower(m, p, potency), potency));
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new ToxicPotion();
    }
    
    @Override
    public int getPotency(final int potency) {
        if (AbstractDungeon.player == null) {
            return 1;
        } else {
            return AbstractDungeon.player.hasRelic("SacredBark") ? 2 : 1;
        }
    }
}
