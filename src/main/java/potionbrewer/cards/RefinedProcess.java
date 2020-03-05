package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class RefinedProcess extends CatalyzeCard {
// TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(RefinedProcess.class.getSimpleName());
    public static final String IMG = makeCardPath("RefinedProcess.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
// Must have an image with the same NAME as the card in your image folder!.

// /TEXT DECLARATION/

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 2;

    private static final int DAMAGE = 3;

    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC_AMT = 1;
// /STAT DECLARATION/

    public RefinedProcess() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        misc = DAMAGE;
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void applyPowers() {
        baseDamage = misc;
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void catalyzeActions(AbstractPlayer p, AbstractMonster m) {
        exhaust = true;
        this.addToBot(new IncreaseMiscAction(this.uuid, this.misc, 1));
    }

    @Override
    public void useActions(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage, damageTypeForTurn);
        for (int i = 0; i < magicNumber; i++) {
            this.addToBot(new DamageAction(m, info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_AMT);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard copy = super.makeStatEquivalentCopy();
        copy.baseDamage = copy.misc;
        return copy;
    }
}

