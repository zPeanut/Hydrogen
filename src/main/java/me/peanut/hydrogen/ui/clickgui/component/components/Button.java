package me.peanut.hydrogen.ui.clickgui.component.components;

import java.awt.*;
import java.util.ArrayList;

import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.ui.clickgui.component.components.sub.Checkbox;
import me.peanut.hydrogen.ui.clickgui.component.components.sub.Keybind;
import me.peanut.hydrogen.ui.clickgui.component.components.sub.ModeButton;
import me.peanut.hydrogen.ui.clickgui.component.components.sub.Slider;
import me.peanut.hydrogen.utils.FontHelper;
import me.peanut.hydrogen.utils.FontUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.ui.clickgui.ClickGui;
import me.peanut.hydrogen.ui.clickgui.component.Component;
import me.peanut.hydrogen.ui.clickgui.component.Frame;
import me.peanut.hydrogen.settings.Setting;

public class Button extends Component {

	public Module mod;
	public Frame parent;
	public int offset;
	private boolean isHovered;
	public ArrayList<Component> subcomponents;
	public boolean open;
	public int height;
	private	int tooltipX;
	private int tooltipY;

	
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

	public void updateTooltipPosition(int mouseX, int mouseY) {
		tooltipX = mouseX + 18;
		tooltipY = mouseY - 18;
	}

	public void renderTooltip(String name) {
		boolean ttf = Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getValString().equalsIgnoreCase("TTF");
		if(ttf) {
			Utils.drawBorderedCorneredRect(parent.getWidth() / 2 + tooltipX - 54, this.parent.barHeight + tooltipY - 3, parent.getWidth() / 2 + tooltipX + FontHelper.sf_l.getStringWidth(name) - 47, this.parent.barHeight + tooltipY + 12, 2, 0x95000000, 0x80000000);
			FontHelper.sf_l.drawStringWithShadow(name, parent.getWidth() / 2 + tooltipX - 50, (this.parent.barHeight + tooltipY) - 2, Color.white);

			Utils.startClip(parent.getWidth() / 2 + tooltipX - 54, this.parent.barHeight + tooltipY - 3, parent.getWidth() / 2 + tooltipX + FontHelper.sf_l.getStringWidth(name) - 45, this.parent.barHeight + tooltipY + 12);
			Utils.endClip();
		} else {
			Utils.drawBorderedCorneredRect(parent.getWidth() / 2 + tooltipX - 54, this.parent.barHeight + tooltipY - 3, parent.getWidth() / 2 + tooltipX + Minecraft.getMinecraft().fontRendererObj.getStringWidth(name) - 45, this.parent.barHeight + tooltipY + 12, 2, 0x95000000, 0x80000000);
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, parent.getWidth() / 2 + tooltipX - 50, (this.parent.barHeight + tooltipY), -1);

			Utils.startClip(parent.getWidth() / 2 + tooltipX - 54, this.parent.barHeight + tooltipY - 3, parent.getWidth() / 2 + tooltipX + Minecraft.getMinecraft().fontRendererObj.getStringWidth(name) - 45, this.parent.barHeight + tooltipY + 12);
			Utils.endClip();
		}
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
	
	@Override
	public void renderComponent() {
		Utils.rect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x33000000);
		Utils.rect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x33000000);

		if(this.mod.isEnabled() && this.isHovered) {
			Utils.rect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, 0x20000000);
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

		if(this.isHovered && Hydrogen.getClient().settingsManager.getSettingByName("Tooltip").isEnabled()) {
			renderTooltip(mod.getDescription());
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
		if(Hydrogen.getClient().settingsManager.getSettingByName("Tooltip").isEnabled()) {
			updateTooltipPosition(mouseX, mouseY);
		}
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
		return x > parent.getX() && x < parent.getX() + parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
	}


}
