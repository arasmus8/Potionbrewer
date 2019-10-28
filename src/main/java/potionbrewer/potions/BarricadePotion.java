package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.unique.DoubleYourBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;

import java.util.stream.IntStream;

public class BarricadePotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(BarricadePotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.LIGHT_GRAY.cpy();
    public static final Color HYBRID_COLOR = Color.SKY.cpy();
    public static final Color SPOTS_COLOR = Color.FIREBRICK.cpy();

    public BarricadePotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.SPHERE, PotionColor.ENERGY);
        isThrown = false;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        if (potency > 1) {
            description = DESCRIPTIONS[0] + " " + potency + DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        IntStream.rangeClosed(1, getPotency())
                .forEach(i -> addToBot(new DoubleYourBlockAction(AbstractDungeon.player)));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BarricadePotion();
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
