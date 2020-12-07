package potionbrewer;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.random.Random;
import potionbrewer.optioncards.ChoosePotion;
import potionbrewer.potions.tonics.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

public class TonicLibrary implements CustomSavable<Integer> {
    public static HashMap<String, Class<? extends AbstractPotion>> tonicList;
    private static Random rng;

    static {
        tonicList = new HashMap<>();
        tonicList.put(BlockTonic.ID, BlockTonic.class);
        tonicList.put(EnergyTonic.ID, EnergyTonic.class);
        tonicList.put(ExplosiveTonic.ID, ExplosiveTonic.class);
        tonicList.put(FearTonic.ID, FearTonic.class);
        tonicList.put(FireTonic.ID, FireTonic.class);
        tonicList.put(FlexTonic.ID, FlexTonic.class);
        tonicList.put(SpeedTonic.ID, SpeedTonic.class);
        tonicList.put(SwiftTonic.ID, SwiftTonic.class);
        tonicList.put(WeakTonic.ID, WeakTonic.class);
    }

    public static void initialize() {
        if (rng == null) {
            rng = new Random(Settings.seed);
        }
    }

    public ArrayList<AbstractCard> getRandomChoices(int amount) {
        ArrayList<String> list = new ArrayList<>(tonicList.keySet());
        Random forShuffle = new Random(rng.randomLong());
        Collections.shuffle(list, forShuffle.random);
        return list.subList(0, amount).stream().map(ChoosePotion::new).collect(Collectors.toCollection(ArrayList::new));
    }

    public static boolean isATonic(AbstractPotion potion) {
        return tonicList.containsKey(potion.ID);
    }

    public AbstractPotion getRandomTonic() {
        try {
            String[] list = tonicList.keySet().toArray(new String[0]);
            int i = rng.random(0, list.length - 1);
            Class<? extends AbstractPotion> p = tonicList.get(list[i]);
            return p.newInstance();
        } catch (Exception e) {
            System.out.println("Error getting random tonic!");
            return new FireTonic();
        }
    }

    public static AbstractPotion getTonicById(String Id) {
        try {
            if (tonicList.containsKey(Id)) {
                Class<? extends AbstractPotion> clz = tonicList.get(Id);
                return clz.newInstance();
            } else {
                return new FireTonic();
            }
        } catch (Exception err) {
            err.printStackTrace();
            return new FireTonic();
        }
    }

    @Override
    public Integer onSave() {
        return rng.counter;
    }

    @Override
    public void onLoad(Integer savedValue) {
        int randomCount = Optional.ofNullable(savedValue).orElse(0);
        rng = new Random(Settings.seed, randomCount);
    }
}
