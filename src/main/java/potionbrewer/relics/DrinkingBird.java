package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.powers.BrewPotionPower;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class DrinkingBird extends CustomRelic {

    public static final String ID = PotionbrewerMod.makeID(DrinkingBird.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DrinkingBird.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DrinkingBird.png"));

    public DrinkingBird() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.CLINK);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractPotion p = AbstractDungeon.returnRandomPotion(true);
        AbstractPlayer player = AbstractDungeon.player;
        int turns = 3;
        if (player.hasRelic(BunsenBurner.ID)) {
            turns -= 1;
        }
        addToBot(new ApplyPowerAction(player, player, new BrewPotionPower(player, turns, p)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
