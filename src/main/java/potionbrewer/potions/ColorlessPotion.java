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

public class ColorlessPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(ColorlessPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.YELLOW;
    public static final Color HYBRID_COLOR = Color.WHITE;
    public static final Color SPOTS_COLOR = Color.CLEAR;

    public ColorlessPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.SPHERE, PotionColor.SMOKE);
        potency = getPotency();
        if (potency > 1) {
            description = DESCRIPTIONS[1] + potency + DESCRIPTIONS[2] + DESCRIPTIONS[3];
        } else {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[3];
        }
        isThrown = false;
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
        return new ColorlessPotion();
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
