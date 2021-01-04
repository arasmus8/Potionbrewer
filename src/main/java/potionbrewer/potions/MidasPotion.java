package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.unique.GreedAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import potionbrewer.PotionbrewerMod;

public class MidasPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(MidasPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.GOLD.cpy();
    public static final Color HYBRID_COLOR = Color.GOLDENROD.cpy();
    public static final Color SPOTS_COLOR = Color.WHITE.cpy();

    public MidasPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.SPHERE, PotionColor.POISON);
        isThrown = true;
        targetRequired = true;
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
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && p != null && target != null) {
            this.addToBot(new GreedAction(target, new DamageInfo(p, potency, DamageInfo.DamageType.THORNS), potency));
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new MidasPotion();
    }
    
    @Override
    public int getPotency(final int potency) {
        return 10;
    }
}
