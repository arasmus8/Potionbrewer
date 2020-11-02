package potionbrewer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

public class BeatDown extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(BeatDown.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 0;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 3;

    public boolean shouldRebound;

    public BeatDown() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        baseDamage = DAMAGE;
        shouldRebound = false;
    }

    private void calculateRebound() {
        shouldRebound = AbstractDungeon.player.hand.size() > 1;
    }

    @Override
    public void triggerWhenDrawn() {
        calculateRebound();
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        calculateRebound();
    }

    @Override
    public void applyPowers() {
        calculateRebound();
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = shouldRebound ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
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

