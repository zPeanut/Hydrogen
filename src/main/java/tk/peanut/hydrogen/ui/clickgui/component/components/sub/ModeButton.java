package tk.peanut.hydrogen.ui.clickgui.component.components.sub;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.file.files.SettingsComboBoxFile;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.ui.clickgui.component.Component;
import tk.peanut.hydrogen.ui.clickgui.component.components.Button;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.FontHelper;

import java.awt.*;

public class ModeButton extends Component {

	private boolean hovered;
	private Button parent;
	private Setting set;
	private int offset;
	private int x;
	private int y;
	private Module mod;

	private int modeIndex;
	
	public ModeButton(Setting set, Button button, Module mod, int offset) {
		this.set = set;
		this.parent = button;
		this.mod = mod;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
		this.modeIndex = 0;
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
	}
	
	@Override
	public void renderComponent() {
		Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth() * 1), parent.parent.getY() + offset + 12, this.hovered ? 0x88222222 : 0x88111111);
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, 0x88111111);
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);

		if(!Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getValString().equalsIgnoreCase("TTF")) {
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.hovered ? "§7" + set.getName() + " " : set.getName() + " ", (parent.parent.getX() + 7) * 1.33333333333f, (parent.parent.getY() + offset + 2) * 1.33333333333f + 2, -1);
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(set.getValString(), (parent.parent.getX() + 86) * 1.33333333333f - Minecraft.getMinecraft().fontRendererObj.getStringWidth(set.getValString()), (parent.parent.getY() + offset + 2) * 1.33333333333f + 2, -1);
		} else {
			FontHelper.hfontbold.drawStringWithShadow(this.hovered ? "§7" + set.getName() + " " : set.getName() + " ", (parent.parent.getX() + 7) * 1.33333333333f, (parent.parent.getY() + offset + 2) * 1.33333333333f, Color.white);
			FontHelper.hfontbold.drawStringWithShadow(set.getValString(), (parent.parent.getX() + 86) * 1.33333333333f - FontHelper.hfontbold.getStringWidth(set.getValString()), (parent.parent.getY() + offset + 2) * 1.33333333333f, Color.white);
		}

		GL11.glPopMatrix();
	}
	
	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.hovered = isMouseOnButton(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
			int maxIndex = set.getOptions().size();
			modeIndex++;

			if(modeIndex + 1 > maxIndex)
				modeIndex = 0;
			set.setValString(set.getOptions().get(modeIndex));
			SettingsComboBoxFile.saveState();
		}
	}
	
	public boolean isMouseOnButton(int x, int y) {
		if(x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12) {
			return true;
		}
		return false;
	}
}
