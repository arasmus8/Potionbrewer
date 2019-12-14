package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.CrossReferenceAction;
import potionbrewer.characters.Potionbrewer;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class CrossReference extends CustomCard {

    // TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(CrossReference.class.getSimpleName());
    public static final String IMG = makeCardPath("CrossReference.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
    // Must have an image with the same NAME as the card in your image folder!

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = -1;

    // /STAT DECLARATION/


    public CrossReference() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        this.addToBot(new CrossReferenceAction(p, upgraded, freeToPlayOnce, energyOnUse));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}