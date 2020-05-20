package potionbrewer;

import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.cards.option.ChoosePotion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PotionCardConsoleCommand extends ConsoleCommand {

    public PotionCardConsoleCommand() {
        maxExtraTokens = 3;
        minExtraTokens = 2;
        requiresPlayer = true;
        simpleCheck = true;
        followup.put("obtain", MakePotionCardCommand.class);
        followup.put("use", MakePotionCardCommand.class);
    }

    @Override
    protected void execute(String[] tokens, int depth) {
        if (AbstractDungeon.player == null) {
            DevConsole.log("Cannot generate card - player is null");
        } else {
            DevConsole.log("Cannot generate card - ID required!");
        }
    }

    public static class MakePotionCardCommand extends ConsoleCommand {
        public MakePotionCardCommand() {
            minExtraTokens = 1;
            maxExtraTokens = 2;
            requiresPlayer = true;
            simpleCheck = true;
        }

        @Override
        protected ArrayList<String> extraOptions(String[] tokens, int depth) {
            if (depth >= 2) {
                return new ArrayList<>(ChoosePotion.imageMap.keySet());
            }
            return new ArrayList<>();
        }

        @Override
        protected void execute(String[] tokens, int depth) {
            if (AbstractDungeon.player == null) {
                DevConsole.log("Cannot generate card - player is null");
            } else {
                if (tokens.length >= 3) {
                    String potionId = Arrays.stream(tokens)
                            .skip(2)
                            .collect(Collectors.joining(" "));
                    AbstractCard card = new ChoosePotion(potionId, tokens[1].equals("obtain"));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
                } else {
                    DevConsole.log("Cannot generate card - ID required!");
                }
            }
        }
    }
}
