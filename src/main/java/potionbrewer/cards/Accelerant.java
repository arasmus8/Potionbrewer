package potionbrewer.cards;

import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.powers.AccelerantPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class Accelerant extends CustomCard {

// TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(Accelerant.class.getSimpleName());
    public static final String IMG = makeCardPath("Accelerant.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
// Must have an image with the same NAME as the card in your image folder!

// /TEXT DECLARATION/

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 0;
// /STAT DECLARATION/

    public Accelerant() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cardsToDraw = BaseMod.MAX_HAND_SIZE - p.hand.size();
        addToBot(new DrawCardAction(p, cardsToDraw));
        this.addToBot(new ApplyPowerAction(p, p, new AccelerantPower()));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            exhaust = true;
            initializeDescription();
        }
    }
}
