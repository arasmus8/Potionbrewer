package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

public class Smack extends CatalyzeCard {
    public static final String ID = PotionbrewerMod.makeID(Smack.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int ENERGY = 1;

    public Smack() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = ENERGY;
        magicNumber = ENERGY;
    }

    @Override
    public void catalyzeActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public void useActions(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
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