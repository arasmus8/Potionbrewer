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
import com.megacrit.cardcrawl.powers.ChokePower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;

public class NoxiousPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(NoxiousPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.FIREBRICK.cpy();
    public static final Color HYBRID_COLOR = Color.BROWN.cpy();
    public static final Color SPOTS_COLOR = Color.BLACK.cpy();

    public NoxiousPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.H, PotionColor.POISON);
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
            this.addToBot(new ApplyPowerAction(m, p, new ChokePower(m, potency), potency));
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new NoxiousPotion();
    }
    
    @Override
    public int getPotency(final int potency) {
        if (AbstractDungeon.player == null) {
            return 4;
        } else {
            return AbstractDungeon.player.hasRelic("SacredBark") ? 8 : 4;
        }
    }
}
