package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.patches.PotionTracker;

public class ChemicalShroud extends PotionTrackingCard {
    public static final String ID = PotionbrewerMod.makeID(ChemicalShroud.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 4;
    private static final int BLOCK = 12;
    private static final int UPGRADE_BLOCK_AMT = 4;

    public ChemicalShroud() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BLOCK;
        if (CardCrawlGame.dungeon != null) {
            this.configureCostsOnNewCard();
        }
    }

    public void configureCostsOnNewCard() {
        int potionsUsed = PotionTracker.potionsUsedThisCombat.get(AbstractDungeon.player);
        this.updateCost(-potionsUsed);
    }

    @Override
    public void onUsePotion(AbstractPotion p) {
        this.updateCost(-1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, block));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK_AMT);
            initializeDescription();
        }
    }
}