package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;

public class FreezingPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(FreezingPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.BLACK.cpy();
    public static final Color HYBRID_COLOR = Color.SKY.cpy();
    public static final Color SPOTS_COLOR = Color.CLEAR.cpy();

    public FreezingPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.FAIRY, PotionColor.POWER);
        this.isThrown = true;
        this.targetRequired = true;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = String.format(DESCRIPTIONS[0], potency);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new SlowPower(target, potency), potency));
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new FreezingPotion();
    }
    
    @Override
    public int getPotency(final int potency) {
        return 5;
    }
}
