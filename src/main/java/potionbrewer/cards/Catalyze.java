package potionbrewer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.EnergyPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.UseTempPotionAction;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.potions.tonics.EnergyTonic;
import potionbrewer.potions.tonics.SwiftTonic;

public class Catalyze extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(Catalyze.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 0;

    public Catalyze() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            this.addToBot(new UseTempPotionAction(new EnergyPotion(), m));
            this.addToBot(new UseTempPotionAction(new SwiftTonic(), m));
        } else {
            this.addToBot(new UseTempPotionAction(new EnergyTonic(), m));
            this.addToBot(new UseTempPotionAction(new SwiftTonic(), m));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
