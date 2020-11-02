package potionbrewer.cards;

import com.megacrit.cardcrawl.potions.AbstractPotion;

public abstract class PotionTrackingCard extends AbstractPotionbrewerCard {
    public PotionTrackingCard(final String id,
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

    public abstract void onUsePotion(AbstractPotion p);
}
