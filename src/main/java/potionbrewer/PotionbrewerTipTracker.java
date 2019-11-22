package potionbrewer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;

public class PotionbrewerTipTracker {
    private static final Logger logger = LogManager.getLogger(PotionbrewerTipTracker.class.getName());
    public static final String REAGENTS = "potionbrewer:ReagentTip";
    public static HashMap<String, Boolean> tips = new HashMap<>();

    public PotionbrewerTipTracker() {
    }

    public static void initialize() {
        tips.put(REAGENTS, PotionbrewerMod.config.getBool(REAGENTS));
    }

    public static boolean hasShown(String tip) {
        return tips.getOrDefault(tip, false);
    }

    public static void neverShowAgain(String tip) {
        logger.info("Never showing again: " + tip);
        PotionbrewerMod.config.setBool(tip, true);
        tips.put(tip, true);

        try {
            PotionbrewerMod.config.save();
        } catch (IOException var2) {
            logger.catching(var2);
        }

    }
}
