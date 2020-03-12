package potionbrewer.patches;

import com.badlogic.gdx.backends.lwjgl.audio.OpenALMusic;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.lib.*;
import javassist.CtBehavior;

@SpirePatch(
        clz = OpenALMusic.class,
        method = "play"
)
public class AudioCrashPrevent {
    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"errorCode"}
    )
    public static SpireReturn Insert(OpenALMusic _instance, int errorCode) {
        if (errorCode != 0) {
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.NewExprMatcher(GdxRuntimeException.class);
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
