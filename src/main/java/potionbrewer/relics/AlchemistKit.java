package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.PotionbrewerMod;
import potionbrewer.powers.TwoForOnePower;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class AlchemistKit extends CustomRelic {

    public static final String ID = PotionbrewerMod.makeID(AlchemistKit.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AlchemistKit.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("AlchemistKit.png"));

    public AlchemistKit() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p, p, new TwoForOnePower(p, 1), 1));
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(PotionKit.ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
