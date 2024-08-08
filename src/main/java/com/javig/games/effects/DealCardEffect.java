package com.javig.games.effects;


import com.dainws.games.crm.domain.exception.EffectException;
import com.dainws.games.crm.domain.models.dealer.DealStrategy;
import com.dainws.games.crm.domain.models.dealer.Dealer;
import com.dainws.games.crm.domain.models.effect.Effect;
import com.dainws.games.crm.domain.models.effect.EffectContext;
import com.dainws.games.crm.domain.models.player.Player;


public abstract class DealCardEffect implements Effect {
	
	private Dealer dealer;
	private DealStrategy strategy;
	
	protected DealCardEffect(Dealer dealer, DealStrategy strategy) {
		this.dealer = dealer;
		this.strategy = strategy;
	}

	@Override
	public void perform(EffectContext context) throws EffectException {
		Player target = context.getTargetPlayer();
		dealer.dealCardsToPlayer(context.getGame(), target, strategy);
	}

}
