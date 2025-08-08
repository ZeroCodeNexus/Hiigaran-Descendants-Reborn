package data.scripts.world;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;



public class HiigaraGen implements SectorGeneratorPlugin  {
    
    @Override
    public void generate(SectorAPI sector) {
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("hiigaran_descendants");
        
        initFactionRelationships(sector);
        
        new HiiPolarisGen().generate(sector);
        
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
        FactionAPI blackrock = sector.getFaction("blackrock_driveyards");
        FactionAPI SCY = sector.getFaction("SCY");
        FactionAPI neutrino = sector.getFaction("neutrinocorp");
        FactionAPI imperium = sector.getFaction("interstellarimperium");
        FactionAPI exigency = sector.getFaction("exigency");
        FactionAPI exipirated = sector.getFaction("exipirated");
        FactionAPI templars = sector.getFaction("templars");
        
        hiigara.setRelationship(path.getId(), RepLevel.FAVORABLE);
        hiigara.setRelationship(hegemony.getId(), RepLevel.COOPERATIVE);
        hiigara.setRelationship(pirates.getId(), RepLevel.HOSTILE);
        hiigara.setRelationship(diktat.getId(), RepLevel.WELCOMING);
        hiigara.setRelationship(tritachyon.getId(), RepLevel.NEUTRAL);
        hiigara.setRelationship(independent.getId(), RepLevel.WELCOMING);
        hiigara.setRelationship(church.getId(), RepLevel.HOSTILE);
        hiigara.setRelationship(kol.getId(), RepLevel.HOSTILE);
        
        if(blackrock != null){
            hiigara.setRelationship(blackrock.getId(), RepLevel.NEUTRAL);
        }
        if(SCY != null){
            hiigara.setRelationship(SCY.getId(), RepLevel.HOSTILE);
        }
        if(neutrino != null){
            hiigara.setRelationship(neutrino.getId(), RepLevel.COOPERATIVE);
        }
        if(imperium != null){
            hiigara.setRelationship(imperium.getId(), RepLevel.HOSTILE);
        }
        if(exigency != null){
            hiigara.setRelationship(exigency.getId(), RepLevel.NEUTRAL);
        }
        if(exipirated !=  null){
            hiigara.setRelationship(exipirated.getId(), RepLevel.HOSTILE);
        }
        if(templars != null){
            hiigara.setRelationship(templars.getId(), RepLevel.HOSTILE);
        }
    }
}
    
  






