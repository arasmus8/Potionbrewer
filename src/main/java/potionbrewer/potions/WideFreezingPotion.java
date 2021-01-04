package potionbrewer.potions;

import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class WideFreezingPotion extends WidePotion {
    public WideFreezingPotion() {
        super(new FreezingPotion());
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDeadOrEscaped()) {
                    addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new SlowPower(m, potency), potency));
                }
            }
        }
    }

    @Override
    public int getPotency(final int potency) {
        return 10;
    }
}
