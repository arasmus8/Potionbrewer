package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class TheFinalStraw extends CustomCard {
    // TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(TheFinalStraw.class.getSimpleName());
    public static final String IMG = makeCardPath("TheFinalStraw.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
    // Must have an image with the same NAME as the card in your image folder!.

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 3;
    // /STAT DECLARATION/

    public TheFinalStraw() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = 0;
        magicNumber = baseMagicNumber = 40;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = (mo.maxHealth - mo.currentHealth) * magicNumber / 100;
        super.calculateCardDamage(mo);
        rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(20);
            initializeDescription();
        }
    }
}