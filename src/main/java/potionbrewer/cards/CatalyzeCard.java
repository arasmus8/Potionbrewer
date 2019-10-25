package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.patches.PotionTracker;
import potionbrewer.powers.NoCatalyzePower;

public abstract class CatalyzeCard extends CustomCard {

    public CatalyzeCard ( final String id,
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
    public void triggerWhenDrawn() {
        triggerOnGlowCheck();
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player != null &&
                PotionTracker.potionsUsedThisTurn.get(AbstractDungeon.player) > 0 &&
                !AbstractDungeon.player.hasPower(NoCatalyzePower.POWER_ID)
        ) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public abstract void catalyzeActions(AbstractPlayer p, AbstractMonster m);

    public abstract void useActions(AbstractPlayer p, AbstractMonster m);

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.useActions(p, m);
        if (p != null && PotionTracker.potionsUsedThisTurn.get(p) > 0 && !p.hasPower(NoCatalyzePower.POWER_ID)) {
            this.catalyzeActions(p, m);
        }
    }
}
