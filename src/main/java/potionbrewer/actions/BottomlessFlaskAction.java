package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.option.ChoosePotion;

import java.util.Optional;

public class BottomlessFlaskAction extends AbstractGameAction {
    private AbstractPlayer player;
    private AbstractCard card;
    private int selectCount;
    private int groupSize;
    private static final String[] TEXT;
    public static final String ID = PotionbrewerMod.makeID(BottomlessFlaskAction.class.getSimpleName());

    public BottomlessFlaskAction(AbstractPlayer p, AbstractCard cardToReplace, final int numberOfPotions) {
        this.player = p;
        this.card = cardToReplace;
        this.groupSize = 10;
        this.selectCount = numberOfPotions;
        duration = startDuration = Settings.ACTION_DUR_FAST;

        this.card.purgeOnUse = true;
        p.limbo.addToTop(this.card);
        Optional<AbstractCard> original = player.masterDeck.group.stream()
                .filter(c -> c.uuid.equals(this.card.uuid))
                .findFirst();
        original.ifPresent(card -> player.masterDeck.removeCard(card));
    }

    @Override
    public void update() {
        if (duration == this.startDuration) {
            if (selectCount > 0) {
                String tipMessage = TEXT[0];

                CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
                ChoosePotion.getRandomPotionIdList(groupSize).stream()
                        .map(ChoosePotion::new)
                        .forEach(cardGroup::addToTop);
                cardGroup.sortAlphabetically(true);
                AbstractDungeon.gridSelectScreen.open(cardGroup, selectCount, tipMessage, false);

                tickDuration();
            } else {
                isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard selectedCard : AbstractDungeon.gridSelectScreen.selectedCards) {
                    player.masterDeck.addToTop(selectedCard);
                    selectedCard.lighten(false);
                    selectedCard.unhover();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
                isDone = true;
            }

            this.tickDuration();
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    }
}
