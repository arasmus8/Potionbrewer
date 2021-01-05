package potionbrewer.util;

import com.evacipated.cardcrawl.mod.widepotions.WidePotionsMod;
import potionbrewer.potions.*;

public class WidePotionHelper {
    public static void setupWidePotions() {
        WidePotionsMod.whitelistSimplePotion(AcidPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(BarricadePotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(BlacksmithPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(BoundlessPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(CleansingPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(DiscountPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(EndurancePotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(HastePotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(InfectionPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(MidasPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(NoxiousPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(QuicksilverPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(StunPotion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(ToxicPotion.POTION_ID);

        WidePotionsMod.whitelistComplexPotion(FreezingPotion.POTION_ID, new WideFreezingPotion());
        WidePotionsMod.whitelistComplexPotion(SplittingPotion.POTION_ID, new WideSplittingPotion());
    }
}
