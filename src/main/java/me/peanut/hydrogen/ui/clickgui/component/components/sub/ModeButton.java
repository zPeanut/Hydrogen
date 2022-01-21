package me.peanut.hydrogen.ui.clickgui.component.components.sub;

import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.font.FontHelper;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.ui.clickgui.component.Component;
import me.peanut.hydrogen.ui.clickgui.component.components.Button;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;

public class ModeButton extends Component {

	private boolean hovered;
	private final Button parent;
	private final Setting set;
	private int offset;
	private int x;
	private int y;
	private final Module mod;

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

		int c1 = new Color(17, 17, 17, 140).getRGB(); // 0x88111111
		int c3 = new Color(34, 34, 34, 140).getRGB(); // 0x88222222

		Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth() * 1), parent.parent.getY() + offset + 12, hovered ? 0x99000000 : 0x88000000);
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, c1);
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);

		if(!Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getMode().equalsIgnoreCase("TTF")) {
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.hovered ? "§7" + set.getName() + " " : set.getName() + " ", (parent.parent.getX() + 7) * 1.33333333333f, (parent.parent.getY() + offset + 2) * 1.33333333333f + 2, -1);
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(set.getMode(), (parent.parent.getX() + 86) * 1.33333333333f - Minecraft.getMinecraft().fontRendererObj.getStringWidth(set.getMode()), (parent.parent.getY() + offset + 2) * 1.33333333333f + 2, -1);
		} else {
			FontHelper.verdana.drawStringWithShadow(this.hovered ? "§7" + set.getName() + " " : set.getName() + " ", (parent.parent.getX() + 7) * 1.33333333333f, (parent.parent.getY() + offset + 2) * 1.33333333333f, Color.white);
			FontHelper.verdana.drawStringWithShadow(set.getMode(), (parent.parent.getX() + 86) * 1.33333333333f - FontHelper.verdana.getStringWidth(set.getMode()), (parent.parent.getY() + offset + 2) * 1.33333333333f, Color.white);
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

			if(modeIndex + 1 > maxIndex) {
				modeIndex = 0;
			}
			set.setMode(set.getOptions().get(modeIndex));
		}
	}
	
	public boolean isMouseOnButton(int x, int y) {
		return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
	}
}
