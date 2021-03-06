package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;
import potionbrewer.relics.SalesContract;

public class SplittingPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(SplittingPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.CHARTREUSE;
    public static final Color HYBRID_COLOR = Color.GOLD;
    public static final Color SPOTS_COLOR = Color.CLEAR;

    public int createdCount = 0;

    public SplittingPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.S, PotionColor.ANCIENT);
        isThrown = false;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer player = AbstractDungeon.player;
        if (player.hasRelic(Sozu.ID)) {
            return;
        } else if (player.hasRelic(SalesContract.ID)) {
            return;
        }
        int i;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for(i = 0; i < potency && i < player.potionSlots; ++i) {
                this.addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
            }
        } else {
            for (i = 0; i < player.potionSlots && createdCount < getPotency(); ++i) {
                if (player.potions.get(i) instanceof PotionSlot) {
                    player.obtainPotion(AbstractDungeon.returnRandomPotion());
                    createdCount += 1;
                }
            }
        }

    }

    @Override
    public boolean canUse() {
        if (AbstractDungeon.actionManager.turnHasEnded && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            return false;
        } else {
            return AbstractDungeon.getCurrRoom().event == null || !(AbstractDungeon.getCurrRoom().event instanceof WeMeetAgain);
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        return new SplittingPotion();
    }
    
    @Override
    public int getPotency(final int potency) {
        return 2;
    }
}
