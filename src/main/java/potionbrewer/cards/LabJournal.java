package potionbrewer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.LabJournalAction;
import potionbrewer.characters.Potionbrewer;

public class LabJournal extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(LabJournal.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    public LabJournal() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        isEthereal = true;
        exhaust = true;
        cardsToPreview = new Prototype();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LabJournalAction());
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isEthereal = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}