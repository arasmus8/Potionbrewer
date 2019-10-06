package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;

public class ColorlessPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(ColorlessPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.GRAY;
    public static final Color HYBRID_COLOR = Color.LIGHT_GRAY;
    public static final Color SPOTS_COLOR = Color.CLEAR;

    public ColorlessPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.CARD, PotionColor.SKILL);
        isThrown = false;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        if(potency == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));

    }

    @Override
    public void use(AbstractCreature target) {
        AbstractCard c = AbstractDungeon.returnTrulyRandomColorlessCardInCombat(AbstractDungeon.cardRandomRng).makeCopy();
        c.setCostForTurn(0);
        this.addToBot(new MakeTempCardInHandAction(c, 1));
        if (potency > 1) {
            c = c.makeStatEquivalentCopy();
            this.addToBot(new MakeTempCardInHandAction(c, 1));
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new ColorlessPotion();
    }
    
    @Override
    public int getPotency(final int potency) {
        if (AbstractDungeon.player == null) {
            return 1;
        } else {
            return AbstractDungeon.player.hasRelic("SacredBark") ? 2 : 1;
        }
    }
}
