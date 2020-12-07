package potionbrewer.optioncards;

import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.relics.Sozu;
import potionbrewer.PotionbrewerMod;
import potionbrewer.actions.UseTempPotionAction;
import potionbrewer.potions.*;
import potionbrewer.potions.tonics.*;
import potionbrewer.relics.SalesContract;

import java.util.HashMap;
import java.util.Map;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ChoosePotion extends AbstractCard implements CustomSavable<String> {
    public static String ID = PotionbrewerMod.makeID(ChoosePotion.class.getSimpleName());
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);

    public String potionId;
    public boolean obtain;
    public static Map<String, String> imageMap;

    public ChoosePotion(final String id, boolean obtain) {
        super(ID, name(id), portrait(id), 0, CARD_STRINGS.DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
        this.potionId = id;
        this.obtain = obtain;
        rawDescription = (obtain ? CARD_STRINGS.EXTENDED_DESCRIPTION[1] : CARD_STRINGS.EXTENDED_DESCRIPTION[0])
                + (isVowel(this.name.charAt(0)) ? CARD_STRINGS.EXTENDED_DESCRIPTION[3] : CARD_STRINGS.EXTENDED_DESCRIPTION[2])
                + this.name
                + " NL "
                + potionDescription(id);
        exhaust = true;
        initializeDescription();
        this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[7];
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

    public ChoosePotion(final String id) {
        this(id, false);
    }

    public ChoosePotion() {
        this(
                PotionbrewerMod.potionLibrary == null ?
                        FirePotion.POTION_ID :
                        PotionbrewerMod.potionLibrary.getRandomPotionId(),
                false
        );
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean fromSuper = super.canUse(p, m);
        AbstractPotion potion = fromId(potionId);
        if (potion == null) {
            potion = new FireTonic();
        }
        return fromSuper && potion.canUse();
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
            if (AbstractDungeon.player.hasRelic(SalesContract.ID)) {
                AbstractDungeon.player.getRelic(SalesContract.ID).flash();
                AbstractDungeon.player.gainGold(SalesContract.GOLD_AMOUNT);
                CardCrawlGame.sound.play("GOLD_GAIN");
            } else if (AbstractDungeon.player.hasRelic(Sozu.ID)) {
                AbstractDungeon.player.getRelic(Sozu.ID).flash();
            } else {
                AbstractDungeon.player.obtainPotion(p);
            }
        } else {
            if (p.canUse()) {
                addToBot(new UseTempPotionAction(p, AbstractDungeon.getRandomMonster()));
            }
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
            return CARD_STRINGS.EXTENDED_DESCRIPTION[4];
        } else if (id.equals("RAND_TONIC")) {
            return CARD_STRINGS.EXTENDED_DESCRIPTION[5];
        }
        AbstractPotion p = fromId(id);
        if (p == null) {
            return CARD_STRINGS.EXTENDED_DESCRIPTION[6];
        } else {
            return p.name;
        }
    }

    private static final String rCode = Settings.RED_TEXT_COLOR.toString();
    private static final String gCode = Settings.GREEN_TEXT_COLOR.toString();
    private static final String bCode = Settings.BLUE_TEXT_COLOR.toString();
    private static final String yCode = Settings.GOLD_COLOR.toString();

    private static String fixColors(final String orig) {
        return orig
                .replaceAll("#r(\\w+)", "[#" + rCode + "]$1[]")
                .replaceAll("#g(\\w+)", "[#" + gCode + "]$1[]")
                .replaceAll("#b(\\w+)", "[#" + bCode + "]$1[]")
                .replaceAll("#y(\\w+)", "[#" + yCode + "]$1[]")
                ;
    }

    public static String potionDescription(final String id) {
        if (id == null) {
            return "";
        } else if (id.equals("RAND_TONIC")) {
            return "";
        }
        AbstractPotion p = fromId(id);
        if (p == null) {
            return "";
        } else {
            return "(" + fixColors(p.description) + ")";
        }
    }

    public static String portrait(final String id) {
        return imageMap.getOrDefault(id, "green/skill/alchemize");
    }

    @Override
    public String onSave() {
        return potionId;
    }

    @Override
    public void onLoad(String s) {
        potionId = s;
        this.name = this.originalName = name(potionId);
        this.assetUrl = portrait(s);
        TextureAtlas cardAtlas = new TextureAtlas(Gdx.files.internal("cards/cards.atlas"));
        TextureAtlas oldCardAtlas = new TextureAtlas(Gdx.files.internal("oldCards/cards.atlas"));
        this.portrait = cardAtlas.findRegion(this.assetUrl);
        this.jokePortrait = oldCardAtlas.findRegion(this.assetUrl);
        if (this.portrait == null) {
            if (this.jokePortrait != null) {
                this.portrait = this.jokePortrait;
            } else {
                this.portrait = cardAtlas.findRegion("status/beta");
            }
        }
        rawDescription = (obtain ? CARD_STRINGS.EXTENDED_DESCRIPTION[1] : CARD_STRINGS.EXTENDED_DESCRIPTION[0])
                + (isVowel(this.name.charAt(0)) ? CARD_STRINGS.EXTENDED_DESCRIPTION[3] : CARD_STRINGS.EXTENDED_DESCRIPTION[2])
                + this.name;
        exhaust = true;
        initializeDescription();
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
        imageMap.put(BarricadePotion.POTION_ID, "red/skill/entrench");
        imageMap.put(BlacksmithPotion.POTION_ID, "colorless/skill/forethought");
        imageMap.put(BoundlessPotion.POTION_ID, "red/skill/limit_break");
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
        imageMap.put(StunPotion.POTION_ID, "status/dazed");
        imageMap.put(ToxicPotion.POTION_ID, "status/slimed");

        // built-in potions
        imageMap.put(Ambrosia.POTION_ID, "purple/power/deva_form");
        imageMap.put(AncientPotion.POTION_ID, "colorless/skill/panacea");
        imageMap.put(AttackPotion.POTION_ID, "red/skill/infernal_blade");
        imageMap.put(BlessingOfTheForge.POTION_ID, "red/skill/armaments");
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
        imageMap.put(StrengthPotion.POTION_ID, "red/power/demon_form");
        imageMap.put(SwiftPotion.POTION_ID, "blue/skill/skim");
        imageMap.put(WeakenPotion.POTION_ID, "red/skill/intimidate");
    }
}
