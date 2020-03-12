package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.powers.AlchemistFormEnemyPower;
import potionbrewer.powers.AlchemistFormPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class AlchemistForm extends CustomCard {

    public static final String ID = PotionbrewerMod.makeID(AlchemistForm.class.getSimpleName());
    public static final String IMG = makeCardPath("AlchemistForm.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
    
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    
    private static final int COST = 3;

    public static final int ENEMY_GOLD = 15;
    private static final int MAGIC = 20;
    private static final int UPGRADE_MAGIC = -5;

    public AlchemistForm() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        tags.add(BaseModCardTags.FORM);
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        this.addToBot(new ApplyPowerAction(p, p, new AlchemistFormPower(magicNumber)));
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.hasPower(MinionPower.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(m, m, new AlchemistFormEnemyPower(m, ENEMY_GOLD)));
            }
        }
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
