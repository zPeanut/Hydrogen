package tk.peanut.hydrogen.ui.clickgui.component.components;

import java.awt.*;
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
import tk.peanut.hydrogen.ui.clickgui.component.components.sub.Checkbox;
import tk.peanut.hydrogen.utils.BlurUtil;
import tk.peanut.hydrogen.utils.FontHelper;
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

	int tooltipX;
	int tooltipY;
	
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
		this.tooltipX = mouseX + 12;
		this.tooltipY = mouseY - 12;
	}

	public void drawTooltip() {
		this.height = (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2);
		int padding = 6;

		GlStateManager.color(0, 0, 0, 0);
		GL11.glColor4f(0, 0, 0, 0);


		Utils.drawBorderedCorneredRect((int) (this.parent.getX() - padding) + tooltipX, (int) (this.parent.getY() - padding) + 2 + tooltipY, (int) (this.parent.getX() + parent.getWidth() + padding) + Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod.getDescription()) + tooltipX, (int) (this.parent.getY() + height + padding) + tooltipY, 2, 0x90000000, 0x80000000);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(mod.getDescription(), (float) this.parent.getX(), (float) this.parent.getY() + (float) height - 4, -1);

		Utils.startClip((int) (tooltipX - padding), (int) (tooltipY), (int) (tooltipX + parent.getWidth() + padding), (int) (tooltipY + height + padding));
		Utils.endClip();
	}
	
	@Override
	public void renderComponent() {

		Utils.rect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x33000000);
		Utils.rect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x33000000);

		if(this.mod.isEnabled() && this.isHovered) {
			Utils.rect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth() - 10, this.parent.getY() + 12 + this.offset, 0x20000000);
		}

		if(this.mod.isEnabled()) {
			Utils.rect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x40000000);
		}

		if(this.isHovered) {
			Utils.rect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x30000000);
		}

		if(Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getValString().equalsIgnoreCase("TTF")) {
			FontUtil.drawTotalCenteredStringWithShadow2(this.mod.toggled ? this.mod.getName() : this.isHovered ? "§7" + this.mod.getName() : "§f" + this.mod.getName(), parent.getX() + parent.getWidth() / 2, (parent.getY() + offset + 7) - 2, new Color(255, 233, 181));
		} else {
			FontUtil.drawTotalCenteredStringWithShadow(this.mod.toggled ? this.mod.getName() : this.isHovered ? "§7" + this.mod.getName() : "§f" + this.mod.getName(), parent.getX() + parent.getWidth() / 2, (parent.getY() + offset + 7), 0xffffe9b5);
		}

		if(this.subcomponents.size() > 1)
			FontHelper.sf_l.drawStringWithShadow(this.open ? "v" : "§f>", (parent.getX() + parent.getWidth() - 10), (parent.getY() + offset), new Color(255, 230, 181));

		if(this.open) {
			if (!this.subcomponents.isEmpty()) {
				for (Component comp : this.subcomponents) {
					comp.renderComponent();
				}
				Utils.rect(parent.getX() + 2, parent.getY() + this.offset + 12, parent.getX() + 3, parent.getY() + this.offset + ((this.subcomponents.size() + 1) * 12), ClickGui.color);
			}
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
