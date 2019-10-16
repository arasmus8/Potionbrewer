package potionbrewer.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import potionbrewer.PotionbrewerMod;
import potionbrewer.potions.DiscountPotion;

public class Gold extends Reagent {
    public static final String ORB_ID = PotionbrewerMod.makeID("Gold");
    private static final Texture img;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public Gold() {
        super(ORB_ID, img, orbString.NAME, DESC);
        exhaust = true;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Gold();
    }

    @Override
    public Texture getTexture() {
        return img;
    }

    @Override
    public AbstractPotion getPotion() {
        return new DiscountPotion();
    }

    @Override
    public void doEffects(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.gainGold(5);
        for (int i = 0; i < 5; ++i) {// 39
            AbstractDungeon.effectList.add(new GainPennyEffect(m, m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, true));
        }
    }

    @Override
    public String getCardDescription() {
        return DESC[1];
    }

    static {
        ImageMaster.loadRelicImg("Old Coin", "oldCoin.png");
        img = ImageMaster.getRelicImg("Old Coin");
    }
}