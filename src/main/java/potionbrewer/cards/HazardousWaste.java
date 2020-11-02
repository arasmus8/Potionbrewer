package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.powers.SlimedPower;

public class HazardousWaste extends CatalyzeCard {
    public static final String ID = PotionbrewerMod.makeID(HazardousWaste.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;
    private static final int MAGIC = 2;

    public HazardousWaste() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        misc = magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void applyPowers() {
        baseMagicNumber = misc;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void catalyzeActions(AbstractPlayer p, AbstractMonster m) {
        exhaust = true;
        this.addToBot(new IncreaseMiscAction(this.uuid, this.misc, 1));
    }

    @Override
    public void useActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new SlimedPower(m, p, misc), misc, AbstractGameAction.AttackEffect.POISON));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            ++this.timesUpgraded;// 860
            upgraded = true;
            isInnate = true;
            name = EXTENDED_DESCRIPTION[0];
            initializeTitle();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard copy = super.makeStatEquivalentCopy();
        copy.baseMagicNumber = copy.misc;
        return copy;
    }
}
