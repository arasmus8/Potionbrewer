package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import static potionbrewer.PotionbrewerMod.makeCardPath;

public class DefaultSecondMagicNumberSkill extends AbstractDynamicCard {
    
    public static final String ID = PotionbrewerMod.makeID(DefaultSecondMagicNumberSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    
    private static final int COST = 1;
    
    private static final int VULNERABLE = 2;
    private static final int UPGRADE_PLUS_VULNERABLE = 3;
    
    private static final int POISON = 4;
    private static final int UPGRADE_PLUS_POISON = 5;
    
    public DefaultSecondMagicNumberSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = VULNERABLE;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = POISON;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), this.magicNumber));
        
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new PoisonPower(m, p, this.defaultSecondMagicNumber), this.defaultSecondMagicNumber));
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE);
            this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_POISON);
            this.initializeDescription();
        }
    }
}