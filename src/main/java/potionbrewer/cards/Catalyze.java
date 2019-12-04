package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.EnergyPotion;
import com.megacrit.cardcrawl.potions.SwiftPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.UseTempPotionAction;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.potions.tonics.EnergyTonic;
import potionbrewer.potions.tonics.SwiftTonic;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class Catalyze extends CustomCard {

// TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(Catalyze.class.getSimpleName());
    public static final String IMG = makeCardPath("Catalyze.png");
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


    public Catalyze() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            this.addToBot(new UseTempPotionAction(new EnergyPotion(), m));
            this.addToBot(new UseTempPotionAction(new SwiftPotion(), m));
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
            rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
