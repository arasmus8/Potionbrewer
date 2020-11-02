package potionbrewer.cards;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AutoplayCardAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Spark extends AbstractPotionbrewerCard {
    public static final String ID = PotionbrewerMod.makeID(Spark.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;

    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 3;

    public Spark() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR, null);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber;
        cardsToPreview = new Reaction();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        ArrayList<AbstractCard> reactions = p.drawPile.group.stream()
                .filter(card -> card.cardID.equals(Reaction.ID))
                .limit(1)
                .collect(Collectors.toCollection(ArrayList::new));
        if (reactions.size() > 0) {
            AbstractCard c = reactions.get(0);
            if (p.hand.size() < BaseMod.MAX_HAND_SIZE) {
                if (AutoplayField.autoplay.get(c)) {
                    addToBot(new AutoplayCardAction(c, AbstractDungeon.player.hand));
                }
                c.triggerWhenDrawn();
                p.drawPile.moveToHand(c, p.drawPile);

                for (AbstractRelic r : p.relics) {
                    r.onCardDraw(c);
                }
            } else {
                p.createHandIsFullDialog();
                p.drawPile.moveToDiscardPile(c);
            }
        }
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

