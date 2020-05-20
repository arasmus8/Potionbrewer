package potionbrewer.orbs;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.random.Random;
import potionbrewer.cards.option.ChooseReagent;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReagentList implements CustomSavable<Integer> {
    public static HashMap<String, Class<? extends Reagent>> reagentsById;
    public static ArrayList<String> inCombatPotionReagentList;
    private Random rng;

    public ReagentList() {
        rng = new Random();
    }

    static {
        reagentsById = new HashMap<>();
        reagentsById.put("", null);
        reagentsById.put(Barb.ORB_ID, Barb.class);
        reagentsById.put(Beak.ORB_ID, Beak.class);
        reagentsById.put(Bile.ORB_ID, Bile.class);
        reagentsById.put(Bludgeon.ORB_ID, Bludgeon.class);
        reagentsById.put(Bone.ORB_ID, Bone.class);
        reagentsById.put(Bug.ORB_ID, Bug.class);
        reagentsById.put(Chest.ORB_ID, Chest.class);
        reagentsById.put(CityCrest.ORB_ID, CityCrest.class);
        reagentsById.put(Clay.ORB_ID, Clay.class);
        reagentsById.put(Crown.ORB_ID, Crown.class);
        reagentsById.put(Ether.ORB_ID, Ether.class);
        reagentsById.put(Eye.ORB_ID, Eye.class);
        reagentsById.put(Feather.ORB_ID, Feather.class);
        reagentsById.put(FeyFire.ORB_ID, FeyFire.class);
        reagentsById.put(Fireflies.ORB_ID, Fireflies.class);
        reagentsById.put(Flame.ORB_ID, Flame.class);
        reagentsById.put(Gold.ORB_ID, Gold.class);
        reagentsById.put(Grimace.ORB_ID, Grimace.class);
        reagentsById.put(Hand.ORB_ID, Hand.class);
        reagentsById.put(Horn.ORB_ID, Horn.class);
        reagentsById.put(Ichor.ORB_ID, Ichor.class);
        reagentsById.put(Ink.ORB_ID, Ink.class);
        reagentsById.put(Iodine.ORB_ID, Iodine.class);
        reagentsById.put(Jaw.ORB_ID, Jaw.class);
        reagentsById.put(LaserCore.ORB_ID, LaserCore.class);
        reagentsById.put(Lightning.ORB_ID, Lightning.class);
        reagentsById.put(Mechanism.ORB_ID, Mechanism.class);
        reagentsById.put(Meteorite.ORB_ID, Meteorite.class);
        reagentsById.put(Needle.ORB_ID, Needle.class);
        reagentsById.put(Nethershroud.ORB_ID, Nethershroud.class);
        reagentsById.put(Pebble.ORB_ID, Pebble.class);
        reagentsById.put(PhilosopherShard.ORB_ID, PhilosopherShard.class);
        reagentsById.put(Pocketwatch.ORB_ID, Pocketwatch.class);
        reagentsById.put(GuardianScales.ORB_ID, GuardianScales.class);
        reagentsById.put(Pyramid.ORB_ID, Pyramid.class);
        reagentsById.put(Radiance.ORB_ID, Radiance.class);
        reagentsById.put(RitualJar.ORB_ID, RitualJar.class);
        reagentsById.put(Root.ORB_ID, Root.class);
        reagentsById.put(RunicShape.ORB_ID, RunicShape.class);
        reagentsById.put(Saltpeter.ORB_ID, Saltpeter.class);
        reagentsById.put(SerpentSkull.ORB_ID, SerpentSkull.class);
        reagentsById.put(Silk.ORB_ID, Silk.class);
        reagentsById.put(Skull.ORB_ID, Skull.class);
        reagentsById.put(Slime.ORB_ID, Slime.class);
        reagentsById.put(Spore.ORB_ID, Spore.class);
        reagentsById.put(Steel.ORB_ID, Steel.class);
        reagentsById.put(Storybook.ORB_ID, Storybook.class);
        reagentsById.put(Sulphur.ORB_ID, Sulphur.class);
        reagentsById.put(SuperSpore.ORB_ID, SuperSpore.class);
        reagentsById.put(Tentacle.ORB_ID, Tentacle.class);
        reagentsById.put(TinyHat.ORB_ID, TinyHat.class);
        reagentsById.put(Tooth.ORB_ID, Tooth.class);
        reagentsById.put(TrainTicket.ORB_ID, TrainTicket.class);
        reagentsById.put(TwistedRelic.ORB_ID, TwistedRelic.class);
        reagentsById.put(Wax.ORB_ID, Wax.class);

        inCombatPotionReagentList = new ArrayList<>();
        inCombatPotionReagentList.add(Beak.ORB_ID);
        inCombatPotionReagentList.add(Bile.ORB_ID);
        inCombatPotionReagentList.add(Bone.ORB_ID);
        inCombatPotionReagentList.add(Bug.ORB_ID);
        inCombatPotionReagentList.add(Chest.ORB_ID);
        inCombatPotionReagentList.add(Clay.ORB_ID);
        inCombatPotionReagentList.add(Crown.ORB_ID);
        inCombatPotionReagentList.add(Ether.ORB_ID);
        inCombatPotionReagentList.add(Eye.ORB_ID);
        inCombatPotionReagentList.add(Feather.ORB_ID);
        inCombatPotionReagentList.add(FeyFire.ORB_ID);
        inCombatPotionReagentList.add(Gold.ORB_ID);
        inCombatPotionReagentList.add(Grimace.ORB_ID);
        inCombatPotionReagentList.add(GuardianScales.ORB_ID);
        inCombatPotionReagentList.add(Horn.ORB_ID);
        inCombatPotionReagentList.add(Ichor.ORB_ID);
        inCombatPotionReagentList.add(Ink.ORB_ID);
        inCombatPotionReagentList.add(Iodine.ORB_ID);
        inCombatPotionReagentList.add(Mechanism.ORB_ID);
        inCombatPotionReagentList.add(Meteorite.ORB_ID);
        inCombatPotionReagentList.add(Needle.ORB_ID);
        inCombatPotionReagentList.add(Pyramid.ORB_ID);
        inCombatPotionReagentList.add(Radiance.ORB_ID);
        inCombatPotionReagentList.add(RitualJar.ORB_ID);
        inCombatPotionReagentList.add(Root.ORB_ID);
        inCombatPotionReagentList.add(RunicShape.ORB_ID);
        inCombatPotionReagentList.add(Saltpeter.ORB_ID);
        inCombatPotionReagentList.add(SerpentSkull.ORB_ID);
        inCombatPotionReagentList.add(Silk.ORB_ID);
        inCombatPotionReagentList.add(Skull.ORB_ID);
        inCombatPotionReagentList.add(Slime.ORB_ID);
        inCombatPotionReagentList.add(Spore.ORB_ID);
        inCombatPotionReagentList.add(Steel.ORB_ID);
        inCombatPotionReagentList.add(Sulphur.ORB_ID);
        inCombatPotionReagentList.add(Tentacle.ORB_ID);
        inCombatPotionReagentList.add(TinyHat.ORB_ID);
        inCombatPotionReagentList.add(Tooth.ORB_ID);
    }

    public static int indexFromReagent(Reagent o) {
        Set<String> keys = reagentsById.keySet();
        String[] list = keys.toArray(new String[0]);
        Arrays.sort(list);
        String id = o.ID;
        int len = list.length;
        return IntStream.range(0, len)
                .filter(i -> id.equals(list[i]))
                .findFirst() // first occurrence
                .orElse(-1); // No element found
    }

    public static Reagent fromId(String Id) {
        if (reagentsById.containsKey(Id)) {
            Class<? extends Reagent> clz = reagentsById.get(Id);
            try {
                return clz.newInstance();
            } catch (Exception err) {
                System.out.println("Error instantiating class for Id=" + Id);
            }
        }
        return new Slime();
    }

    private RandomEntry randomEntry(ArrayList<RandomEntry> toExclude) {
        ArrayList<RandomEntry> list = new ArrayList<>();
        list.add(new RandomEntry(9, new Bone()));
        list.add(new RandomEntry(5, new Ether()));
        list.add(new RandomEntry(15, new Beak()));
        list.add(new RandomEntry(4, new Flame()));
        list.add(new RandomEntry(9, new Grimace()));
        list.add(new RandomEntry(10, new Slime()));
        list.add(new RandomEntry(3, new Lightning()));
        list.add(new RandomEntry(12, new Silk()));
        list.add(new RandomEntry(9, new Spore()));
        list.add(new RandomEntry(5, new Steel()));
        list.add(new RandomEntry(12, new Tooth()));
        list.add(new RandomEntry(20, new Saltpeter()));
        list.add(new RandomEntry(15, new Iodine()));
        if (toExclude != null) {
            for (RandomEntry exclude : toExclude) {
                list.remove(exclude);
            }
        }
        list.sort(new SortScoreDescending());
        int total = list.stream().mapToInt(e -> e.chance).reduce(0, Integer::sum);
        int roll = rng.random(1, total);
        int index = -1, chanceSum = 0;
        while (chanceSum < roll) {
            index += 1;
            chanceSum += list.get(index).chance;
        }
        return list.get(index);
    }

    public Reagent randomReagent() {
        return randomEntry(null).reagent;
    }

    public ArrayList<AbstractCard> randomChoice(int amount) {
        ArrayList<RandomEntry> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            list.add(randomEntry(list));
        }
        return list.stream().map(re -> re.optionCard).collect(Collectors.toCollection(ArrayList::new));
    }

    public Reagent randomInCombatPotionReagent() {
        int roll = rng.random(inCombatPotionReagentList.size() - 1);
        return fromId(inCombatPotionReagentList.get(roll));
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

    public void initialize() {
        rng = new Random(Settings.seed);
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
