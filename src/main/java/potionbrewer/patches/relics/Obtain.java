package potionbrewer.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import potionbrewer.relics.AlchemistKit;
import potionbrewer.relics.PotionKit;

@SpirePatch(clz = AbstractRelic.class, method = "obtain")
public class Obtain {

    @SpirePrefixPatch
    public static SpireReturn ObtainAlchemistKit(AbstractRelic __instance) {
        if (__instance.relicId.equals(AlchemistKit.ID)) {
            int slot = -1;
            for (AbstractRelic relic : AbstractDungeon.player.relics) {
                if (relic.relicId.equals(PotionKit.ID)) {
                    slot = AbstractDungeon.player.relics.indexOf(relic);
                }
            }
            if (slot >= 0) {
                __instance.instantObtain(AbstractDungeon.player, slot, true);// 248
                AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;// 249
                return SpireReturn.Return(null);
            }
        }
        return SpireReturn.Continue();
    }
}
