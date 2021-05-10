package potionbrewer.potions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import potionbrewer.PotionbrewerMod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class BlacksmithPotion extends AbstractPotion {

    public static final String POTION_ID = PotionbrewerMod.makeID(BlacksmithPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color LIQUID_COLOR = Color.BLACK.cpy();
    public static final Color HYBRID_COLOR = Color.GRAY.cpy();
    public static final Color SPOTS_COLOR = null;

    public BlacksmithPotion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.ANVIL, PotionColor.SMOKE);
        isThrown = false;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        if (potency > 1) {
            description = String.format(DESCRIPTIONS[0], potency) + DESCRIPTIONS[2];
        } else {
            description = String.format(DESCRIPTIONS[0], potency) + DESCRIPTIONS[1];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    private void doUpgrade(AbstractCard card) {
        card.upgrade();
        AbstractDungeon.player.bottledCardUpgradeCheck(card);
        AbstractDungeon.effectsQueue.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
        AbstractDungeon.topLevelEffectsQueue.add(
                new ShowCardBrieflyEffect((card).makeStatEquivalentCopy(),
                        Settings.WIDTH / 2.0F + MathUtils.random(-250f, 250f) * Settings.scale,
                        Settings.HEIGHT / 2.0F + MathUtils.random(-175f, 175) * Settings.scale));
        addToBot(new WaitAction(Settings.ACTION_DUR_MED));
    }

    @Override
    public void use(AbstractCreature target) {
        ArrayList<AbstractCard> upgradeableCards = AbstractDungeon.player.masterDeck.group.stream()
                .filter(AbstractCard::canUpgrade)
                .collect(Collectors.toCollection(ArrayList::new));

        if (!upgradeableCards.isEmpty()) {
            Collections.shuffle(upgradeableCards, AbstractDungeon.miscRng.random);
            upgradeableCards.stream()
                    .limit(potency)
                    .forEach(this::doUpgrade);
        }
    }

    @Override
    public boolean canUse() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            return false;
        } else {
            return AbstractDungeon.getCurrRoom().event == null || !(AbstractDungeon.getCurrRoom().event instanceof WeMeetAgain);
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BlacksmithPotion();
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
