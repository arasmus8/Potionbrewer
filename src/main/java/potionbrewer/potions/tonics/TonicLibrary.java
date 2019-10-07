package potionbrewer.potions.tonics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.HashMap;

public class TonicLibrary {
    public static HashMap<String, Class<? extends AbstractPotion>> tonicList;

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

    public static AbstractPotion getRandomTonic() {
        try {
            String[] list = tonicList.keySet().toArray(new String[0]);
            int i = MathUtils.random(0, list.length - 1);
            Class p = tonicList.get(list[i]);
            return (AbstractPotion)p.newInstance();
        } catch (Exception e) {
            System.out.println("Error getting random tonic!");
            return new FireTonic();
        }
    }

    public static AbstractPotion getTonicById(String Id) {
        try {
            if (tonicList.containsKey(Id)) {
                Class clz = tonicList.get(Id);
                return (AbstractPotion) clz.newInstance();
            } else {
                return new FireTonic();
            }
        } catch (Exception err) {
            err.printStackTrace();
            return new FireTonic();
        }
    }
}
