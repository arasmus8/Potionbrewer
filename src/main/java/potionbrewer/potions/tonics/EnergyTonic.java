package potionbrewer.potions.tonics;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;

public class EnergyTonic extends AbstractPotion {

    public static final String ID = PotionbrewerMod.makeID(EnergyTonic.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.GREEN.cpy();
    public static final Color HYBRID_COLOR = Color.BROWN.cpy();
    public static final Color SPOTS_COLOR = Color.RED.cpy();

    public EnergyTonic() {
        super(NAME, ID, PotionRarity.COMMON, PotionSize.T, PotionColor.ENERGY);
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = String.format(DESCRIPTIONS[0], potency);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature m) {
        this.addToBot(new GainEnergyAction(potency));
    }

    @Override
    public int getPotency(int ascLevel) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new EnergyTonic();
    }
}
