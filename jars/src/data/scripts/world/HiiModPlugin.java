package data.scripts.world;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import data.scripts.world.HiiPolarisGen;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import exerelin.campaign.SectorManager;
import org.dark.shaders.light.LightData;
import org.dark.shaders.util.ShaderLib;
import org.dark.shaders.util.TextureData;
//import data.scripts.world.HiiHalcyonGen;
//import data.scripts.world.HiiSolitudeGen;


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
            throw new RuntimeException("Hiigaran Descendents requires GraphicsLib!" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=10982");
        }

    }
    
	private static void initHiigaranDescendants()
	{
			if (isExerelin && !SectorManager.getCorvusMode()) {
				return;
			}

			new HiigaraGen().generate(Global.getSector());

	}

	@Override
	public void onNewGame()
	{
		initHiigaranDescendants();
	}
        
}
