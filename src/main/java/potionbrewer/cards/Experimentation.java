package potionbrewer.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.defect.RedoAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import potionbrewer.PotionbrewerMod;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.orbs.Reagent;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static potionbrewer.PotionbrewerMod.makeCardPath;

public class Experimentation extends CustomCard {

    // TEXT DECLARATION

    public static final String ID = PotionbrewerMod.makeID(Experimentation.class.getSimpleName());
    public static final String IMG = makeCardPath("Experimentation.png");
    public static CardStrings CARD_STRINGS = languagePack.getCardStrings(ID);
    // Must have an image with the same NAME as the card in your image folder!

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Potionbrewer.Enums.COLOR_CYAN;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    // /STAT DECLARATION/


    public Experimentation() {
        super(ID, CARD_STRINGS.NAME, IMG, COST, CARD_STRINGS.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        isEthereal = true;
        cardsToPreview = new Prototype();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RedoAction());
        ArrayList<Reagent> collectedReagents = p.orbs.stream()
                .filter(o -> o instanceof Reagent)
                .map(o -> (Reagent) o)
                .collect(Collectors.toCollection(ArrayList::new));
        if (collectedReagents.size() >= 3) {
            Prototype card = new Prototype(collectedReagents.get(0), collectedReagents.get(1), collectedReagents.get(2));
            card.purgeOnUse = true;
            this.addToBot(new MakeTempCardInDrawPileAction(card, 1, true, true));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}