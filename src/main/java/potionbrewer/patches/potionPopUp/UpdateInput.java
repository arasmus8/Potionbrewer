package potionbrewer.patches.potionPopUp;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CtBehavior;
import potionbrewer.potions.SplittingPotion;
import potionbrewer.relics.DiscardPotionRelic;

import java.util.Arrays;

@SpirePatch(
        clz = PotionPopUp.class,
        method = "updateInput"
)
public class UpdateInput {

    @SpireInsertPatch(
            locator = Locator1.class
    )
    public static void onDiscardPotion(PotionPopUp __instance) {
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            if (relic instanceof DiscardPotionRelic) {
                ((DiscardPotionRelic) relic).onDiscardPotion();
            }
        }
    }

    private static class Locator1 extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(TopPanel.class, "destroyPotion");
            int[] matches = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{matches[1]};
        }
    }

    @SpireInsertPatch(
            locator = Locator2.class
    )
    public static void onUseSplittingPotionOutOfCombat(PotionPopUp __instance) {
        AbstractPotion potion = (AbstractPotion) ReflectionHacks.getPrivate(__instance, PotionPopUp.class, "potion");
        if (potion instanceof SplittingPotion && ((SplittingPotion) potion).createdCount < potion.getPotency()) {
            AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion());
        }
    }

    private static class Locator2 extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(TopPanel.class, "destroyPotion");
            return Arrays.stream(LineFinder.findInOrder(ctMethodToPatch, finalMatcher)).map(i -> i + 1).toArray();
        }
    }
}
