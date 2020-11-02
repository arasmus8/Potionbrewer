package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.defect.RedoAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.orbs.Reagent;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
        this.addToBot(new RedoAction());
        ArrayList<Reagent> collectedReagents = p.orbs.stream()
                .filter(o -> o instanceof Reagent)
                .map(o -> (Reagent) o)
                .collect(Collectors.toCollection(ArrayList::new));
        if (collectedReagents.size() >= 3) {
            Prototype card = new Prototype(collectedReagents.get(0), collectedReagents.get(1), collectedReagents.get(2));
            card.purgeOnUse = true;
            this.addToBot(new MakeTempCardInDrawPileAction(card, 1, true, true));
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