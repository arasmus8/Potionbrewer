package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public abstract class FollowupCard extends CustomCard {

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
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        ArrayList<AbstractCard> cardsPlayedThisCombat = AbstractDungeon.actionManager.cardsPlayedThisCombat;
        if (!cardsPlayedThisCombat.isEmpty()) {
            AbstractCard lastPlayed = cardsPlayedThisCombat.get(cardsPlayedThisCombat.size() - 1);
            if (lastPlayed.cost == 0) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public abstract void followupActions(AbstractPlayer p, AbstractMonster m);

    public abstract void useActions(AbstractPlayer p, AbstractMonster m);

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.useActions(p, m);
        ArrayList<AbstractCard> cardsPlayedThisCombat = AbstractDungeon.actionManager.cardsPlayedThisCombat;
        if (cardsPlayedThisCombat.size() >= 2) {
            AbstractCard lastPlayed = cardsPlayedThisCombat.get(cardsPlayedThisCombat.size() - 2);
            if (!lastPlayed.purgeOnUse && (lastPlayed.cost == 0 || lastPlayed.freeToPlayOnce)) {
                this.followupActions(p, m);
            }
        }
    }
}
