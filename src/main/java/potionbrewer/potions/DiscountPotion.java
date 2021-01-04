package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.unique.MadnessAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;

public class DiscountPotion extends AbstractPotion {
    
    public static final String POTION_ID = PotionbrewerMod.makeID(DiscountPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.YELLOW;
    public static final Color HYBRID_COLOR = Color.WHITE;
    public static final Color SPOTS_COLOR = Color.CLEAR;

    public DiscountPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.SMOKE);
        isThrown = false;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        if (potency > 1) {
            description = String.format(DESCRIPTIONS[1], potency) + DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[2];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));

    }

    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for(int i = 0; i < potency; i++) {
                this.addToBot(new MadnessAction());
            }
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new DiscountPotion();
    }
    
    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
