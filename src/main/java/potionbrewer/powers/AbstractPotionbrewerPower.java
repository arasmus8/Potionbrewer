package potionbrewer.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potionbrewer.PotionbrewerMod;

public class AbstractPotionbrewerPower extends AbstractPower {
    private final TextureAtlas powerAtlas = PotionbrewerMod.assets.loadAtlas(PotionbrewerMod.assetPath("images/powers/powers.atlas"));

    @Override
    protected void loadRegion(String fileName) {
        region48 = powerAtlas.findRegion("32/" + fileName);
        region128 = powerAtlas.findRegion("128/" + fileName);

        if (region48 == null && region128 == null) {
            super.loadRegion(fileName);
        }
    }
}
