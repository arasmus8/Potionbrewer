package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
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

public class HastePotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(HastePotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.SCARLET.cpy();
    public static final Color HYBRID_COLOR = Color.RED.cpy();
    public static final Color SPOTS_COLOR = Color.WHITE.cpy();

    public HastePotion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.BOLT, PotionColor.FIRE);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        isThrown = false;
        tips.add(new PowerTip(name, description));
    }
    
    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCreature m = target;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && p != null && m != null) {
            this.addToBot(new DiscardAtEndOfTurnAction());
            this.addToBot(new GainEnergyAction(p.energy.energyMaster - p.energy.energy));
            this.addToBot(new DrawCardAction(p, potency));
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new HastePotion();
    }
    
    @Override
    public int getPotency(final int potency) {
        if (AbstractDungeon.player == null) {
            return 5;
        } else {
            return AbstractDungeon.player.hasRelic("SacredBark") ? 10 : 5;
        }
    }
}
