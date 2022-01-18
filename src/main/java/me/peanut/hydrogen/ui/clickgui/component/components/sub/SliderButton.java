package me.peanut.hydrogen.ui.clickgui.component.components.sub;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.ui.clickgui.component.Component;
import me.peanut.hydrogen.ui.clickgui.component.components.Button;
import me.peanut.hydrogen.settings.Setting;

public class SliderButton extends Component {

	private boolean hovered;

	private final Setting set;
	private final Button parent;
	private int offset;
	private int x;
	private int y;
	private boolean dragging = false;

	private double renderWidth;
	
	public SliderButton(Setting value, Button button, int offset) {
		this.set = value;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
	}
	
	@Override
	public void renderComponent() {

		int c1 = new Color(17, 17, 17, 140).getRGB(); // 0x88111111
		int c2 = new Color(0, 0, 0, 115).getRGB(); // 0x77000000
		int c3 = new Color(34, 34, 34, 140).getRGB(); // 0x88222222

		RenderUtil.rect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + parent.parent.getWidth(), parent.parent.getY() + offset + 12, hovered ? 0x99000000 : 0x88000000);
		final int drag = (int)(this.set.getValue() / this.set.getMax() * this.parent.parent.getWidth());
		RenderUtil.rect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (int) renderWidth, parent.parent.getY() + offset + 12, 0x88000000);
		if(this.hovered) {
			RenderUtil.rect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (int) renderWidth, parent.parent.getY() + offset + 12, 0x88000000);
		}
		RenderUtil.rect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, 0x88111111);

		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);
		if(Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getMode().equalsIgnoreCase("TTF")) {
			FontHelper.verdana.drawStringWithShadow(this.hovered ? "§7" + this.set.getName() + " " : this.set.getName() + " ", (parent.parent.getX() * 1.333333333333f + 9), (parent.parent.getY() + offset + 2) * 1.33333333333333f, Color.white);
			FontHelper.verdana.drawStringWithShadow(this.hovered ? "§7" + this.set.getValue() : String.valueOf(this.set.getValue()), (parent.parent.getX() + 86) * 1.3333333333f - FontHelper.verdana.getStringWidth(this.hovered ? "§7" + this.set.getValue() : String.valueOf(this.set.getValue())), (parent.parent.getY() + offset + 2) * 1.3333333333f, Color.white);
		} else {
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.hovered ? "§7" + this.set.getName() + " " : this.set.getName() + " ", (parent.parent.getX() * 1.333333333333f + 9), (parent.parent.getY() + offset + 2) * 1.33333333333333f + 2, -1);
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.hovered ? "§7" + this.set.getValue() : String.valueOf(this.set.getValue()), (parent.parent.getX() + 86) * 1.3333333333f - Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.hovered ? "§7" + this.set.getValue() : String.valueOf(this.set.getValue())), (parent.parent.getY() + offset + 2) * 1.3333333333f + 2, -1);
		}

		GL11.glPopMatrix();
	}

	@Override
	public void setOff(int newOff) {
		offset = newOff;
	}
	
	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.hovered = isMouseOnButtonD(mouseX, mouseY) || isMouseOnButtonI(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
		
		double diff = Math.min(88, Math.max(0, mouseX - this.x));

		double min = set.getMin();
		double max = set.getMax();
		
		renderWidth = (88) * (set.getValue() - min) / (max - min);
		
		if (dragging) {
			if (diff == 0) {
				set.setValue(set.getMin());
			}
			else {
				double newValue = roundToPlace(((diff / 88) * (max - min) + min), 2);
				set.setValue(newValue);
			}
		}
	}
	
	private static double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.parent.open) {
			dragging = true;
		}
		if(isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.parent.open) {
			dragging = true;
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		dragging = false;
	}
	
	public boolean isMouseOnButtonD(int x, int y) {
		return x > this.x && x < this.x + (parent.parent.getWidth() / 2 + 1) && y > this.y && y < this.y + 12;
	}
	
	public boolean isMouseOnButtonI(int x, int y) {
		return x > this.x + parent.parent.getWidth() / 2 && x < this.x + parent.parent.getWidth() && y > this.y && y < this.y + 12;
	}
}
