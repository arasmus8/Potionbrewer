package potionbrewer.potions.tonics;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;

public class FlexTonic extends AbstractPotion {

    public static final String ID = PotionbrewerMod.makeID(FlexTonic.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.GREEN.cpy();
    public static final Color HYBRID_COLOR = Color.BROWN.cpy();
    public static final Color SPOTS_COLOR = Color.RED.cpy();

    public FlexTonic() {
        super(NAME, ID, PotionRarity.COMMON, PotionSize.T, PotionColor.STEROID);
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
        AbstractCreature target = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new StrengthPower(target, this.potency), this.potency));
            this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new LoseStrengthPower(target, this.potency), this.potency));
        }
    }

    @Override
    public int getPotency(int ascLevel) {
        return 2;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new FlexTonic();
    }
}
