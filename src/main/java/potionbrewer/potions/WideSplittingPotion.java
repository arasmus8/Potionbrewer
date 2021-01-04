package potionbrewer.potions;

import com.evacipated.cardcrawl.mod.widepotions.extensions.PotionExtensions;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.relics.SalesContract;

public class WideSplittingPotion extends WidePotion {
    public int createdCount = 0;

    public WideSplittingPotion() {
        super(new SplittingPotion());
        targetRequired = false;
    }

    private WidePotion getRandomWidePotion() {
        AbstractPotion rolled;
        boolean canBeWide = false;
        do {
            rolled = AbstractDungeon.returnRandomPotion(true);
            canBeWide = PotionExtensions.canBeWide(rolled);
        } while (!canBeWide);
        return new WidePotion(rolled);
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
            for (i = 0; i < potency && i < player.potionSlots; ++i) {
                WidePotion randomWide = getRandomWidePotion();
                this.addToBot(new ObtainPotionAction(randomWide));
            }
        } else {
            int potency = getPotency();
            for (i = 0; i < player.potionSlots && createdCount < potency; ++i) {
                if (player.potions.get(i) instanceof PotionSlot) {
                    player.obtainPotion(getRandomWidePotion());
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
    public int getPotency(final int potency) {
        return 2;
    }
}
