package me.peanut.hydrogen.settings;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;

import java.util.ArrayList;

public class SettingsManager {
	
	private final ArrayList<Setting> settings;
	
	public SettingsManager(){
		this.settings = new ArrayList<Setting>();
	}
	
	public void addSetting(Setting in){
		this.settings.add(in);
	}
	
	public ArrayList<Setting> getSettings(){
		return this.settings;
	}
	
	public ArrayList<Setting> getSettingsByMod(Module mod){
		ArrayList<Setting> out = new ArrayList<Setting>();
		for(Setting s : getSettings()){
			if(s.getParentMod().equals(mod)){
				out.add(s);
			}
		}
		if(out.isEmpty()){
			return null;
		}
		return out;
	}

	public boolean hasSettingMod(Module mod){
		for(Setting s : getSettings()) {
			if(s.getParentMod() == mod) {
				return true;
			}
		}
		return false;
	}
	
	public Setting getSettingByName(Module mod, String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name) && set.getParentMod() == mod){
				return set;
			}
		}
		String errormessage = String.format("[%s] ERROR! Setting not found! [%s] in [%s] returned null!", Hydrogen.name, name, mod);
		return null;
	}

	public Setting getSettingByName(String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name)){
				return set;
			}
		}
		String errormessage = String.format("[%s] ERROR! Setting not found! [%s] returned null!", Hydrogen.name, name);
		return null;
	}

}