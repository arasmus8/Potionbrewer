package potionbrewer.orbs;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import java.util.HashMap;

public class ReagentList {
    private static HashMap<String, Class> reagentsById;

    static {
        reagentsById = new HashMap<>();
        reagentsById.put(Barb.ORB_ID, Barb.class);
        reagentsById.put(Bile.ORB_ID, Bile.class);
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
        reagentsById.put(Lightning.ORB_ID, Lightning.class);
        reagentsById.put(Mechanism.ORB_ID, Mechanism.class);
        reagentsById.put(Meteorite.ORB_ID, Meteorite.class);
        reagentsById.put(Needle.ORB_ID, Needle.class);
        reagentsById.put(Nethershroud.ORB_ID, Nethershroud.class);
        reagentsById.put(PhilosopherShard.ORB_ID, PhilosopherShard.class);
        reagentsById.put(PowerCore.ORB_ID, PowerCore.class);
        reagentsById.put(Root.ORB_ID, Root.class);
        reagentsById.put(RunicShape.ORB_ID, RunicShape.class);
        reagentsById.put(SerpentSkull.ORB_ID, SerpentSkull.class);
        reagentsById.put(Silk.ORB_ID, Silk.class);
        reagentsById.put(Skull.ORB_ID, Skull.class);
        reagentsById.put(Spore.ORB_ID, Spore.class);
        reagentsById.put(Steel.ORB_ID, Steel.class);
        reagentsById.put(Sulphur.ORB_ID, Sulphur.class);
        reagentsById.put(Tentacle.ORB_ID, Tentacle.class);
        reagentsById.put(TinyHat.ORB_ID, TinyHat.class);
        reagentsById.put(Tooth.ORB_ID, Tooth.class);
        reagentsById.put(Wax.ORB_ID, Wax.class);
    }

    public static AbstractOrb randomReagent() {
        int roll = MathUtils.random(0, 99);
        if(roll < 20) {
            return new Ichor();
        } else if(roll < 40) {
            return new Feather();
        } else if(roll < 49) {
            return new Silk();
        } else if(roll < 58) {
            return new Tooth();
        } else if(roll < 67) {
            return new Bone();
        } else if(roll < 76) {
            return new Grimace();
        } else if(roll < 85) {
            return new Spore();
        } else if(roll < 90) {
            return new Steel();
        } else if(roll < 95) {
            return new Ether();
        } else {
            return new Flame();
        }
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
        return new Ichor();
    }
}
