package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.impl.campaign.ids.Stats;

public class ExtendedManufactory extends BaseHullMod {

	public static final float RATE_DECREASE_MODIFIER = 66f;
	public static final float RATE_INCREASE_MODIFIER = 25f;
	public static final float REFIT_BONUS = 25f;
	public static final float STAT_BONUS = 15f;
	

	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
	
		stats.getDynamic().getStat(Stats.REPLACEMENT_RATE_DECREASE_MULT).modifyMult(id, 1f - RATE_DECREASE_MODIFIER / 100f);
		stats.getDynamic().getStat(Stats.REPLACEMENT_RATE_INCREASE_MULT).modifyPercent(id, RATE_INCREASE_MODIFIER);
		stats.getFighterRefitTimeMult().modifyPercent(id, -REFIT_BONUS);
		
	}
	
	public void applyEffectsToFighterSpawnedByShip(ShipAPI fighter, ShipAPI ship, String id) {
	
		MutableShipStatsAPI stats = fighter.getMutableStats();
		
		stats.getMaxSpeed().modifyMult(id, 1f + STAT_BONUS * 0.01f);
		
		stats.getArmorDamageTakenMult().modifyPercent(id, 1f + STAT_BONUS * 0.01f);
		stats.getShieldDamageTakenMult().modifyPercent(id, 1f + STAT_BONUS * 0.01f);
		stats.getHullDamageTakenMult().modifyPercent(id, 1f + STAT_BONUS * 0.01f);
		
	}
		
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) RATE_DECREASE_MODIFIER + "%";
		if (index == 1) return "" + (int) REFIT_BONUS + "%";
		if (index == 2) return "" + (int) STAT_BONUS + "%";
		if (index == 3) return "" + (int) STAT_BONUS + "%";
		return null;
	}
	

}




