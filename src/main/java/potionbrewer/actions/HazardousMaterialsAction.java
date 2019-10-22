package potionbrewer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import potionbrewer.powers.DiseasePower;
import potionbrewer.powers.ToxicPower;

public class HazardousMaterialsAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private AbstractPlayer p;
    private AbstractMonster m;
    private int energyOnUse;
    public boolean triggerCatalyze = false;

    public HazardousMaterialsAction(final AbstractPlayer p, final AbstractMonster m, final boolean freeToPlayOnce, final int energyOnUse) {
        this.freeToPlayOnce = false;
        this.p = p;
        this.m = m;
        this.freeToPlayOnce = freeToPlayOnce;
        actionType = ActionType.SPECIAL;
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
        if (effect > 0) {
            for (int i = 0; i < effect; ++i) {
                this.addToBot(new ApplyPowerAction(m, p, new ToxicPower(m, p, 1), 1, AttackEffect.POISON));
                if (triggerCatalyze) {
                    this.addToBot(new ApplyPowerAction(m, p, new DiseasePower(m, p, 1), 1, AttackEffect.NONE));
                }
            }
            if (!freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        isDone = true;
    }
}
