package potionbrewer.patches.potionPopUp;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CtBehavior;
import potionbrewer.relics.DiscardPotionRelic;

@SpirePatch(
        clz = PotionPopUp.class,
        method = "updateInput"
)
public class DestroyPotion {

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(PotionPopUp __instance) {
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            if (relic instanceof DiscardPotionRelic) {
                ((DiscardPotionRelic) relic).onDiscardPotion();
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(TopPanel.class, "destroyPotion");
            int[] matches = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{matches[1]};
        }
    }
}
