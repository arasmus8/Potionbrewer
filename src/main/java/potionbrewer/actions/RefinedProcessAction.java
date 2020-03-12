package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;
import java.util.function.Consumer;

public class RefinedProcessAction extends AbstractGameAction {
    private int miscIncrease;
    private UUID uuid;

    public RefinedProcessAction(UUID targetUUID, int miscIncrease) {
        this.miscIncrease = miscIncrease;
        this.uuid = targetUUID;
    }

    public void update() {
        Consumer<AbstractCard> adjustMiscAndDamage = card -> {
            card.misc += miscIncrease;
            card.baseDamage = card.misc;
            card.isDamageModified = false;
        };

        AbstractDungeon.player.masterDeck.group.stream()
                .filter(card -> card.uuid.equals(uuid))
                .forEach(adjustMiscAndDamage);

        GetAllInBattleInstances.get(uuid).forEach(adjustMiscAndDamage);

        this.isDone = true;
    }
}
