package potionbrewer.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;

public abstract class FollowupCard extends AbstractPotionbrewerCard {

    public FollowupCard(final String id,
                        final int cost,
                        final CardType type,
                        final CardColor color,
                        final CardRarity rarity,
                        final CardTarget target) {

        super(id, cost, type, rarity, target, color, null);
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
    }

    @Override
    public void triggerOnGlowCheck() {
        if (PotionbrewerMod.lastPlayedCardCostZero) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public abstract void followupActions(AbstractPlayer p, AbstractMonster m);

    public abstract void useActions(AbstractPlayer p, AbstractMonster m);

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.useActions(p, m);
        if (PotionbrewerMod.lastPlayedCardCostZero && !this.purgeOnUse) {
            this.followupActions(p, m);
            PotionbrewerMod.lastPlayedCardCostZero = this.costForTurn == 0 || this.freeToPlayOnce;
        }
    }
}
