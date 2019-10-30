package potionbrewer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import potionbrewer.PotionbrewerMod;
import potionbrewer.util.TextureLoader;

import static potionbrewer.PotionbrewerMod.makeRelicOutlinePath;
import static potionbrewer.PotionbrewerMod.makeRelicPath;

public class BoosterPack extends CustomRelic {
    private static final int COMMON_CARDS = 3;
    private static final int UNCOMMON_CARDS = 2;
    private static final int RARE_CARDS = 1;
    public static final String ID = PotionbrewerMod.makeID(BoosterPack.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BoosterPack.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BoosterPack.png"));

    public BoosterPack() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        AbstractCard c;
        int i;
        AbstractPlayer player = AbstractDungeon.player;
        AbstractCard.CardRarity rarity;

        rarity = AbstractCard.CardRarity.COMMON;
        for (i = 0; i < COMMON_CARDS; i++) {
            if (player.hasRelic("PrismaticShard")) {
                c = CardLibrary.getAnyColorCard(rarity).makeCopy();
            } else {
                c = AbstractDungeon.getCard(rarity).makeCopy();
            }
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
        }

        rarity = AbstractCard.CardRarity.UNCOMMON;
        for (i = 0; i < UNCOMMON_CARDS; i++) {
            if (player.hasRelic("PrismaticShard")) {
                c = CardLibrary.getAnyColorCard(rarity).makeCopy();
            } else {
                c = AbstractDungeon.getCard(rarity).makeCopy();
            }
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
        }

        rarity = AbstractCard.CardRarity.RARE;
        for (i = 0; i < RARE_CARDS; i++) {
            if (player.hasRelic("PrismaticShard")) {
                c = CardLibrary.getAnyColorCard(rarity).makeCopy();
            } else {
                c = AbstractDungeon.getCard(rarity).makeCopy();
            }
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
