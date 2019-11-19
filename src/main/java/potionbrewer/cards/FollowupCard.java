package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;

public abstract class FollowupCard extends CustomCard {

    private boolean lastWasFree = false;

    public FollowupCard(final String id,
                        final String name,
                        final String img,
                        final int cost,
                        final String rawDescription,
                        final CardType type,
                        final CardColor color,
                        final CardRarity rarity,
                        final CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);
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
