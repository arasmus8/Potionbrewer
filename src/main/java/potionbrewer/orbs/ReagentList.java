package potionbrewer.orbs;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class ReagentList {
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

}
