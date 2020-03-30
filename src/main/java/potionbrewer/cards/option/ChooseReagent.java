package potionbrewer.cards.option;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.orbs.*;

import java.util.HashMap;
import java.util.Map;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ChooseReagent extends AbstractCard {
    public static String ID = PotionbrewerMod.makeID(ChooseReagent.class.getSimpleName());
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

    public String reagentId;
    private static Map<String, String> imageMap;

    public ChooseReagent(final String id) {
        super(ID, name(id), portrait(id), -2, CARD_STRINGS.DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
        this.reagentId = id;
        Reagent r = fromId(reagentId);
        if (r == null) {
            r = new Slime();
        }
        rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[0] + r.getPotion().name + CARD_STRINGS.EXTENDED_DESCRIPTION[1] + r.getCardDescription();
        // rawDescription = (isVowel(name.charAt(0)) ? CARD_STRINGS.EXTENDED_DESCRIPTION[1] : CARD_STRINGS.EXTENDED_DESCRIPTION[0]) + this.name;
        exhaust = true;
        initializeDescription();
    }

    private static boolean isVowel(final char leading) {
        return leading == 'A' ||
                leading == 'E' ||
                leading == 'I' ||
                leading == 'O' ||
                leading == 'U' ||
                leading == 'a' ||
                leading == 'e' ||
                leading == 'i' ||
                leading == 'o' ||
                leading == 'u';
    }

    public ChooseReagent() {
        this(PotionbrewerMod.reagentList.randomReagent().ID);
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
        Reagent r = fromId(reagentId);
        if (r == null) {
            r = new Slime();
        }
        this.addToBot(new ChannelAction(r));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChooseReagent(reagentId);
    }

    public static Reagent fromId(final String id) {
        return ReagentList.fromId(id);
    }

    public static String name(final String id) {
        if (id == null) {
            return "Random Reagent";
        }
        Reagent r = fromId(id);
        if (r == null) {
            return "Unknown Reagent";
        } else {
            return r.name;
        }
    }

    public static String portrait(final String id) {
        return imageMap.getOrDefault(id, "green/skill/alchemize");
    }

    static {
        imageMap = new HashMap<>();

        imageMap.put(Bone.ORB_ID, "red/skill/offering");
        imageMap.put(Ether.ORB_ID, "green/skill/catalyst");
        imageMap.put(Beak.ORB_ID, "colorless/skill/apotheosis");
        imageMap.put(Flame.ORB_ID, "red/power/fire_breathing");
        imageMap.put(Grimace.ORB_ID, "colorless/skill/madness");
        imageMap.put(Slime.ORB_ID, "status/slimed");
        imageMap.put(Lightning.ORB_ID, "blue/skill/zap");
        imageMap.put(Silk.ORB_ID, "purple/attack/cut_through_fate");
        imageMap.put(Spore.ORB_ID, "curse/parasite");
        imageMap.put(Steel.ORB_ID, "red/power/metallicize");
        imageMap.put(Tooth.ORB_ID, "colorless/attack/bite");
    }
}
