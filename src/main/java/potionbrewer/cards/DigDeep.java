package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class DigDeep extends FollowupCard {

    // TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(DigDeep.class.getSimpleName());
    public static final String IMG = makeCardPath("DigDeep.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
    // Must have an image with the same NAME as the card in your image folder!

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC_AMT = 1;

    // /STAT DECLARATION/


    public DigDeep() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = MAGIC;
        this.magicNumber = MAGIC;
    }

    @Override
    public void followupActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(1));
    }

    @Override
    public void useActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            ++this.timesUpgraded;// 860
            upgraded = true;
            name = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            initializeTitle();
            upgradeMagicNumber(UPGRADE_MAGIC_AMT);
            initializeDescription();
        }
    }
}