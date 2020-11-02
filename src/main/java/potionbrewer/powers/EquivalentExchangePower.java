package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;

public class EquivalentExchangePower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(EquivalentExchangePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int cardsThisTurn;

    public EquivalentExchangePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("rushdown");
        cardsThisTurn = 0;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        cardsThisTurn = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && cardsThisTurn < amount) {
            this.flash();
            cardsThisTurn += 1;
            action.exhaustCard = true;
            int roll = MathUtils.random(100);
            AbstractCard generated;
            if (roll < 10) {
                generated = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();
            } else {
                generated = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            }
            this.addToBot(new MakeTempCardInHandAction(generated, false));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new EquivalentExchangePower(owner, amount);
    }
}
