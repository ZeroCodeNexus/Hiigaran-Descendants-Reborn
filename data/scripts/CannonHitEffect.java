package data.scripts;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import com.fs.starfarer.api.combat.listeners.ApplyDamageResultAPI;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.util.vector.Vector2f;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.OnHitEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import org.magiclib.util.MagicLensFlare;
import org.magiclib.util.MagicRender;


public class CannonHitEffect implements OnHitEffectPlugin {

	
	public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target,
					  Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {
		
		Random random = new Random();

		float emp = projectile.getEmpAmount() * 0.28f;
		float dam = projectile.getDamageAmount() * 0.28f;
		
		float thickness = 48f;
		float length = 300f;
		
		MagicLensFlare.createSharpFlare(engine, projectile.getSource(), point, thickness, length, 0f, new Color(0,0,200,255),  new Color(210,255,255,255));
		
		MagicRender.battlespace(Global.getSettings().getSprite("fx", "hii_fx_blast_center"), point, new Vector2f(0,0), new Vector2f(500f,500f), new Vector2f(-150f,-150f), random.nextFloat() * 360f, random.nextFloat() * 50f, new Color(210,255,255,255), true, 0.15f, 0.35f, 1f);
		MagicRender.battlespace(Global.getSettings().getSprite("fx", "hii_fx_blast_center"), point, new Vector2f(0,0), new Vector2f(400f,400f), new Vector2f(150f,150f), random.nextFloat() * 360f, random.nextFloat() * 50f, new Color(0,0,200,255), true, 0.1f, 0.8f, 2f);
		MagicRender.battlespace(Global.getSettings().getSprite("fx", "hii_fx_blast_ring"), point, new Vector2f(0,0), new Vector2f(200f,200f), new Vector2f(2000f,2000f), random.nextFloat() * 360f, 0f, new Color(150,150,255,150), false, 0.05f, 0.08f, 0.2f);
		MagicRender.battlespace(Global.getSettings().getSprite("fx", "hii_fx_blast_ring"), point, new Vector2f(0,0), new Vector2f(200f,200f), new Vector2f(1000f,1000f), random.nextFloat() * 360f, 0f, new Color(0,0,200,255), true, 0.05f, 0.15f, 0.45f);
		MagicRender.battlespace(Global.getSettings().getSprite("fx", "hii_fx_blast_ring_glow"), point, new Vector2f(0,0), new Vector2f(300f,300f), new Vector2f(3500f,3500f), random.nextFloat() * 360f, 0f, new Color(90,90,250,255), true, 0.1f, 0.1f, 0.1f);
		MagicRender.battlespace(Global.getSettings().getSprite("fx", "hii_fx_blast_ring_glow"), point, new Vector2f(0,0), new Vector2f(300f,300f), new Vector2f(800f,800f), random.nextFloat() * 360f, 0f, new Color(0,0,200,255), true, 0.25f, 0.3f, 0.9f);
		

		List<ShipAPI> ships = CombatUtils.getShipsWithinRange(point, 475f);

		
		for (ShipAPI ship : ships) {
			if (ship != projectile.getSource() && ship.getHullSize() != HullSize.FIGHTER) {
					
				float max = random.nextFloat() * 4f;
				if (MathUtils.isWithinRange(ship, point, 150f)) max += 1;
				if (MathUtils.isWithinRange(ship, point, 300f)) max += 1;
				max = 1f + Math.round(max * 0.5f);
				
				for (float i = 0; i<=max; i++) {					
					engine.spawnEmpArcPierceShields(
						   projectile.getSource(), point, ship, ship,
						   DamageType.ENERGY, 
						   dam,
						   emp, // emp 
						   100000f, // max range 
						   "tachyon_lance_emp_impact",
						   20f, // thickness
						   new Color(0,0,200,255),
						   new Color(210,255,255,255)
						   );
				}
				for (float i = 0; i<=max; i++) {
					engine.spawnEmpArc( 
						   projectile.getSource(), point, ship, ship,
						   DamageType.ENERGY, 
						   dam, // damage
						   emp, // emp 
						   100000f, // max range 
						   "tachyon_lance_emp_impact",
						   20f, // thickness
						   new Color(0,0,200,255),
						   new Color(210,255,255,255)
							);
				}
			}	
		}
	}

}