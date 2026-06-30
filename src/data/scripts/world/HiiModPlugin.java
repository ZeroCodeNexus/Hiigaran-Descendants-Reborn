package data.scripts.world;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import exerelin.campaign.SectorManager;
import org.dark.shaders.light.LightData;
import org.dark.shaders.util.ShaderLib;


public class HiiModPlugin extends BaseModPlugin
{
	public static boolean isExerelin = false;
	
     @Override
    public void onApplicationLoad() {
		
		isExerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
        boolean hasGraphicsLib = Global.getSettings().getModManager().isModEnabled("shaderLib");
        if (hasGraphicsLib) {
			ShaderLib.init();
			LightData.readLightDataCSV("data/lights/hii_light_data.csv");
			//org.dark.shaders.util.TextureData.readTextureDataCSV("data/lights/hii_texture_data.csv");
		}
		if (!hasGraphicsLib) {
            throw new RuntimeException("Hiigaran Descendants requires GraphicsLib!" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=10982");


        }

    }
    
	private static void initHiigaranDescendants()
	{
			if (isExerelin && !SectorManager.getManager().isCorvusMode()) {
				return;
			}

			new HiigaraGen().generate(Global.getSector());

	}

	@Override
	public void onNewGame()
	{
		initHiigaranDescendants();
        initFactionRelationships(Global.getSector());

	}


    public static void initFactionRelationships(SectorAPI sector) {
        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI independent = sector.getFaction(Factions.INDEPENDENT);
        FactionAPI kol = sector.getFaction(Factions.KOL);
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);
        FactionAPI diktat = sector.getFaction(Factions.DIKTAT);
        FactionAPI hiigara = sector.getFaction("hiigaran_descendants");
        FactionAPI SCY = sector.getFaction("SCY");
        FactionAPI imperium = sector.getFaction("interstellarimperium");
        FactionAPI UAF = sector.getFaction("UAF");

        hiigara.setRelationship(path.getId(), RepLevel.FAVORABLE);
        hiigara.setRelationship(hegemony.getId(), RepLevel.HOSTILE);
        hiigara.setRelationship(pirates.getId(), RepLevel.HOSTILE);
        hiigara.setRelationship(diktat.getId(), RepLevel.WELCOMING);
        hiigara.setRelationship(tritachyon.getId(), RepLevel.NEUTRAL);
        hiigara.setRelationship(independent.getId(), RepLevel.WELCOMING);
        hiigara.setRelationship(church.getId(), RepLevel.HOSTILE);
        hiigara.setRelationship(kol.getId(), RepLevel.HOSTILE);
        hiigara.setRelationship(path.getId(), RepLevel.HOSTILE);


        if(SCY != null){
            hiigara.setRelationship(SCY.getId(), RepLevel.HOSTILE);
        }
        if(imperium != null){
            hiigara.setRelationship(imperium.getId(), RepLevel.HOSTILE);
        }
        if(UAF != null){
            hiigara.setRelationship(UAF.getId(), RepLevel.WELCOMING);
        }
    }
        
}
