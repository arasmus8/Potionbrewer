package potionbrewer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.HazardousMaterialsAction;
import potionbrewer.characters.Potionbrewer;

public class HazardousMaterials extends CatalyzeCard {

    public static final String ID = PotionbrewerMod.makeID(HazardousMaterials.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    
    private static final int COST = -1;

    private HazardousMaterialsAction action;

    public HazardousMaterials() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
    }

    @Override
    public void catalyzeActions(AbstractPlayer p, AbstractMonster m) {
        action.triggerCatalyze = true;
    }

    @Override
    public void useActions(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        action = new HazardousMaterialsAction(p, m, freeToPlayOnce, energyOnUse);
        this.addToBot(action);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isInnate = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}