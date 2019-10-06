package potionbrewer.potions.tonics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;

public class TonicLibrary {
    private static ArrayList<Class> tonicList;

    static {
        tonicList = new ArrayList<>();
        tonicList.add(BlockTonic.class);
        tonicList.add(EnergyTonic.class);
        tonicList.add(ExplosiveTonic.class);
        tonicList.add(FearTonic.class);
        tonicList.add(FireTonic.class);
        tonicList.add(FlexTonic.class);
        tonicList.add(SpeedTonic.class);
        tonicList.add(SwiftTonic.class);
        tonicList.add(WeakTonic.class);
    }

    public static AbstractPotion getRandomTonic() {
        try {
            int i = MathUtils.random(0, tonicList.size() - 1);
            Class p = tonicList.get(i);
            return (AbstractPotion)p.newInstance();
        } catch (Exception e) {
            System.out.println("Error getting random tonic!");
            return new FireTonic();
        }
    }
}
