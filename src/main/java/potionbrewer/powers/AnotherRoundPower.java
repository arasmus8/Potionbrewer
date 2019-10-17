package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class AnotherRoundPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(AnotherRoundPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    /*
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("alchemy84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("alchemy32.png"));
    */

    public AnotherRoundPower() {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("rebound");

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        ArrayList<AbstractCard> discard = AbstractDungeon.player.discardPile.group;
        if (discard.size() > 0) {
            ArrayList<AbstractCard> zeroes = discard
                    .stream()
                    .filter(c -> c.cost == 0 || c.freeToPlayOnce)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (zeroes.size() > 0) {
                Collections.shuffle(zeroes, MathUtils.random);
                AbstractCard toHand = zeroes.get(0);
                this.addToBot(new DiscardToHandAction(toHand));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AnotherRoundPower();
    }
}
