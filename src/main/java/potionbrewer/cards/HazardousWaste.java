package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class HazardousWaste extends CatalyzeCard {

// TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(HazardousWaste.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
// Must have an image with the same NAME as the card in your image folder!

// /TEXT DECLARATION/

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;
    private static final int MAGIC = 3;

// /STAT DECLARATION/


    public HazardousWaste() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        misc = magicNumber = baseMagicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void applyPowers() {
        baseMagicNumber = misc;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void catalyzeActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new IncreaseMiscAction(this.uuid, this.misc, upgraded ? 3 : 2));
    }

    @Override
    public void useActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, misc), misc, AbstractGameAction.AttackEffect.POISON));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            ++this.timesUpgraded;// 860
            upgraded = true;
            name = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            initializeTitle();
            rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
