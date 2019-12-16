package potionbrewer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.HazardousMaterialsAction;
import potionbrewer.characters.Potionbrewer;

import static potionbrewer.PotionbrewerMod.makeCardPath;

public class HazardousMaterials extends CatalyzeCard {

    public static final String ID = PotionbrewerMod.makeID(HazardousMaterials.class.getSimpleName());
    public static final String IMG = makeCardPath("HazardousMaterials.png");
    
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    
    private static final int COST = -1;

    private HazardousMaterialsAction action;

    public HazardousMaterials() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
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