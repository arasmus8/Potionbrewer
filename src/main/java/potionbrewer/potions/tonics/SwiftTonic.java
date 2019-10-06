package potionbrewer.potions.tonics;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;

public class SwiftTonic extends AbstractPotion {

    public static final String ID = PotionbrewerMod.makeID(SwiftTonic.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.GREEN.cpy();
    public static final Color HYBRID_COLOR = Color.BROWN.cpy();
    public static final Color SPOTS_COLOR = Color.RED.cpy();

    public SwiftTonic() {
        super(NAME, ID, PotionRarity.COMMON, PotionSize.T, PotionColor.SWIFT);
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        if(potency == 1) {
            description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[2];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature m) {
        this.addToBot(new DrawCardAction(AbstractDungeon.player, this.potency));
    }

    @Override
    public int getPotency(int i) {
        int p = 1;
        if( AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark") ) {
            p *= 2;
        }
        return p;
    }

    @Override
    public AbstractPotion makeCopy() {
        return null;
    }
}
