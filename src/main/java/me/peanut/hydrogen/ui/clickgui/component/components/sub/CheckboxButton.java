package me.peanut.hydrogen.ui.clickgui.component.components.sub;

import me.peanut.hydrogen.font.FontHelper;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.ui.clickgui.component.Component;
import me.peanut.hydrogen.ui.clickgui.component.components.Button;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;

public class CheckboxButton extends Component {

	private boolean hovered;
	private final Setting op;
	private final Button parent;
	private int offset;
	private int x;
	private int y;
	
	public CheckboxButton(Setting option, Button button, int offset) {
		this.op = option;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
	}

	@Override
	public void renderComponent() {

		int c1 = new Color(17, 17, 17, 140).getRGB(); // 0x88111111
		int c3 = new Color(34, 34, 34, 140).getRGB(); // 0x88222222

		Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth() * 1), parent.parent.getY() + offset + 12, hovered ? 0x99000000 : 0x88000000);
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, c1);
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);


		if(Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getMode().equalsIgnoreCase("TTF")) {
			FontHelper.verdana.drawStringWithShadow(this.hovered ? "§7" + this.op.getName() : this.op.getName(), (parent.parent.getX() + 3) * 1.3333333333f + 5, (parent.parent.getY() + offset + 2) * 1.3333333333f, new Color(255, 255, 255));
		} else {
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.hovered ? "§7" + this.op.getName() : this.op.getName(), (parent.parent.getX() + 3) * 1.3333333333f + 5, (parent.parent.getY() + offset + 2) * 1.3333333333f + 2, -1);
		}

		GL11.glPopMatrix();
		Gui.drawRect(parent.parent.getX() + parent.parent.getWidth() - 2, parent.parent.getY() + offset + 3, parent.parent.getX() + parent.parent.getWidth() - 8, parent.parent.getY() + offset + 9, 0x88999999);
		if(this.op.isEnabled()) {
			Gui.drawRect(parent.parent.getX() + parent.parent.getWidth() - 3, parent.parent.getY() + offset + 4, parent.parent.getX() + parent.parent.getWidth() - 7, parent.parent.getY() + offset + 8, 0x99000000);
		}
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
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
			this.op.setState(!op.isEnabled());
		}
	}
	
	public boolean isMouseOnButton(int x, int y) {
		return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
	}
}
