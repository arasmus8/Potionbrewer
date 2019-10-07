package potionbrewer.patches.toncis;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.potions.FirePotion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import potionbrewer.potions.tonics.TonicLibrary;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.helpers.PotionHelper",
        method = "getPotion"
)
public class PotionHelperGetPotion {
    private static final Logger logger = LogManager.getLogger(PotionHelperGetPotion.class);

    public PotionHelperGetPotion() {
    }// 13

    public static Object Postfix(Object __result, String potionID) {
        if (__result == null) {// 17
            return null;// 18
        } else if (__result instanceof FirePotion && !potionID.equals("Fire Potion")) {// 20
            logger.info("Getting tonic: " + potionID);// 21

            try {
                return TonicLibrary.getTonicById(potionID);
            } catch (Exception var3) {// 25
                logger.warn(var3.getMessage());// 26
                return new FirePotion();// 27
            }
        } else {
            return __result;// 30
        }
    }
}
