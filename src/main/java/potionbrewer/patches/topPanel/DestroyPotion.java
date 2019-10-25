package potionbrewer.patches.topPanel;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import potionbrewer.relics.DiscardPotionRelic;

@SpirePatch(
        clz = TopPanel.class,
        method = "destroyPotion"
)
public class DestroyPotion {
    public static void Postfix(TopPanel __instance, int potionSlot) {
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            if (relic instanceof DiscardPotionRelic) {
                ((DiscardPotionRelic) relic).onDiscardPotion(potionSlot);
            }
        }
    }
}
