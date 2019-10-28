package potionbrewer.orbs;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import potionbrewer.cards.option.ChooseReagent;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReagentList {
    private static HashMap<String, Class> reagentsById;

    static {
        reagentsById = new HashMap<>();
        reagentsById.put("", null);
        reagentsById.put(Barb.ORB_ID, Barb.class);
        reagentsById.put(Bile.ORB_ID, Bile.class);
        reagentsById.put(Bludgeon.ORB_ID, Bludgeon.class);
        reagentsById.put(Bone.ORB_ID, Bone.class);
        reagentsById.put(Clay.ORB_ID, Clay.class);
        reagentsById.put(Crown.ORB_ID, Crown.class);
        reagentsById.put(Ether.ORB_ID, Ether.class);
        reagentsById.put(Eye.ORB_ID, Eye.class);
        reagentsById.put(Feather.ORB_ID, Feather.class);
        reagentsById.put(FeyFire.ORB_ID, FeyFire.class);
        reagentsById.put(Flame.ORB_ID, Flame.class);
        reagentsById.put(Gold.ORB_ID, Gold.class);
        reagentsById.put(Grimace.ORB_ID, Grimace.class);
        reagentsById.put(Hand.ORB_ID, Hand.class);
        reagentsById.put(Horn.ORB_ID, Horn.class);
        reagentsById.put(Ichor.ORB_ID, Ichor.class);
        reagentsById.put(Ink.ORB_ID, Ink.class);
        reagentsById.put(Jaw.ORB_ID, Jaw.class);
        reagentsById.put(LaserCore.ORB_ID, LaserCore.class);
        reagentsById.put(Lightning.ORB_ID, Lightning.class);
        reagentsById.put(Mechanism.ORB_ID, Mechanism.class);
        reagentsById.put(Meteorite.ORB_ID, Meteorite.class);
        reagentsById.put(Needle.ORB_ID, Needle.class);
        reagentsById.put(Nethershroud.ORB_ID, Nethershroud.class);
        reagentsById.put(Pocketwatch.ORB_ID, Pocketwatch.class);
        reagentsById.put(PhilosopherShard.ORB_ID, PhilosopherShard.class);
        reagentsById.put(PowerCore.ORB_ID, PowerCore.class);
        reagentsById.put(Pyramid.ORB_ID, Pyramid.class);
        reagentsById.put(RitualJar.ORB_ID, RitualJar.class);
        reagentsById.put(Root.ORB_ID, Root.class);
        reagentsById.put(RunicShape.ORB_ID, RunicShape.class);
        reagentsById.put(SerpentSkull.ORB_ID, SerpentSkull.class);
        reagentsById.put(Silk.ORB_ID, Silk.class);
        reagentsById.put(Skull.ORB_ID, Skull.class);
        reagentsById.put(Slime.ORB_ID, Slime.class);
        reagentsById.put(Spore.ORB_ID, Spore.class);
        reagentsById.put(Steel.ORB_ID, Steel.class);
        reagentsById.put(Sulphur.ORB_ID, Sulphur.class);
        reagentsById.put(Tentacle.ORB_ID, Tentacle.class);
        reagentsById.put(TinyHat.ORB_ID, TinyHat.class);
        reagentsById.put(Tooth.ORB_ID, Tooth.class);
        reagentsById.put(Wax.ORB_ID, Wax.class);
    }

    private static Reagent byIndex(int index) {
        Set<String> keys = reagentsById.keySet();
        String[] list = keys.toArray(new String[0]);
        Arrays.sort(list);
        try {
            return (Reagent) reagentsById.get(list[index]).newInstance();
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }

    public static Reagent firstReagent(int misc) {
        if (misc == 0) {
            return null;
        }
        int idx = misc % 100;
        return byIndex(idx);
    }

    public static Reagent secondReagent(int misc) {
        if (misc == 0) {
            return null;
        }
        int idx = misc / 100 % 100;
        return byIndex(idx);
    }

    public static Reagent thirdReagent(int misc) {
        if (misc == 0) {
            return null;
        }
        int idx = misc / 10000 % 100;
        return byIndex(idx);
    }

    public static int indexFromReagent(Reagent o) {
        Set<String> keys = reagentsById.keySet();
        String[] list = keys.toArray(new String[0]);
        Arrays.sort(list);
        String id = o.ID;
        int len = list.length;
        return IntStream.range(0, len)
                .filter(i -> id.equals(list[i]))
                .findFirst() // first occurence
                .orElse(-1); // No element found
    }

    public static int buildMisc(Reagent first, Reagent second, Reagent third) {
        int f = indexFromReagent(first);
        int s = indexFromReagent(second);
        int t = indexFromReagent(third);

        if (f == -1 || s == -1 || t == -1) {
            throw new IllegalThreadStateException("Invalid orb!");
        }

        return t * 10000 + s * 100 + f;
    }

    public static AbstractOrb fromId(String Id) {
        if (reagentsById.containsKey(Id)) {
            Class clz = reagentsById.get(Id);
            try {
                return (AbstractOrb) clz.newInstance();
            } catch (Exception err) {
                System.out.println("Error instanciating class for Id=" + Id);
            }
        }
        return new Slime();
    }

    private static RandomEntry randomEntry(ArrayList<RandomEntry> toExclude) {
        ArrayList<RandomEntry> list = new ArrayList<>();
        list.add(new RandomEntry(9, new Bone()));
        list.add(new RandomEntry(5, new Ether()));
        list.add(new RandomEntry(15, new Feather()));
        list.add(new RandomEntry(5, new Flame()));
        list.add(new RandomEntry(9, new Grimace()));
        list.add(new RandomEntry(20, new Slime()));
        list.add(new RandomEntry(5, new Lightning()));
        list.add(new RandomEntry(9, new Silk()));
        list.add(new RandomEntry(9, new Spore()));
        list.add(new RandomEntry(5, new Steel()));
        list.add(new RandomEntry(9, new Tooth()));
        if (toExclude != null) {
            for (RandomEntry exclude : toExclude) {
                list.remove(exclude);
            }
        }
        list.sort(new SortScoreDescending());
        int total = list.stream().mapToInt(e -> e.chance).reduce(0, Integer::sum);
        int roll = MathUtils.random(1, total);
        int index = -1, chanceSum = 0;
        while (chanceSum < roll) {
            index += 1;
            chanceSum += list.get(index).chance;
        }
        return list.get(index);
    }

    public static AbstractOrb randomReagent() {
        return randomEntry(null).reagent;
    }

    public static ArrayList<AbstractCard> randomChoice(int amount) {
        ArrayList<RandomEntry> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            list.add(randomEntry(list));
        }
        return list.stream().map(re -> re.optionCard).collect(Collectors.toCollection(ArrayList::new));
    }

    public static AbstractCard randomChoice() {
        return randomEntry(null).optionCard;
    }

    private static class RandomEntry {
        protected int chance;
        protected AbstractCard optionCard;
        protected Reagent reagent;

        public RandomEntry(int chance, Reagent reagent) {
            this.chance = chance;
            this.optionCard = new ChooseReagent(reagent.ID);
            this.reagent = reagent;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof RandomEntry) {
                return ((RandomEntry) obj).reagent.ID.equals(this.reagent.ID);
            }
            return false;
        }
    }

    private static class SortScoreDescending implements Comparator<RandomEntry> {
        @Override
        public int compare(RandomEntry o1, RandomEntry o2) {
            return o2.chance - o1.chance;
        }
    }
}
