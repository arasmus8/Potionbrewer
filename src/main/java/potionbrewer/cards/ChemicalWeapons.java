package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.option.ChoosePotion;
import potionbrewer.characters.Potionbrewer;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class ChemicalWeapons extends CustomCard {
// TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(ChemicalWeapons.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");
// Must have an image with the same NAME as the card in your image folder!.

    // /TEXT DECLARATION/
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 3;
// /STAT DECLARATION/

    public ChemicalWeapons() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        cardsToPreview = new ChoosePotion("RAND_TONIC");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new MakeTempCardInHandAction(new ChoosePotion(PotionbrewerMod.tonicLibrary.getRandomTonic().ID)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}

