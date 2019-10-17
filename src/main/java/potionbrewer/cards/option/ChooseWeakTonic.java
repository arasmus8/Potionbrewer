package potionbrewer.cards.option;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.UseTempPotionAction;
import potionbrewer.potions.tonics.WeakTonic;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ChooseWeakTonic extends AbstractCard {
    public static String ID = PotionbrewerMod.makeID(ChooseWeakTonic.class.getSimpleName());
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

    public ChooseWeakTonic() {
        super(ID, CARD_STRINGS.NAME, "red/skill/intimidate", -2, CARD_STRINGS.DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        this.addToBot(new UseTempPotionAction(new WeakTonic(), AbstractDungeon.getRandomMonster()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChooseWeakTonic();
    }
}
