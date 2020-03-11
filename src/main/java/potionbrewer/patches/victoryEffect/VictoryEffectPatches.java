package potionbrewer.patches.victoryEffect;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.vfx.PotionbrewerVictoryEffect;

import java.util.ArrayList;

public class VictoryEffectPatches {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @SpirePatch(
            clz = VictoryScreen.class,
            method = "updateVfx"
    )
    public static class UpdateVfxPatch {
        public static SpireReturn Prefix(VictoryScreen _instance) {
            if (AbstractDungeon.player.chosenClass == Potionbrewer.Enums.POTIONBREWER) {
                ArrayList<AbstractGameEffect> effects = (ArrayList<AbstractGameEffect>) ReflectionHacks.getPrivate(_instance, VictoryScreen.class, "effect");
                for (AbstractGameEffect effect : effects) {
                    if (effect instanceof PotionbrewerVictoryEffect) {
                        // already created the effect
                        return SpireReturn.Return(null);
                    }
                }
                effects.add(new PotionbrewerVictoryEffect());
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
