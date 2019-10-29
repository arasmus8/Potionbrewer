package potionbrewer;

import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import potionbrewer.cards.Prototype;
import potionbrewer.orbs.Reagent;
import potionbrewer.orbs.ReagentList;

public class PrototypeConsoleCommand extends ConsoleCommand {

    public PrototypeConsoleCommand() {
        maxExtraTokens = 1;
        minExtraTokens = 0;
        requiresPlayer = true;
        simpleCheck = true;
    }

    @Override
    protected void execute(String[] tokens, int depth) {
        if (AbstractDungeon.player == null) {
            DevConsole.log("Cannot generate card - player is null");
        } else {
            if (tokens.length == 1) {
                AbstractCard card = new Prototype(
                        (Reagent) ReagentList.randomReagent(),
                        (Reagent) ReagentList.randomReagent(),
                        (Reagent) ReagentList.randomReagent()
                );
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            } else {
                int i;
                try {
                    i = Integer.parseInt(tokens[1]);// 31
                    Prototype card = new Prototype();
                    card.misc = i;
                    card.hydrate();
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                } catch (Exception e) {
                    DevConsole.log("Invalid argument: " + tokens[1] + " is not a number.");
                }
            }
        }

    }
}
