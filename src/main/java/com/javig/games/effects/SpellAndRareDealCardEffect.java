package com.javig.games.effects;

import com.dainws.games.crm.domain.models.card.WarriorRarity;
import com.dainws.games.crm.domain.models.dealer.ComposedStrategy;
import com.dainws.games.crm.domain.models.dealer.Dealer;
import com.dainws.games.crm.domain.models.dealer.SpellStrategy;
import com.dainws.games.crm.domain.models.dealer.WarriorStrategy;
import com.dainws.games.crm.domain.models.effect.EffectId;

public class SpellAndRareDealCardEffect extends DealCardEffect {

	public SpellAndRareDealCardEffect(Dealer dealer) {
		super(dealer, new ComposedStrategy(new SpellStrategy(), new WarriorStrategy(WarriorRarity.RARE)));
	}

	@Override
	public EffectId getId() {
		return new EffectId(1);
	}

}
