package tk.peanut.phosphor.ui.clickgui.element;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.ui.clickgui.Panel;
import tk.peanut.phosphor.ui.clickgui.element.elements.ElementCheckBox;
import tk.peanut.phosphor.ui.clickgui.element.elements.ElementComboBox;
import tk.peanut.phosphor.ui.clickgui.element.elements.ElementSlider;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;
import tk.peanut.phosphor.ui.clickgui.util.ColorUtil;
import tk.peanut.phosphor.ui.clickgui.util.FontUtil;
import tk.peanut.phosphor.utils.KeybindManager;
import tk.peanut.phosphor.utils.Utils;


public class ModuleButton {
	public Module mod;
	public ArrayList<Element> menuelements;
	public Panel parent;
	public double x;
	public double y;
	public double width;
	public double height;
	public boolean extended = false;
	public boolean listening = false;

	/*
	 * Konstrukor
	 */
	public ModuleButton(Module imod, Panel pl) {
		mod = imod;
		height = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2;
		parent = pl;
		menuelements = new ArrayList<>();
		/*
		 * Settings wurden zuvor in eine ArrayList eingetragen
		 * dieses SettingSystem hat 3 Konstruktoren je nach
		 *  verwendetem Konstruktor §ndert sich die Value
		 *  bei .isCheck() usw. so kann man ganz einfach ohne
		 *  irgendeinen Aufwand bestimmen welches Element
		 *  f§r ein Setting ben§tigt wird :>
		 */
		if (Phosphor.getInstance().settingsManager.getSettingsByMod(imod) != null)
			for (Setting s : Phosphor.getInstance().settingsManager.getSettingsByMod(imod)) {
				if (s.isCheck()) {
					menuelements.add(new ElementCheckBox(this, s));
				} else if (s.isSlider()) {
					menuelements.add(new ElementSlider(this, s));
				} else if (s.isCombo()) {
					menuelements.add(new ElementComboBox(this, s));
				}
			}

	}

	/*
	 * Rendern des Elements 
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (isHovered(mouseX, mouseY)) {
			Utils.drawRect(x - 2, y, x + width + 2, y + height + 1, 0x55111111);
		}
		Color temp = ColorUtil.getClickGUIColor();
		int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 80).getRGB();
		
		/*
		 * Ist das Module an, wenn ja dann soll
		 *  #ein neues Rechteck in Gr§§e des Buttons den Knopf als Toggled kennzeichnen
		 *  #sich der Text anders f§rben
		 */
		int textcolor = 0xffafafaf;
		if (mod.isEnabled()) {
			Utils.drawRect(x - 2, y, x + width + 2, y + height + 1, 0x66111111);
			textcolor = 0xffefefef;
		}
		
		/*
		 * Ist die Maus §ber dem Element, wenn ja dann soll der Button sich anders f§rben
		 */

		
		/*
		 * Den Namen des Modules in die Mitte (x und y) rendern
		 */
		
		
		if (menuelements != null && menuelements.size() > 0) {
			
			FontUtil.drawTotalCenteredStringWithShadow(mod.getName(), x + width / 2, y + 1 + height / 2, textcolor);
			
			if (this.extended) {
			
			FontUtil.drawTotalCenteredStringWithShadow("§e  >", x + width - 6, y + 1 + height / 2, textcolor);
			
			} else {
				
				FontUtil.drawTotalCenteredStringWithShadow("§7  >", x + width - 6, y + 1 + height / 2, textcolor);
				
			}
			
		} else {
			
			FontUtil.drawTotalCenteredStringWithShadow(mod.getName(), x + width / 2, y + 1 + height / 2, textcolor);
		}
	}

	/*
	 * 'true' oder 'false' bedeutet hat der Nutzer damit interagiert und
	 * sollen alle anderen Versuche der Interaktion abgebrochen werden?
	 */
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (!isHovered(mouseX, mouseY))
			return false;

		/*
		 * Rechtsklick, wenn ja dann Module togglen, 
		 */
		if (mouseButton == 0) {
			mod.toggle();
			
			if(Phosphor.getInstance().settingsManager.getSettingByName("Sound").getValBoolean())
			Minecraft.getMinecraft().thePlayer.playSound("gui.button.press", 0.5f, 0.5f);
		} else if (mouseButton == 1) {
			/*
			 * Wenn ein Settingsmenu existiert dann sollen alle Settingsmenus 
			 * geschlossen werden und dieses ge§ffnet/geschlossen werden
			 */
			if (menuelements != null && menuelements.size() > 0) {
				boolean b = !this.extended;
				Phosphor.getInstance().clickgui.closeAllSettings();
				this.extended = b;
				
				if(Phosphor.getInstance().settingsManager.getSettingByName("Sound").getValBoolean())
				if(extended)Minecraft.getMinecraft().thePlayer.playSound("tile.piston.in", 0.3f, 1f);else Minecraft.getMinecraft().thePlayer.playSound("tile.piston.out", 0.3f, 1f);
			}
		} else if (mouseButton == 2) {
			/*
			 * MidClick => Set keybind (wait for next key)
			 */
			listening = true;
		}
		return true;
	}

	public boolean keyTyped(char typedChar, int keyCode)
		    throws IOException
		  {
		    KeybindManager mgr = Phosphor.getInstance().keybindManager;
		    if (this.listening)
		    {
		      if (keyCode != 1)
		      {
		        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Phosphor.getInstance().prefix + "§7 " + "Bound §9" + this.mod.getName() + "§7" + " to §9" + Keyboard.getKeyName(keyCode)));
		        mgr.bind(this.mod, keyCode);
		      }
		      else
		      {
		        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Phosphor.getInstance().prefix + "§7 " + "Unbound §9" + this.mod.getName()));
		        mgr.bind(this.mod, 0);
		      }
		      this.listening = false;
		      return true;
		    }
		    return false;
		  }

	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}

}
