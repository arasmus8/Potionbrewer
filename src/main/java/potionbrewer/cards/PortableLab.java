package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.optioncards.ChoosePotion;
import potionbrewer.powers.PortableLabPower;

public class PortableLab extends AbstractPotionbrewerCard {

    public static final String ID = PotionbrewerMod.makeID(PortableLab.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 0;

    private static final int MAGIC = 5;
    private static final int UPGRADE_MAGIC = -2;

    public PortableLab() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        magicNumber = baseMagicNumber = MAGIC;
        cardsToPreview = new ChoosePotion(null);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PortableLabPower(magicNumber), 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}
