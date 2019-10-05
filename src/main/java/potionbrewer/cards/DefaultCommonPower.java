package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.powers.ToxicPower;

import static potionbrewer.PotionbrewerMod.makeCardPath;

public class DefaultCommonPower extends AbstractDynamicCard {
    
    public static final String ID = PotionbrewerMod.makeID(DefaultCommonPower.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    
    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;
    
    public DefaultCommonPower() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new ToxicPower(p, p, magicNumber), magicNumber));
    }
    
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}