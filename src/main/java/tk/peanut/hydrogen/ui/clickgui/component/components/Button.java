package tk.peanut.hydrogen.ui.clickgui.component.components;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.ui.clickgui.ClickGui;
import tk.peanut.hydrogen.ui.clickgui.component.Component;
import tk.peanut.hydrogen.ui.clickgui.component.Frame;
import tk.peanut.hydrogen.ui.clickgui.component.components.sub.*;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.FontUtil;
import tk.peanut.hydrogen.utils.Utils;

public class Button extends Component {

	public Module mod;
	public Frame parent;
	public int offset;
	private boolean isHovered;
	public ArrayList<Component> subcomponents;
	public boolean open;
	private int height;
	
	public Button(Module mod, Frame parent, int offset) {
		this.mod = mod;
		this.parent = parent;
		this.offset = offset;
		this.subcomponents = new ArrayList<Component>();
		this.open = false;
		height = 12;
		int opY = offset + 12;
		if(Hydrogen.getClient().settingsManager.getSettingsByMod(mod) != null) {
			for(Setting s : Hydrogen.getClient().settingsManager.getSettingsByMod(mod)){
				if(s.isCombo()){
					this.subcomponents.add(new ModeButton(s, this, mod, opY));
					opY += 12;
				}
				if(s.isSlider()){
					this.subcomponents.add(new Slider(s, this, opY));
					opY += 12;
				}
				if(s.isCheck()){
					this.subcomponents.add(new Checkbox(s, this, opY));
					opY += 12;
				}
			}
		}
		this.subcomponents.add(new Keybind(this, opY));
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
		int opY = offset + 12;
		for(Component comp : this.subcomponents) {
			comp.setOff(opY);
			opY += 12;
		}
	}

	public void update(int mouseX, int mouseY) {
		this.parent.x = mouseX + 12;
		this.parent.y = mouseY - 12;
	}
	
	@Override
	public void renderComponent() {
		Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x99000000);
		Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x33000000);
		if(this.mod.isEnabled() && this.isHovered) {
			Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x20000000);
		}
		if(this.mod.isEnabled()) {
			Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x40000000);
		}
		if(this.isHovered) {
			Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x30000000);
		}
		FontUtil.drawTotalCenteredStringWithShadow(this.mod.toggled ? this.mod.getName() : this.isHovered ? "§7" + this.mod.getName() : "§f" + this.mod.getName(), parent.getX() + parent.getWidth() / 2, (parent.getY() + offset + 7), 0xffffe9ad);
		if(this.subcomponents.size() > 1)
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.open ? "..." : "§f...", (parent.getX() + parent.getWidth() - 10), (parent.getY() + offset + 2), 0xffffe9ad);

		if(this.open) {
			if (!this.subcomponents.isEmpty()) {
				for (Component comp : this.subcomponents) {
					comp.renderComponent();
				}
				Gui.drawRect(parent.getX() + 2, parent.getY() + this.offset + 12, parent.getX() + 3, parent.getY() + this.offset + ((this.subcomponents.size() + 1) * 12), ClickGui.color);
			}
		}

		if(this.isHovered && Hydrogen.getClient().settingsManager.getSettingByName("Tooltip").isEnabled()) {

			this.height = (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2);
			int padding = 6;

			GlStateManager.color(0, 0, 0, 0);
			GL11.glColor4f(0, 0, 0, 0);


			Utils.drawBorderedCorneredRect((int) (this.parent.getX() - padding) + 100, (int) (this.parent.getY() - padding) + 2 + this.offset, (int) (this.parent.getX() + padding) + Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.mod.getDescription()) + 100, (int) (this.parent.getY() + height + padding) + this.offset, 2, 0x90000000, 0x80000000);
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.mod.getDescription(), (float) this.parent.getX() + 100, (float)this.parent.getY() + (float)height - 4 + this.offset, -1);


			Utils.startClip((int)(this.parent.getX() - padding), (int)(this.parent.getY()), (int)(this.parent.getX() + this.parent.getWidth() + padding), (int)(this.parent.getY() + height + padding));

			Utils.endClip();
		}
	}
	
	@Override
	public int getHeight() {
		if(this.open) {
			return (12 * (this.subcomponents.size() + 1));
		}
		return 12;
	}
	
	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.isHovered = isMouseOnButton(mouseX, mouseY);
		if(!this.subcomponents.isEmpty()) {
			for(Component comp : this.subcomponents) {
				comp.updateComponent(mouseX, mouseY);
			}
		}
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButton(mouseX, mouseY) && button == 0) {
			this.mod.toggle();
		}
		if(isMouseOnButton(mouseX, mouseY) && button == 1) {
			this.open = !this.open;
			this.parent.refresh();
		}
		for(Component comp : this.subcomponents) {
			comp.mouseClicked(mouseX, mouseY, button);
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		for(Component comp : this.subcomponents) {
			comp.mouseReleased(mouseX, mouseY, mouseButton);
		}
	}
	
	@Override
	public void keyTyped(char typedChar, int key) {
		for(Component comp : this.subcomponents) {
			comp.keyTyped(typedChar, key);
		}
	}
	
	public boolean isMouseOnButton(int x, int y) {
		if(x > parent.getX() && x < parent.getX() + parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset) {
			return true;
		}
		return false;
	}


}
