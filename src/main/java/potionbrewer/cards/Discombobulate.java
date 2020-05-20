package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class Discombobulate extends CatalyzeCard {
// TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(Discombobulate.class.getSimpleName());
    public static final String IMG = makeCardPath("Discombobulate.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
// Must have an image with the same NAME as the card in your image folder!.

// /TEXT DECLARATION/

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC_AMT = 1;
// /STAT DECLARATION/

    public Discombobulate() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void catalyzeActions(AbstractPlayer p, AbstractMonster m) {
        exhaust = true;
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
    }

    @Override
    public void useActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_MAGIC_AMT);
            initializeDescription();
        }
    }
}

