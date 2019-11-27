package potionbrewer;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.random.Random;
import potionbrewer.potions.BlacksmithPotion;
import potionbrewer.potions.SplittingPotion;

import java.util.ArrayList;
import java.util.Collections;

public class PotionLibrary implements CustomSavable<Integer> {
    private static Random rng;
    private static int randomCount = 0;
    private static ArrayList<String> inBattleIds;

    public static void initializePotionList(AbstractPlayer.PlayerClass playerClass) {
        ArrayList<String> ids = PotionHelper.getPotions(playerClass, false);
        ids.remove(RegenPotion.POTION_ID);
        ids.remove(BloodPotion.POTION_ID);
        ids.remove(FruitJuice.POTION_ID);
        ids.remove(FairyPotion.POTION_ID);
        ids.remove(EntropicBrew.POTION_ID);
        ids.remove(SplittingPotion.POTION_ID);
        ids.remove(BlacksmithPotion.POTION_ID);

        inBattleIds = new ArrayList<>(ids);

        if (rng == null) {
            rng = new Random(Settings.seed);
        }
    }

    public String getRandomPotionId() {
        if (inBattleIds == null) {
            return "";
        }
        randomCount += 1;
        return inBattleIds.get(rng.random(inBattleIds.size() - 1));
    }

    public ArrayList<String> getRandomPotionIdList(final int amount) {
        ArrayList<String> ret = new ArrayList<>(inBattleIds);
        Random forShuffle = new Random(rng.randomLong());
        randomCount += 1;
        Collections.shuffle(ret, forShuffle.random);
        return new ArrayList<>(ret.subList(0, amount));
    }

    @Override
    public Integer onSave() {
        return randomCount;
    }

    @Override
    public void onLoad(Integer savedValue) {
        randomCount = savedValue;
        rng = new Random(Settings.seed, randomCount);
    }
}
