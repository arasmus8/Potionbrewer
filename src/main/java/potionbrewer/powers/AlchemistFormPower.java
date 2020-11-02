package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.AlchemistForm;

public class AlchemistFormPower extends AbstractPotionbrewerPower implements CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(AlchemistFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int goldCost;

    public AlchemistFormPower(final int goldCost) {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.goldCost = goldCost;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("alchemy");

        updateDescription();
    }
    
    @Override
    public void atStartOfTurn() {
        boolean hasEmptySlot = AbstractDungeon.player.potions.stream().anyMatch(p -> p instanceof PotionSlot);
        if (hasEmptySlot && AbstractDungeon.player.gold >= goldCost) {
            AbstractDungeon.player.loseGold(goldCost);
            AbstractPotion randomPotion = PotionHelper.getPotion(PotionbrewerMod.potionLibrary.getRandomPotionId());
            this.addToBot(new ObtainPotionAction(randomPotion));
        }

        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.hasPower(MinionPower.POWER_ID) && !m.hasPower(AlchemistFormEnemyPower.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(m, m, new AlchemistFormEnemyPower(m, AlchemistForm.ENEMY_GOLD)));
            }
        }
    }
    
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + goldCost + DESCRIPTIONS[1];
    }
    
    @Override
    public AbstractPower makeCopy() {
        return new AlchemistFormPower(goldCost);
    }
}
