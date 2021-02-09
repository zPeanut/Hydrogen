package tk.peanut.hydrogen.ui.clickgui.component;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.ui.clickgui.component.components.Button;
import tk.peanut.hydrogen.utils.FontUtil;
import tk.peanut.hydrogen.utils.Utils;

public class Frame {

	public ArrayList<Component> components;
	public Category category;
	public boolean open;
	private static int width;
	public static int y;

	public static int x;
	private int barHeight;
	private boolean isDragging;
	public int dragX;
	public int dragY;

	public Frame(Category cat) {
		this.components = new ArrayList<Component>();
		this.category = cat;
		this.width = 88;
		this.x = 5;
		this.y = 5;
		this.barHeight = 13;
		this.dragX = 0;
		this.open = false;
		this.isDragging = false;
		int tY = this.barHeight;
		

		
		for(Module mod : Hydrogen.getClient().moduleManager.getModulesInCategory(category)) {
			Button modButton = new Button(mod, this, tY);
			this.components.add(modButton);
			tY += 12;
		}
	}
	
	public ArrayList<Component> getComponents() {
		return components;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public void setDrag(boolean drag) {
		this.isDragging = drag;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public void renderFrame(FontRenderer fontRenderer) {
		Gui.drawRect(this.x - 2, this.y - 2, this.x + this.width + 2, this.y + this.barHeight, 0xff33aaff);
		FontUtil.drawTotalCenteredStringWithShadow(this.category.name(), (this.x + this.width / 2), (this.y + 7) - 1, -1);
		if(this.open) {
			if(!this.components.isEmpty()) {
				//Gui.drawRect(this.x, this.y + this.barHeight, this.x + 1, this.y + this.barHeight + (12 * components.size()), new Color(0, 200, 20, 150).getRGB());
				//Gui.drawRect(this.x, this.y + this.barHeight + (12 * components.size()), this.x + this.width, this.y + this.barHeight + (12 * components.size()) + 1, new Color(0, 200, 20, 150).getRGB());
				//Gui.drawRect(this.x + this.width, this.y + this.barHeight, this.x + this.width - 1, this.y + this.barHeight + (12 * components.size()), new Color(0, 200, 20, 150).getRGB());
				for(Component component : components) {
					component.renderComponent();
				}
			}
		}
	}
	
	public void refresh() {
		int off = this.barHeight;
		for(Component comp : components) {
			comp.setOff(off);
			off += comp.getHeight();
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}
	
	public void updatePosition(int mouseX, int mouseY) {
		if(this.isDragging) {
			this.setX(mouseX - dragX);
			this.setY(mouseY - dragY);
		}
	}
	
	public boolean isWithinHeader(int x, int y) {
		if(x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight) {
			return true;
		}
		return false;
	}

	public static class Tooltip {
		public String text = "";
		public boolean enabled;
		private float boxFade;
		private float padding = 6f;
		private int height;


		public void update(int mouseX, int mouseY) {
			x = mouseX + 12;
			y = mouseY - 12;
		}

		public void render(String name) {
			this.height = (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2);

			GlStateManager.color(0, 0, 0, 0);
			GL11.glColor4f(0, 0, 0, 0);


			Utils.drawBorderedCorneredRect((int) (x - padding), (int) (y - padding) + 2, (int) (x + width + padding) + Minecraft.getMinecraft().fontRendererObj.getStringWidth(name), (int) (y + height + padding), 2, 0x90000000, 0x80000000);
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, (float) x, (float)y + (float)height - 4, -1);


			Utils.startClip((int)(x - padding), (int)(y), (int)(x + width + padding), (int)(y + height + padding));

			Utils.endClip();
		}
	}

}
