package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;

public class CleansingPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(CleansingPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.WHITE.cpy();
    public static final Color HYBRID_COLOR = Color.GRAY.cpy();
    public static final Color SPOTS_COLOR = Color.CLEAR.cpy();

    public CleansingPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.S, PotionColor.SMOKE);
        isThrown = false;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = String.format(DESCRIPTIONS[0], potency);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            int debuffsToRemove = potency;
            for(AbstractPower pow : p.powers) {
                if(pow.type == AbstractPower.PowerType.DEBUFF) {
                    this.addToBot(new RemoveSpecificPowerAction(p, p, pow));
                    debuffsToRemove -= 1;
                    if(debuffsToRemove == 0) {
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new CleansingPotion();
    }
    
    @Override
    public int getPotency(final int ascLevel) {
        return 2;
    }
}
