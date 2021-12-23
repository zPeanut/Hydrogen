package me.peanut.hydrogen.altmanager.components;

import me.peanut.hydrogen.Hydrogen;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.ScaledResolution;
import me.peanut.hydrogen.altmanager.GuiAltManager;
import me.peanut.hydrogen.altmanager.account.Account;

public class GuiAccountList extends GuiSlot {

	public int selected = -1;

	private final GuiAltManager parent;

	public GuiAccountList(GuiAltManager parent) {
		super(Minecraft.getMinecraft(), parent.width, parent.height, 36, parent.height - 56, 40);
		this.parent = parent;
	}

	public int getSize() {
		return Hydrogen.getClient().accountManager.getAccounts().size();
	}

	public void elementClicked(int i, boolean b, int i1, int i2) {
		this.selected = i;
		if (b)
			this.parent.login(getAccount(i));
	}

	protected boolean isSelected(int i) {
		return (i == this.selected);
	}

	protected void drawBackground() {
	}

	protected void drawSlot(int i, int i1, int i2, int i3, int i4, int i5) {
		Account account = getAccount(i);
		Minecraft minecraft = Minecraft.getMinecraft();
		ScaledResolution scaledResolution = new ScaledResolution(minecraft);
		FontRenderer fontRenderer = minecraft.fontRendererObj;
		int x = i1 + 2;
		int y = i2;
		if (y >= scaledResolution.getScaledHeight() || y < 0)
			return;
		GL11.glTranslated(x, y, 0.0D);
		drawFace(account.getName(), 0, 6, 24, 24);
		fontRenderer.drawStringWithShadow(account.getName(), 30.0F, 6.0F, -1);
		fontRenderer.drawStringWithShadow("§7" + account.getEmail(), 30.0F, (6 + fontRenderer.FONT_HEIGHT + 2), -1);
		GL11.glTranslated(-x, -y, 0.0D);
	}

	public Account getAccount(int i) {
		return Hydrogen.getClient().accountManager.getAccounts().get(i);
	}

	private void drawFace(String name, int x, int y, int w, int h) {
		try {
			AbstractClientPlayer.getDownloadImageSkin(AbstractClientPlayer.getLocationSkin(name), name).loadTexture(Minecraft.getMinecraft().getResourceManager());
			Minecraft.getMinecraft().getTextureManager().bindTexture(AbstractClientPlayer.getLocationSkin(name));
			GL11.glEnable(3042);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			Gui.drawModalRectWithCustomSizedTexture(x, y, 24.0F, 24.0F, w, h, 192.0F, 192.0F);
			Gui.drawModalRectWithCustomSizedTexture(x, y, 120.0F, 24.0F, w, h, 192.0F, 192.0F);
			GL11.glDisable(3042);
		} catch (Exception exception) {
		}
	}

	public void removeSelected() {
		if (this.selected == -1)
			return;
		Hydrogen.getClient().accountManager.getAccounts().remove(getAccount(this.selected));
		Hydrogen.getClient().accountManager.save();
	}

	public Account getSelectedAccount() {
		return getAccount(this.selected);
	}

}