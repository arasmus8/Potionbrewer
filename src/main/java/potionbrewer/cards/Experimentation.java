package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.orbs.Reagent;

public class Experimentation extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(Experimentation.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    public Experimentation() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        isEthereal = true;
        cardsToPreview = new Prototype();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (PotionbrewerMod.reagents.size() >= 3) {
            Reagent r1 = PotionbrewerMod.popReagent();
            Reagent r2 = PotionbrewerMod.popReagent();
            Reagent r3 = PotionbrewerMod.popReagent();
            Prototype card = new Prototype(r3, r2, r1);
            card.purgeOnUse = true;
            this.addToBot(new MakeTempCardInDrawPileAction(card, 1, true, true));
            PotionbrewerMod.addReagent(r3);
            PotionbrewerMod.addReagent(r2);
            PotionbrewerMod.addReagent(r1);
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}