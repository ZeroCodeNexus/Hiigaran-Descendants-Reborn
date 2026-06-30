package data.scripts.world;

import java.awt.Color;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;

import java.util.ArrayList;
import java.util.Arrays;


public class HiiPolarisGen
{
    public void generate(SectorAPI sector)
    {
        
        StarSystemAPI system = sector.createStarSystem("Polaris");
        LocationAPI hyper = Global.getSector().getHyperspace();
        system.setBackgroundTextureFilename("graphics/HD/backgrounds/hii_nebula.jpg");
        system.getLocation().set(-29000, -35500);
        
        sector.setRespawnLocation(system);
        sector.getRespawnCoordinates().set(-2500.0F, -3500.0F);
        
        PlanetAPI star = system.initStar("polaris", "star_blue_giant", 600f, 600f);
        star.setCustomDescriptionId("hii_star");
        
        PlanetAPI hii1 = system.addPlanet("newhiigara", star, "New Hiigara", "arid", 235, 175, 8000, 250);
        hii1.setCustomDescriptionId("newhiigara");
        //hii1.setInteractionImage("HD/illustrations", "hii_higara");
        
        PlanetAPI hii2 = system.addPlanet("jakuul", star, "Jakuul", "gas_giant", 275, 255, 13000, 350);
        hii2.setCustomDescriptionId("hii_gas_giant");
        
        PlanetAPI hii3 = system.addPlanet("koya", hii2, "Koya", "barren3", 135, 50, 500, 30);
        hii3.setCustomDescriptionId("hii_moon");
        hii3.setInteractionImage("illustrations", "vacuum_colony");
        
        PlanetAPI hii4 = system.addPlanet("daiamid", star, "Daiamid", "water", 75, 150, 6000, 150);
        hii4.setCustomDescriptionId("hii_ocean");
        
        PlanetAPI hii5 = system.addPlanet("maal", star, "Maal", "lava", 0, 125, 3000, 100);
        hii5.setCustomDescriptionId("hii_volcanic");
        hii5.setInteractionImage("illustrations", "industrial_megafacility");
        
        PlanetAPI hii6 = system.addPlanet("kohntala",star, "Kohntala", "frozen1", 200, 200, 16000, 350);
        hii6.setCustomDescriptionId("hii_frozen");
        //hii6.setInteractionImage("HD/illustrations", "hii_pirate_ice");
        
        SectorEntityToken relay = system.addCustomEntity("polaris_relay", "New Hiigara Relay", "comm_relay", "hiigaran_descendants");
        relay.setCircularOrbit(system.getEntityById("newhiigara"), 90, 850, 275);

        SectorEntityToken jmp1 = system.getEntityById("newhiigara");        
        JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("hiigara_gate", "Hiigara Gate");
        OrbitAPI orbit = Global.getFactory().createCircularOrbit(hii1, 0, 800, 30);
        jumpPoint.setOrbit(orbit);
        jumpPoint.setRelatedPlanet(hii1);
        jumpPoint.setStandardWormholeToHyperspaceVisual();
        system.addEntity(jumpPoint); 
        
        JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint("junk_gate", "Junk Gate");
        OrbitAPI orbit1 = Global.getFactory().createCircularOrbit(hii5, 0, 800, 30);
        jumpPoint1.setOrbit(orbit1);
        jumpPoint1.setRelatedPlanet(hii1);
        jumpPoint1.setStandardWormholeToHyperspaceVisual();
        system.addEntity(jumpPoint1); 
/*
        SectorEntityToken NewHiigaraStation = system.addOrbitalStation(hii1, 45, 400, 50, "'Angel Moon' Station", "hiigaran_descendants");
        SectorEntityToken NewHiigaraSecurityStation = system.addOrbitalStation(hii5, 45, 300, 50, "Security Station", "hiigaran_descendants");
        SectorEntityToken NewHiigaraGraveyard = system.addOrbitalStation(hii1, 245, 600, 50, "Ship Graveyard", "neutral");
*/
        SectorEntityToken NewHiigaraStation = system.addCustomEntity("new_hiigara_station", "'Angel Moon' Station", "new_hiigara_type", "hiigaran_descendants");
        NewHiigaraStation.setCircularOrbitPointingDown(system.getEntityById("newhiigara"), 45, 400, 50);
        
        SectorEntityToken NewHiigaraSecurityStation = system.addCustomEntity("hiigara_security_station", "'Guard House' Station", "hiigara_security_type", "hiigaran_descendants");
        NewHiigaraSecurityStation.setCircularOrbitPointingDown(system.getEntityById("koya"), 45, 300, 50);
                
        SectorEntityToken NewHiigaraGraveyard = system.addCustomEntity("hiigara_graveyard", "Junkyard Station", "new_hiigara_type", "neutral");
        NewHiigaraGraveyard.setCircularOrbitPointingDown(system.getEntityById("maal"), 45, 600, 50);
        
        //Asteroids
        system.addAsteroidBelt(hii2, 25, 800, 128, 40, 80);
        system.addAsteroidBelt(star, 800, 9000, 200, 40, 80);
        system.addAsteroidBelt(star, 800, 5000, 200, 40, 80);

        system.addRingBand(hii1, "misc", "hii_rings", 256f, 3, Color.white, 256f, 250, 40f);
        system.addRingBand(hii1, "misc", "hii_rings", 256f, 2, Color.white, 256f, 250, 20f);
        system.addRingBand(hii1, "misc", "hii_rings", 256f, 1, Color.white, 256f, 250, 10f);
        system.addRingBand(hii5, "misc", "hii_rings", 256f, 0, Color.white, 256f, 190, 10f);
        system.addRingBand(hii4, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 400, 30f);
        system.addRingBand(hii4, "misc", "rings_dust0", 256f, 3, Color.white, 256f, 400, 40f);
        system.addRingBand(hii4, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 600, 40f);
        system.addRingBand(hii6, "misc", "rings_dust0", 256f, 1, Color.white, 256f, 400, 40f);
        system.addRingBand(hii6, "misc", "rings_dust0", 256f, 0, Color.white, 256f, 600, 60f);
        system.addRingBand(hii6, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 1000, 80f);
        
        
        //initStationCargo(NewHiigaraStation);
        //initSecurityStationCargo(NewHiigaraSecurityStation);

        system.autogenerateHyperspaceJumpPoints(true, true);
       // sector.addScript(new EconomyFleetManager());
        
        //SharedData.getData().getPersonBountyEventData().addParticipatingFaction("hiigaran_descendants");
        
         MarketAPI newhiigaramarket = addMarketplace.addMarketplace("hiigaran_descendants", hii1,
                                      new ArrayList<SectorEntityToken>(Arrays.asList(NewHiigaraStation)),
                                      "New Hiigara",
                                      6,
                                      new ArrayList<String>(Arrays.asList(Conditions.POPULATION_6,
                                                                    Conditions.ORE_RICH, Conditions.RARE_ORE_RICH, Conditions.HABITABLE, Conditions.HOT, Conditions.ARID)),
									new ArrayList<String>(java.util.Arrays.asList(
                                                            Industries.POPULATION,
															Industries.ORBITALWORKS,
                                                            Industries.LIGHTINDUSTRY,
                                                            Industries.MEGAPORT,
                                                            Industries.HEAVYBATTERIES,
                                                            Industries.HIGHCOMMAND,
                                                            Industries.STARFORTRESS_HIGH,
                                                            Industries.MINING)),
                                      new ArrayList<String>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, Submarkets.SUBMARKET_BLACK, Submarkets.GENERIC_MILITARY,
                                                                    Submarkets.SUBMARKET_OPEN)),
                                      0.3f
        );
         newhiigaramarket.addIndustry(Industries.ORBITALWORKS, new ArrayList<>(Arrays.asList(Items.PRISTINE_NANOFORGE)));
         addMarketplace.addMarketplace("hiigaran_descendants", hii3,
                                      new java.util.ArrayList<SectorEntityToken>(Arrays.asList(NewHiigaraSecurityStation)),
                                      "Koya",
                                      4,
                                      new ArrayList<String>(Arrays.asList(Conditions.POPULATION_4, Conditions.ORE_MODERATE, Conditions.RARE_ORE_SPARSE,
																	Conditions.VOLATILES_DIFFUSE, Conditions.NO_ATMOSPHERE, Conditions.VERY_COLD)),
									new ArrayList<String>(Arrays.asList(
                                                            Industries.POPULATION,
															Industries.FUELPROD,
                                                            Industries.LIGHTINDUSTRY,
                                                            Industries.MEGAPORT,
                                                            Industries.HEAVYBATTERIES,
                                                            Industries.MILITARYBASE,
                                                            Industries.STARFORTRESS_HIGH,
                                                            Industries.REFINING)),
                                      new ArrayList<String>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, Submarkets.SUBMARKET_BLACK, Submarkets.GENERIC_MILITARY,
                                                                    Submarkets.SUBMARKET_OPEN)),
                                      0.3f
        );
         addMarketplace.addMarketplace("hiigaran_descendants", hii4,
                                      null,
                                      "Daiamid",
                                      3,
                                      new ArrayList<String>(Arrays.asList(Conditions.POPULATION_3,
                                                            Conditions.VOLTURNIAN_LOBSTER_PENS, Conditions.RARE_ORE_SPARSE, Conditions.ORE_SPARSE, Conditions.ORGANICS_ABUNDANT, Conditions.WATER, Conditions.HABITABLE)),
									new ArrayList<String>(Arrays.asList(
                                                            Industries.POPULATION,
                                                            Industries.SPACEPORT,
                                                            Industries.HEAVYBATTERIES,
                                                            Industries.ORBITALSTATION_HIGH,
                                                            Industries.AQUACULTURE)),
                                      new ArrayList<String>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, Submarkets.SUBMARKET_BLACK, Submarkets.GENERIC_MILITARY,
                                                                    Submarkets.SUBMARKET_OPEN)),
                                      0.3f
        );
        

        addMarketplace.addMarketplace(Factions.INDEPENDENT, hii5,
                                      new ArrayList<SectorEntityToken>(Arrays.asList(NewHiigaraGraveyard)),
                                      "Maal",
                                      3,
                                      new ArrayList<String>(
                                              Arrays.asList(Conditions.FREE_PORT, Conditions.POPULATION_4, Conditions.RARE_ORE_ULTRARICH, Conditions.ORE_RICH, Conditions.THIN_ATMOSPHERE, Conditions.VERY_HOT)),
									new ArrayList<String>(Arrays.asList(
                                                            Industries.POPULATION,
                                                            Industries.SPACEPORT,
                                                            Industries.GROUNDDEFENSES,
                                                            Industries.ORBITALSTATION_HIGH,
                                                            Industries.LIGHTINDUSTRY)),
                                      new ArrayList<String>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, Submarkets.SUBMARKET_BLACK, Submarkets.SUBMARKET_OPEN)),
                                      0.3f
        );

        addMarketplace.addMarketplace(Factions.PIRATES, hii6,
                                      null,
                                      "Kohntala",
                                      4,
                                      new ArrayList<String>(Arrays.asList(Conditions.ICE, Conditions.VOLATILES_ABUNDANT, Conditions.RARE_ORE_ABUNDANT, Conditions.ORE_MODERATE, Conditions.FREE_PORT, Conditions.POPULATION_4, Conditions.VERY_COLD, Conditions.NO_ATMOSPHERE)),
									  new ArrayList<String>(Arrays.asList(
                                                            Industries.POPULATION,
                                                            Industries.SPACEPORT,
                                                            Industries.GROUNDDEFENSES,
                                                            Industries.ORBITALSTATION,
                                                            Industries.MINING)),
                                      new ArrayList<String>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, Submarkets.SUBMARKET_BLACK, Submarkets.SUBMARKET_OPEN)),
                                      0.3f
        );
        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin();

        NebulaEditor editor = new NebulaEditor(plugin);

        // clear a generous area
        editor.clearArc(
                system.getLocation().x,
                system.getLocation().y,
                0,
                1200f, // Radius
                0,
                360
        );
    }

}
