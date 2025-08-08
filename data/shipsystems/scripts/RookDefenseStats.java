package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;

public class RookDefenseStats extends BaseShipSystemScript {

	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		stats.getShieldTurnRateMult().modifyMult(id, 2f);
		stats.getShieldUnfoldRateMult().modifyMult(id, 4f);
		
		stats.getShieldDamageTakenMult().modifyMult(id, 1f - .4f * effectLevel);
		stats.getArmorDamageTakenMult().modifyMult(id, 1f - .4f * effectLevel);
		stats.getHullDamageTakenMult().modifyMult(id, 1f - .4f * effectLevel);
		
		//stats.getMissileRoFMult().modifyMult(id, 1f - .33f * effectLevel);
		stats.getBallisticRoFMult().modifyMult(id, 1f - .5f * effectLevel);
		stats.getEnergyRoFMult().modifyMult(id, 1f - .5f * effectLevel);
		
		stats.getFluxDissipation().modifyMult(id, 1f + 0.75f * effectLevel);
		stats.getHardFluxDissipationFraction().modifyFlat(id, 0.5f);
		//System.out.println("level: " + effectLevel);
	}
	
	public void unapply(MutableShipStatsAPI stats, String id) {
		stats.getShieldTurnRateMult().unmodify(id);
		stats.getShieldUnfoldRateMult().unmodify(id);
		
		stats.getShieldDamageTakenMult().unmodify(id);
		stats.getArmorDamageTakenMult().unmodify(id);
		stats.getHullDamageTakenMult().unmodify(id);
		
		//stats.getMissileRoFMult().unmodify(id);
		stats.getBallisticRoFMult().unmodify(id);
		stats.getEnergyRoFMult().unmodify(id);
		
		stats.getFluxDissipation().unmodify(id);
		stats.getHardFluxDissipationFraction().unmodify(id);
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if (index == 0) {
			return new StatusData("Oasis Respite System Active", false);
		}
//		else if (index == 1) {
//			return new StatusData("shield upkeep reduced to 0", false);
//		} else if (index == 2) {
//			return new StatusData("shield upkeep reduced to 0", false);
//		}
		return null;
	}
}
