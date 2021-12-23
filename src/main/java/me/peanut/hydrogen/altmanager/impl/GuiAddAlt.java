package me.peanut.hydrogen.altmanager.impl;

import java.io.IOException;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.altmanager.account.Account;
import me.peanut.hydrogen.altmanager.components.GuiPasswordField;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiAddAlt extends GuiScreen {

	private GuiTextField email;

	private GuiPasswordField password;

	private final GuiScreen parent;

	public GuiAddAlt(GuiScreen parent) {
		this.parent = parent;
	}

	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 92 + 12, "Add"));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 116 + 12, "Back"));
		this.email = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
		this.email.setMaxStringLength(2147483647);
		this.email.setFocused(true);
		this.password = new GuiPasswordField(this.fontRendererObj, this.width / 2 - 100, 100, 200, 20);
		this.password.setMaxStringLength(2147483647);
	}


	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		this.email.drawTextBox();
		this.password.drawTextBox();
		drawCenteredString(this.mc.fontRendererObj, "Add Alt", this.width / 2, 20, -1);
		if (this.email.getText().isEmpty() && !this.email.isFocused())
			drawString(this.mc.fontRendererObj, "§7Email", this.width / 2 - 96, 66, -1);
		if (this.password.getText().isEmpty() && !this.password.isFocused())
			drawString(this.mc.fontRendererObj, "§7Password", this.width / 2 - 96, 106, -1);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public void keyTyped(char character, int keyCode) throws IOException {
		this.email.textboxKeyTyped(character, keyCode);
		this.password.textboxKeyTyped(character, keyCode);
		if (keyCode == 15) {
			this.email.setFocused(!this.email.isFocused());
			this.password.setFocused(!this.password.isFocused());
		}
		if (keyCode == 28)
			actionPerformed(this.buttonList.get(0));
	}

	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.email.mouseClicked(mouseX, mouseY, mouseButton);
		this.password.mouseClicked(mouseX, mouseY, mouseButton);
	}



	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if (!this.email.getText().isEmpty()) {
				Account account = new Account(this.email.getText(), this.password.getText(), this.email.getText());
				Hydrogen.getClient().accountManager.getAccounts().add(account);
				Hydrogen.getClient().accountManager.save();
			}
			this.mc.displayGuiScreen(this.parent);
			break;
		case 1:
			this.mc.displayGuiScreen(this.parent);
			break;
		}
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	public void updateScreen() {
		this.email.updateCursorCounter();
		this.password.updateCursorCounter();
	}

}