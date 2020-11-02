package potionbrewer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.BottomlessFlaskAction;
import potionbrewer.characters.Potionbrewer;

import java.util.Collections;
import java.util.List;

public class BottomlessFlask extends AbstractPotionbrewerCard {

    public static final String ID = PotionbrewerMod.makeID(BottomlessFlask.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    private static final List<CardTags> tagsList = Collections.singletonList(CardTags.HEALING);

    private static final int COST = 1;

    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    public BottomlessFlask() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, tagsList);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BottomlessFlaskAction(p, this, magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}
