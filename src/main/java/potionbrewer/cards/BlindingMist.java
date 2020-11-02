package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

public class BlindingMist extends AbstractPotionbrewerCard {

    public static final String ID = PotionbrewerMod.makeID(BlindingMist.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK_AMT = 3;

    public BlindingMist() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        this.baseBlock = BLOCK;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster target) {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped()) {
                this.addToBot(new GainBlockAction(p, block));
            }
        }
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