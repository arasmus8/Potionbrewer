package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;

public class CrossReferenceAction extends AbstractGameAction {
    private AbstractPlayer p;
    private boolean upgraded;
    private boolean freeToPlayOnce;
    private int energyOnUse;
    public static ArrayList<AbstractCard> crossReferenceCards = new ArrayList<>();

    public CrossReferenceAction(AbstractPlayer p, final boolean upgraded, final boolean freeToPlayOnce, final int energyOnUse) {
        duration = Settings.ACTION_DUR_FASTER;
        actionType = ActionType.SPECIAL;

        this.p = p;
        this.upgraded = upgraded;
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }
        if (p.hasRelic(ChemicalX.ID)) {
            effect += 2;
            p.getRelic(ChemicalX.ID).flash();
        }
        if (upgraded) {
            ++effect;
        }
        if (effect > 0) {
            crossReferenceCards.clear();
            crossReferenceCards.addAll(p.hand.group);
            this.addToBot(new DrawPileToHandAction(effect, AbstractCard.CardType.SKILL));
            this.addToBot(new WaitAction(Settings.ACTION_DUR_FAST));
            this.addToBot(new CrossReferencePostDrawAction());

            if (!freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        isDone = true;
    }
}
