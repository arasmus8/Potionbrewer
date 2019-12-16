package potionbrewer.patches.abstractDungeon;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.events.JunkPileEvent;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "initializeCardPools"
)
public class InitializeCardPools {
    public static void Prefix(AbstractDungeon __instance) {
        if (!(AbstractDungeon.player instanceof Potionbrewer)) {
            AbstractDungeon.eventList.remove(JunkPileEvent.ID);
        }
    }
}
