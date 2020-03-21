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

import java.util.ArrayList;

public class PrototypeConsoleCommand extends ConsoleCommand {

    public PrototypeConsoleCommand() {
        maxExtraTokens = 3;
        minExtraTokens = 0;
        requiresPlayer = true;
        simpleCheck = true;
    }

    @Override
    protected ArrayList<String> extraOptions(String[] tokens, int depth) {
        if (depth > 0 && depth < 5) {
            return new ArrayList<>(ReagentList.reagentsById.keySet());
        }
        return new ArrayList<>();
    }

    @Override
    protected void execute(String[] tokens, int depth) {
        if (AbstractDungeon.player == null) {
            DevConsole.log("Cannot generate card - player is null");
        } else {
            if (tokens.length == 1) {
                AbstractCard card = new Prototype(
                        (Reagent) PotionbrewerMod.reagentList.randomReagent(),
                        (Reagent) PotionbrewerMod.reagentList.randomReagent(),
                        (Reagent) PotionbrewerMod.reagentList.randomReagent()
                );
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            } else if (tokens.length == 4) {
                int i;
                try {
                    Reagent a = ReagentList.fromId(tokens[1]);
                    Reagent b = ReagentList.fromId(tokens[2]);
                    Reagent c = ReagentList.fromId(tokens[3]);
                    Prototype card = new Prototype(a, b, c);
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                } catch (Exception e) {
                    DevConsole.log("Something went wrong!");
                }
            } else {
                DevConsole.log("Cannot generate card - 3 Reagents required!");
            }
        }

    }
}
