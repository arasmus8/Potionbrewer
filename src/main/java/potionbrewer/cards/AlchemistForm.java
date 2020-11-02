package potionbrewer.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.powers.AlchemistFormEnemyPower;
import potionbrewer.powers.AlchemistFormPower;

import java.util.Collections;
import java.util.List;

public class AlchemistForm extends AbstractPotionbrewerCard {

    public static final String ID = PotionbrewerMod.makeID(AlchemistForm.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;
    private static final List<CardTags> tagsList = Collections.singletonList(BaseModCardTags.FORM);

    private static final int COST = 3;

    public static final int ENEMY_GOLD = 15;
    private static final int MAGIC = 20;
    private static final int UPGRADE_MAGIC = -5;

    public AlchemistForm() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, tagsList);
        magicNumber = baseMagicNumber = MAGIC;
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
