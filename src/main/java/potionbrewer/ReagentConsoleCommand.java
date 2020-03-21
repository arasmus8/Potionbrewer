package potionbrewer;

import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.orbs.Reagent;
import potionbrewer.orbs.ReagentList;

import java.util.ArrayList;

public class ReagentConsoleCommand extends ConsoleCommand {

    public ReagentConsoleCommand() {
        maxExtraTokens = 1;
        minExtraTokens = 1;
        requiresPlayer = true;
        simpleCheck = true;
    }

    @Override
    protected ArrayList<String> extraOptions(String[] tokens, int depth) {
        if (depth == 1) {
            return new ArrayList<>(ReagentList.reagentsById.keySet());
        }
        return new ArrayList<>();
    }

    @Override
    protected void execute(String[] tokens, int depth) {
        if (AbstractDungeon.player == null) {
            DevConsole.log("Cannot generate reagent - player is null");
        } else {
            if (tokens.length == 2) {
                Reagent reagent = ReagentList.fromId(tokens[1]);
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(reagent));
            } else {
                errorMsg();
            }
        }
    }

    @Override
    protected void errorMsg() {
        DevConsole.log("Missing Reagent Id!");
    }
}
