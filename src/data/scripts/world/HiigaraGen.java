package data.scripts.world;

import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;


public class HiigaraGen implements SectorGeneratorPlugin  {
    
    @Override
    public void generate(SectorAPI sector) {
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("hiigaran_descendants");

        new HiiPolarisGen().generate(sector);
        
    }

}
    
  






