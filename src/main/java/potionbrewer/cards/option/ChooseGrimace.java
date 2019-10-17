package potionbrewer.cards.option;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.orbs.Grimace;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ChooseGrimace extends AbstractCard {
    public static String ID = PotionbrewerMod.makeID(ChooseGrimace.class.getSimpleName());
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

    public ChooseGrimace() {
        super(ID, CARD_STRINGS.NAME, "colorless/skill/madness", -2, CARD_STRINGS.DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
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
        this.addToBot(new ChannelAction(new Grimace()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChooseGrimace();
    }
}
