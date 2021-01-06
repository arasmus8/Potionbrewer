package potionbrewer.patches.tipHelper;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;
import potionbrewer.cards.Collect;
import potionbrewer.orbs.Reagent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Render {
    @SpirePatch(
            clz = TipHelper.class,
            method = "render"
    )
    public static class RenderTipForCollect {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void RenderTipForCollectOnHover(SpriteBatch sb) {
            if (AbstractDungeon.player != null) {
                AbstractMonster m = ReflectionHacks.getPrivate(AbstractDungeon.player, AbstractPlayer.class, "hoveredMonster");
                AbstractCard c = AbstractDungeon.player.hoveredCard;
                if (m != null && c instanceof Collect) {
                    Reagent r = (Reagent) Collect.getOrbForMonster(m);
                    String HEADER = Collect.CARD_STRINGS.EXTENDED_DESCRIPTION[0] + r.name;
                    String BODY = r.description;
                    Float BODY_TEXT_WIDTH = ReflectionHacks.getPrivateStatic(TipHelper.class, "BODY_TEXT_WIDTH");
                    Float TIP_DESC_LINE_SPACING = ReflectionHacks.getPrivateStatic(TipHelper.class, "TIP_DESC_LINE_SPACING");
                    float textHeight = -FontHelper.getSmartHeight(FontHelper.tipBodyFont, BODY, BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - 7.0F * Settings.scale;
                    ReflectionHacks.setPrivateStatic(TipHelper.class, "textHeight", textHeight);

                    Class<?> clz = TipHelper.class;
                    try {
                        Method renderTipBox = clz.getDeclaredMethod("renderTipBox", float.class, float.class, SpriteBatch.class, String.class, String.class);
                        renderTipBox.setAccessible(true);
                        renderTipBox.invoke(null, m.hb.x, m.hb.y + m.hb.height, sb, HEADER, BODY);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(TipHelper.class, "renderedTipThisFrame");
                List<Matcher> matchers = IntStream.of(1)
                        .mapToObj(i -> finalMatcher)
                        .collect(Collectors.toList());

                return LineFinder.findInOrder(ctMethodToPatch, matchers, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = AbstractMonster.class,
            method = "renderTip"
    )
    public static class AddTipForMonster {
        @SpirePostfixPatch
        public static void AddTipForMonsterIfEmpty(AbstractMonster __instance, SpriteBatch sb) {
            ArrayList<PowerTip> tips = ReflectionHacks.getPrivate(__instance, AbstractCreature.class, "tips");
            if (tips.isEmpty()) {
                AbstractMonster m = ReflectionHacks.getPrivate(AbstractDungeon.player, AbstractPlayer.class, "hoveredMonster");
                AbstractCard c = AbstractDungeon.player.hoveredCard;
                if (m != null && c instanceof Collect) {
                    tips.add(new PowerTip("Collect", "Collect a Reagent."));
                    TipHelper.queuePowerTips(__instance.hb.cX + __instance.hb.width / 2.0F,
                            __instance.hb.cY + TipHelper.calculateAdditionalOffset(tips, __instance.hb.cY),
                            tips);
                }
            }
        }
    }
}
