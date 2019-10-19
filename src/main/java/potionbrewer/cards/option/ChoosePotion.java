package potionbrewer.cards.option;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.UseTempPotionAction;
import potionbrewer.potions.tonics.*;

import java.util.HashMap;
import java.util.Map;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ChoosePotion extends AbstractCard {
    public static String ID = PotionbrewerMod.makeID(ChoosePotion.class.getSimpleName());
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

    private String potionId;
    private boolean obtain;
    private static Map<String, String> imageMap;

    public ChoosePotion(final String id, boolean obtain) {
        super(ID, name(id), portrait(id), -2, CARD_STRINGS.DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
        this.potionId = id;
        this.obtain = obtain;
        rawDescription = obtain ? CARD_STRINGS.EXTENDED_DESCRIPTION[1] : CARD_STRINGS.EXTENDED_DESCRIPTION[0] + this.name;
        initializeDescription();
    }

    public ChoosePotion(final String id) {
        this(id, false);
    }

    public ChoosePotion() {
        this(PotionHelper.getRandomPotion().ID, false);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPotion p = fromId(potionId);
        if (p == null) {
            p = new FireTonic();
        }
        if (obtain) {
            this.addToBot(new ObtainPotionAction(p));
        }
        this.addToBot(new UseTempPotionAction(p, AbstractDungeon.getRandomMonster()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChoosePotion(potionId, obtain);
    }

    public static AbstractPotion fromId(final String id) {
        try {
            return BaseMod.getPotionClass(id).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String name(final String id) {
        AbstractPotion p = fromId(id);
        if (p == null) {
            return "Unknown Potion";
        } else {
            return p.name;
        }
    }

    public static String portrait(final String id) {
        return imageMap.getOrDefault(id, "green/skill/alchemize");
    }

    static {
        imageMap = new HashMap<>();
        imageMap.put(BlockTonic.ID, "red/skill/sentinel");
        imageMap.put(EnergyTonic.ID, "blue/skill/double_energy");
        imageMap.put(ExplosiveTonic.ID, "red/attack/cleave");
        imageMap.put(FearTonic.ID, "green/skill/terror");
        imageMap.put(FireTonic.ID, "red/attack/immolate");
        imageMap.put(FlexTonic.ID, "red/skill/flex");
        imageMap.put(SpeedTonic.ID, "green/skill/backflip");
        imageMap.put(SwiftTonic.ID, "blue/skill/skim");
        imageMap.put(WeakTonic.ID, "red/skill/intimidate");
    }
}
