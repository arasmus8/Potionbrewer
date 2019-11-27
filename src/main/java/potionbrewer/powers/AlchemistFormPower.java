package potionbrewer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.AlchemistForm;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makePowerPath;

public class AlchemistFormPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = PotionbrewerMod.makeID(AlchemistFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("alchemy84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("alchemy32.png"));

    private int goldCost;

    public AlchemistFormPower(final int goldCost) {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.goldCost = goldCost;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        
        updateDescription();
    }
    
    @Override
    public void atStartOfTurn() {
        boolean hasEmptySlot = AbstractDungeon.player.potions.stream().anyMatch(p -> p instanceof PotionSlot);
        if (hasEmptySlot && AbstractDungeon.player.gold >= goldCost) {
            AbstractDungeon.player.loseGold(goldCost);
            AbstractPotion randomPotion = AbstractDungeon.returnRandomPotion(true);
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
