package potionbrewer.patches.PotionViewScreen;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.compendium.PotionViewScreen;

public class PotionViewScreenPatch {
    @SpirePatch(
            clz = PotionViewScreen.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class CtorPatch {
        public static void Postfix(PotionViewScreen _instance) {
            ReflectionHacks.setPrivate(_instance, PotionViewScreen.class, "scrollUpperBound", 1500f * Settings.scale);
            ReflectionHacks.setPrivate(_instance, PotionViewScreen.class, "scrollLowerBound", Settings.HEIGHT - 100f * Settings.scale);
            ReflectionHacks.setPrivate(_instance, PotionViewScreen.class, "scrollY", Settings.HEIGHT - 100f * Settings.scale);
            ReflectionHacks.setPrivate(_instance, PotionViewScreen.class, "targetY", Settings.HEIGHT - 100f * Settings.scale);
        }
    }

    @SpirePatch(
            clz = PotionViewScreen.class,
            method = "open"
    )
    public static class OpenPatch {
        public static void Postfix(PotionViewScreen _instance) {
            ReflectionHacks.setPrivate(_instance, PotionViewScreen.class, "scrollY", Settings.HEIGHT - 100f * Settings.scale);
            ReflectionHacks.setPrivate(_instance, PotionViewScreen.class, "targetY", Settings.HEIGHT - 100f * Settings.scale);
        }
    }
}
