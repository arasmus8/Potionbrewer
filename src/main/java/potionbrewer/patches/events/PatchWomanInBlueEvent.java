package potionbrewer.patches.events;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WomanInBlue;
import com.megacrit.cardcrawl.localization.EventStrings;
import potionbrewer.characters.Potionbrewer;

public class PatchWomanInBlueEvent {
    @SpirePatch(
            clz = WomanInBlue.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class PatchConstructor {
        public static void Postfix(WomanInBlue __instance) {
            if (CardCrawlGame.isInARun() && AbstractDungeon.player.chosenClass == Potionbrewer.Enums.POTIONBREWER) {
                EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("potionbrewer:WomanInBlue");
                ReflectionHacks.setPrivateInherited(__instance, WomanInBlue.class, "body", eventStrings.DESCRIPTIONS[0]);
            }
        }
    }
}
