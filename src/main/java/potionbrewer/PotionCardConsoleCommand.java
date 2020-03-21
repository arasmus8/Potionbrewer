package potionbrewer;

import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.cards.option.ChoosePotion;

import java.util.ArrayList;

public class PotionCardConsoleCommand extends ConsoleCommand {

    public PotionCardConsoleCommand() {
        maxExtraTokens = 1;
        minExtraTokens = 0;
        requiresPlayer = true;
        simpleCheck = true;
    }

    @Override
    protected ArrayList<String> extraOptions(String[] tokens, int depth) {
        if (depth == 1) {
            return new ArrayList<>(ChoosePotion.imageMap.keySet());
        }
        return new ArrayList<>();
    }

    @Override
    protected void execute(String[] tokens, int depth) {
        if (AbstractDungeon.player == null) {
            DevConsole.log("Cannot generate card - player is null");
        } else {
            if (tokens.length == 2) {
                AbstractCard card = new ChoosePotion(tokens[1]);
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
            } else {
                DevConsole.log("Cannot generate card - ID required!");
            }
        }

    }
}
