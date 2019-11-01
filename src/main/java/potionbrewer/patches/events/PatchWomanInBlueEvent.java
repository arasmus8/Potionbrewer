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
            method = SpirePatch.STATICINITIALIZER
    )
    public static class PatchStaticInitializer {

        public static void Postfix() {
            if (AbstractDungeon.player.chosenClass == Potionbrewer.Enums.POTIONBREWER) {
                EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("potionbrewer:WomanInBlue");
                ReflectionHacks.setPrivateStaticFinal(WomanInBlue.class, "DIALOG_1", eventStrings.DESCRIPTIONS[0]);
            }
        }
    }
}
