package potionbrewer.cards.option;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.random.Random;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.UseTempPotionAction;
import potionbrewer.potions.*;
import potionbrewer.potions.tonics.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ChoosePotion extends AbstractCard {
    public static String ID = PotionbrewerMod.makeID(ChoosePotion.class.getSimpleName());
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

    public String potionId;
    public boolean obtain;
    private static Map<String, String> imageMap;
    private static ArrayList<String> inBattleIds;
    private static Random rng = new Random();

    public ChoosePotion(final String id, boolean obtain) {
        super(ID, name(id), portrait(id), 0, CARD_STRINGS.DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
        this.potionId = id;
        this.obtain = obtain;
        rawDescription = (obtain ? CARD_STRINGS.EXTENDED_DESCRIPTION[1] : CARD_STRINGS.EXTENDED_DESCRIPTION[0]) + this.name;
        exhaust = true;
        initializeDescription();
    }

    public ChoosePotion(final String id) {
        this(id, false);
    }

    public ChoosePotion() {
        this(getRandomPotionId(), false);
    }

    public static String getRandomPotionId() {
        return inBattleIds.get(MathUtils.random(inBattleIds.size()));
    }

    public static ArrayList<String> getRandomPotionIdList(final int amount) {
        ArrayList<String> ret = new ArrayList<>(inBattleIds);
        Collections.shuffle(ret, rng.random);
        return new ArrayList<>(ret.subList(0, amount));
    }

    public static void initializePotionList(AbstractPlayer.PlayerClass playerClass) {
        ArrayList<String> ids = PotionHelper.getPotions(playerClass, false);
        ids.remove(RegenPotion.POTION_ID);
        ids.remove(BloodPotion.POTION_ID);
        ids.remove(FruitJuice.POTION_ID);
        ids.remove(FairyPotion.POTION_ID);
        ids.remove(EntropicBrew.POTION_ID);
        ids.remove(SplittingPotion.POTION_ID);

        inBattleIds = new ArrayList<>(ids);
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
        } else {
            this.addToBot(new UseTempPotionAction(p, AbstractDungeon.getRandomMonster()));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChoosePotion(potionId, obtain);
    }

    public static AbstractPotion fromId(final String id) {
        return PotionHelper.getPotion(id);
    }

    public static String name(final String id) {
        if (id == null) {
            return "Random Potion";
        }
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

        //Tonics
        imageMap.put(BlockTonic.ID, "red/skill/sentinel");
        imageMap.put(EnergyTonic.ID, "blue/skill/double_energy");
        imageMap.put(ExplosiveTonic.ID, "colorless/skill/the_bomb");
        imageMap.put(FearTonic.ID, "green/skill/terror");
        imageMap.put(FireTonic.ID, "red/attack/immolate");
        imageMap.put(FlexTonic.ID, "red/skill/flex");
        imageMap.put(SpeedTonic.ID, "green/skill/backflip");
        imageMap.put(SwiftTonic.ID, "blue/skill/skim");
        imageMap.put(WeakTonic.ID, "red/skill/intimidate");

        //Potionbrewer Potions
        imageMap.put(AcidPotion.POTION_ID, "blue/attack/melter");
        imageMap.put(CleansingPotion.POTION_ID, "colorless/skill/deep_breath");
        imageMap.put(DiscountPotion.POTION_ID, "colorless/skill/madness");
        imageMap.put(EndurancePotion.POTION_ID, "red/skill/impervious");
        imageMap.put(FreezingPotion.POTION_ID, "blue/skill/glacier");
        imageMap.put(HastePotion.POTION_ID, "blue/skill/turbo");
        imageMap.put(InfectionPotion.POTION_ID, "green/skill/bouncing_flask");
        imageMap.put(MidasPotion.POTION_ID, "colorless/attack/hand_of_greed");
        imageMap.put(NoxiousPotion.POTION_ID, "green/attack/choke");
        imageMap.put(QuicksilverPotion.POTION_ID, "blue/power/buffer");
        imageMap.put(SplittingPotion.POTION_ID, "red/attack/sever_soul");
        imageMap.put(ToxicPotion.POTION_ID, "status/slimed");

        // built-in potions
        imageMap.put(Ambrosia.POTION_ID, "purple/power/deva_form");
        imageMap.put(AncientPotion.POTION_ID, "colorless/skill/panacea");
        imageMap.put(AttackPotion.POTION_ID, "red/skill/infernal_blade");
        imageMap.put(BlockPotion.POTION_ID, "red/skill/defend");
        imageMap.put(BloodPotion.POTION_ID, "colorless/skill/violence");
        imageMap.put(BottledMiracle.POTION_ID, "colorless/skill/miracle");
        imageMap.put(ColorlessPotion.POTION_ID, "colorless/skill/jack_of_all_trades");
        imageMap.put(CultistPotion.POTION_ID, "red/power/demon_form");
        imageMap.put(CunningPotion.POTION_ID, "green/skill/cloak_and_dagger");
        imageMap.put(DexterityPotion.POTION_ID, "green/skill/blur");
        imageMap.put(DistilledChaosPotion.POTION_ID, "red/skill/havoc");
        imageMap.put(DuplicationPotion.POTION_ID, "green/skill/doppelganger");
        imageMap.put(Elixir.POTION_ID, "colorless/skill/purity");
        imageMap.put(EnergyPotion.POTION_ID, "blue/skill/double_energy");
        imageMap.put(EntropicBrew.POTION_ID, "blue/skill/chaos");
        imageMap.put(EssenceOfDarkness.POTION_ID, "blue/skill/darkness");
        imageMap.put(EssenceOfSteel.POTION_ID, "blue/skill/auto_shields");
        imageMap.put(ExplosivePotion.POTION_ID, "colorless/skill/the_bomb");
        imageMap.put(FairyPotion.POTION_ID, "colorless/skill/metamorphosis");
        imageMap.put(FearPotion.POTION_ID, "green/skill/terror");
        imageMap.put(FirePotion.POTION_ID, "red/attack/immolate");
        imageMap.put(FocusPotion.POTION_ID, "blue/power/defragment");
        imageMap.put(FruitJuice.POTION_ID, "red/attack/feed");
        imageMap.put(GamblersBrew.POTION_ID, "green/skill/calculated_gamble");
        imageMap.put(GhostInAJar.POTION_ID, "green/power/wraith_form");
        imageMap.put(HeartOfIron.POTION_ID, "red/power/metallicize");
        imageMap.put(LiquidBronze.POTION_ID, "green/skill/blade_dance");
        imageMap.put(LiquidMemories.POTION_ID, "blue/skill/hologram");
        imageMap.put(PoisonPotion.POTION_ID, "green/skill/deadly_poison");
        imageMap.put(PotionOfCapacity.POTION_ID, "blue/power/capacitor");
        imageMap.put(PowerPotion.POTION_ID, "blue/skill/white_noise");
        imageMap.put(RegenPotion.POTION_ID, "colorless/skill/bandage_up");
        imageMap.put(SkillPotion.POTION_ID, "green/skill/distraction");
        imageMap.put(SmokeBomb.POTION_ID, "green/skill/crippling_poison");
        imageMap.put(SneckoOil.POTION_ID, "colorless/skill/blind");
        imageMap.put(SpeedPotion.POTION_ID, "green/skill/backflip");
        imageMap.put(StancePotion.POTION_ID, "purple/attack/tantrum");
        imageMap.put(SteroidPotion.POTION_ID, "red/skill/flex");
        imageMap.put(StrengthPotion.POTION_ID, "red/skill/limit_break");
        imageMap.put(SwiftPotion.POTION_ID, "blue/skill/skim");
        imageMap.put(WeakenPotion.POTION_ID, "red/skill/intimidate");
    }
}
