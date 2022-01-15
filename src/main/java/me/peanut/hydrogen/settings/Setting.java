package me.peanut.hydrogen.settings;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import me.peanut.hydrogen.module.Module;

public class Setting {
	
	private final String name;
	private final Module parent;
	private final String mode;
	
	private String sval;
	private ArrayList<String> options;
	
	private boolean bval;
	
	private double dval;
	private double min;
	private double max;
	private boolean onlyint = false;

	private String textvalue;
	

	public Setting(String name, Module parent, String sval, ArrayList<String> options){
		this.name = name;
		this.parent = parent;
		this.sval = sval;
		this.options = options;
		this.mode = "Combo";
	}
	
	public Setting(String name, Module parent, boolean bval){
		this.name = name;
		this.parent = parent;
		this.bval = bval;
		this.mode = "Check";
	}

	public Setting(String name, Module parent, String text){
		this.name = name;
		this.parent = parent;
		this.textvalue = text;
		this.mode = "Text";
	}
	
	public Setting(String name, Module parent, double dval, double min, double max, boolean onlyint){
		this.name = name;
		this.parent = parent;
		this.dval = dval;
		this.min = min;
		this.max = max;
		this.onlyint = onlyint;
		this.mode = "Slider";
	}
	
	public String getName(){
		return name;
	}
	
	public Module getParentMod(){
		return parent;
	}
	
	public String getMode(){
		return this.sval;
	}
	
	public void setMode(String in){
		this.sval = in;
	}
	
	public ArrayList<String> getOptions(){
		return this.options;
	}
	
	public boolean isEnabled(){
		return this.bval;
	}
	
	public void setState(boolean in){
		this.bval = in;
	}

	public String getText() {
		return this.textvalue;
	}

	public void setText(String in) {
		this.textvalue = in;
	}
	
	public double getValue(){
		if(this.onlyint){
			this.dval = (int)dval;
		}
		return this.dval;
	}

	public void setValue(double in){
		this.dval = in;
	}
	
	public double getMin(){
		return this.min;
	}
	
	public double getMax(){
		return this.max;
	}
	
	public boolean isModeMode(){
		return this.mode.equalsIgnoreCase("Combo");
	}
	
	public boolean isModeButton(){
		return this.mode.equalsIgnoreCase("Check");
	}

	public boolean isModeText(){
		return this.mode.equalsIgnoreCase("Text");
	}
	
	public boolean isModeSlider(){
		return this.mode.equalsIgnoreCase("Slider");
	}
	
	public boolean onlyInt(){
		return this.onlyint;
	}
}
