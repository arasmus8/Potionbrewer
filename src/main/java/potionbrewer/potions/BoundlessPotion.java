package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.unique.LimitBreakAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;

import java.util.stream.IntStream;

public class BoundlessPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(BoundlessPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.GOLDENROD.cpy();
    public static final Color HYBRID_COLOR = Color.YELLOW.cpy();
    public static final Color SPOTS_COLOR = Color.CLEAR.cpy();

    public BoundlessPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.HEART, PotionColor.ENERGY);
        isThrown = false;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        if (potency > 1) {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[2];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        IntStream.rangeClosed(1, getPotency())
                .forEach(i -> addToBot(new LimitBreakAction()));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BoundlessPotion();
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
