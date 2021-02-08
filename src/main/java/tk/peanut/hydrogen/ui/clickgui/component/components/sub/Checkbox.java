package tk.peanut.hydrogen.ui.clickgui.component.components.sub;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import tk.peanut.hydrogen.file.files.SettingsButtonFile;
import tk.peanut.hydrogen.ui.clickgui.component.Component;
import tk.peanut.hydrogen.ui.clickgui.component.components.Button;
import tk.peanut.hydrogen.settings.Setting;

public class Checkbox extends Component {

	private boolean hovered;
	private Setting op;
	private Button parent;
	private int offset;
	private int x;
	private int y;
	
	public Checkbox(Setting option, Button button, int offset) {
		this.op = option;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
	}

	@Override
	public void renderComponent() {
		Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth() * 1), parent.parent.getY() + offset + 12, this.hovered ? 0x88222222 : 0x88111111);
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, 0x88111111);
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.hovered ? "§7" + this.op.getName() : this.op.getName(), (parent.parent.getX() + 10 + 4) * 1.3333333333f + 5, (parent.parent.getY() + offset + 2) * 1.3333333333f + 2, -1);
		GL11.glPopMatrix();
		Gui.drawRect(parent.parent.getX() + 3 + 4, parent.parent.getY() + offset + 3, parent.parent.getX() + 9 + 4, parent.parent.getY() + offset + 9, 0x88999999);
		if(this.op.isEnabled())
			Gui.drawRect(parent.parent.getX() + 4 + 4, parent.parent.getY() + offset + 4, parent.parent.getX() + 8 + 4, parent.parent.getY() + offset + 8, 0x99000000);
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
			this.op.setValBoolean(!op.isEnabled());;
			SettingsButtonFile.saveState();
		}
	}
	
	public boolean isMouseOnButton(int x, int y) {
		if(x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12) {
			return true;
		}
		return false;
	}
}
