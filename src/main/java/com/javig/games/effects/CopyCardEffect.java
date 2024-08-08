package com.javig.games.effects;

import com.dainws.games.crm.domain.exception.EffectException;
import com.dainws.games.crm.domain.models.board.Coordinate;
import com.dainws.games.crm.domain.models.board.Zone;
import com.dainws.games.crm.domain.models.card.Card;
import com.dainws.games.crm.domain.models.card.CardType;
import com.dainws.games.crm.domain.models.card.Warrior;
import com.dainws.games.crm.domain.models.effect.Effect;
import com.dainws.games.crm.domain.models.effect.EffectContext;
import com.dainws.games.crm.domain.models.effect.EffectId;

public class CopyCardEffect implements Effect {

	@Override
	public EffectId getId() {
		return new EffectId(50);
	}

	@Override
	public void perform(EffectContext context) throws EffectException {
		validate(context);
		
		Warrior cloned = cloneCard((Warrior) context.getTargetCard());
		Coordinate emptyCoordinate = findFirstEmptyCoordinates(context.getSourceZone());
		context.getSourceZone().putCombatant(emptyCoordinate, cloned);
	}

	private void validate(EffectContext context) throws EffectException {
		Card target = context.getTargetCard();
		
		if(!target.isType(CardType.WARRIOR)) {
			throw new EffectException("","The clone card must be a warrior, dumbass");
		}
	}

	private Warrior cloneCard(Warrior targetWarrior) throws EffectException {
		return Warrior.warriorBuilder(targetWarrior.getRarity())
				.withArmor(targetWarrior.getArmor())
				.withDamage(targetWarrior.getDamage())
				.withHealth(targetWarrior.getHealth())
				.withEquipment(targetWarrior.getEquipment())
				.withDescription(targetWarrior.getDescription().getValue())
				.withCode(targetWarrior.getCode().getCode())
				.withName(targetWarrior.getName().getValue())
				.build();
				
	}
	
	private Coordinate findFirstEmptyCoordinates(Zone zone) {
		Coordinate emptyCoordinate = null;
		for(int i = 0; i < zone.getVerticalDimension() && emptyCoordinate == null; i++) {
			for(int j = 0; j < zone.getHorizontalDimension() && emptyCoordinate == null; j++) {
				Coordinate coordinate = new Coordinate(j, i);
				if(!zone.hasCombatant(coordinate)) {
					emptyCoordinate = coordinate;
				}
			}
		}
		return emptyCoordinate;
	}
}