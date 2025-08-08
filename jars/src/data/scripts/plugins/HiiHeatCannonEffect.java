package data.scripts.plugins;

import java.awt.Color;

import org.lwjgl.util.vector.Vector2f;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.OnHitEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;

import org.lazywizard.lazylib.CollisionUtils;
import org.lazywizard.lazylib.MathUtils;

public class HiiHeatCannonEffect implements OnHitEffectPlugin {

	public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target,
					  Vector2f point, boolean shieldHit, CombatEngineAPI engine) {
		if (target instanceof ShipAPI) {
			float emp = projectile.getEmpAmount() * 0.1f;
			float dam = projectile.getDamageAmount() * 0.2f;
			WeaponAPI weapon = projectile.getWeapon();
			DamageType damType = weapon.getDamageType();
			int heatMe = (int) Math.max(6f,dam / 150f);
			
			Vector2f targLoc = target.getLocation();
			for(int i = 0; i < heatMe; i++)
			{
				//Gets a point far, far away and uses it as our ray-test point.  Long distances work better than shorter ones.
				Vector2f cloneLoc = MathUtils.getRandomPointOnCircumference(targLoc, 1000000f);
						
				//Try to get a valid collision point between our explosion's point source and the Entity.
				Vector2f colPoint = CollisionUtils.getCollisionPoint(cloneLoc, targLoc, target);
				//If we can't get a good collision point, use the center of the target Entity.  This is potentially a balance issue (hits all going to one armor cell are pretty OP lol), but this case mainly covers little teeny drones and suchlike that should be registering hits from giant explosions nearby, but often don't, for whatever reason.  Bigger things rarely fail, so it usually works out.
				if(colPoint == null) colPoint = targLoc;
				if(colPoint != null)//Must check this, getCollisionPoint returns null fairly frequently and that's a wrap
				{
					engine.applyDamage(
						target, //enemy Entity
						colPoint, //Our 2D vector to the exact world-position of the collision
						dam, //DPS modified by the damage multiplier
						damType, //Using the damage type here, so that Kinetic / Explosive / Fragmentation AOE works.
						emp, //EMP (if any)
						false, //Does not bypass shields.
						false, //Does not do Soft Flux damage (unless you want it to for some strange reason)
						projectile.getSource()  //Who owns this projectile?
					);
				}
			}				   
		}
	}
}
