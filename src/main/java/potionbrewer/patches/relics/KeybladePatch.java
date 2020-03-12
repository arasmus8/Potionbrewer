package potionbrewer.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ObtainKeyEffect;
import potionbrewer.relics.Keyblade;

@SpirePatch(
        clz = ObtainKeyEffect.class,
        method = "update"
)
public class KeybladePatch {
    public static void Postfix(ObtainKeyEffect _instance) {
        if (_instance.isDone) {
            AbstractDungeon.player.relics.stream()
                    .filter(r -> r instanceof Keyblade)
                    .forEach(r -> ((Keyblade) r).onKeyObtain());
        }
    }
}
