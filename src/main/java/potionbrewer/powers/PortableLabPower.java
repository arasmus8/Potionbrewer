package potionbrewer.powers;

import basemod.BaseMod;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.option.ChoosePotion;

import java.util.stream.IntStream;

public class PortableLabPower extends AbstractPotionbrewerPower implements CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(PortableLabPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int threshold;
    private int cardCount = 1;

    public PortableLabPower(final int threshold) {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        amount = this.threshold = threshold;

        type = PowerType.BUFF;
        isTurnBased = false;

        loadRegion("portable_lab");

        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        cardCount += stackAmount;
    }

    @Override
    public void atStartOfTurn() {
        if (amount <= 0) {
            this.flash();
            amount = threshold;
            int freeHandSlots = BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size();
            int cardsToCreate = Math.min(freeHandSlots, cardCount);
            IntStream.range(0, cardsToCreate)
                    .mapToObj(i -> new ChoosePotion())
                    .forEach(c -> this.addToBot(new MakeTempCardInHandAction(c)));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (EnergyPanel.totalCount > 0) {
                this.flash();
            }
            amount -= EnergyPanel.totalCount;
            if (amount < 0) {
                amount = 0;
            }
            AbstractDungeon.player.energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public void updateDescription() {
        StringBuilder s = new StringBuilder(DESCRIPTIONS[0]);
        s.append(Math.max(amount, 0));
        s.append(DESCRIPTIONS[1]);
        s.append(cardCount);
        if (cardCount > 1) {
            s.append(DESCRIPTIONS[3]);
        } else {
            s.append(DESCRIPTIONS[2]);
        }
        description = s.toString();
    }

    @Override
    public AbstractPower makeCopy() {
        return new PortableLabPower(threshold);
    }
}
