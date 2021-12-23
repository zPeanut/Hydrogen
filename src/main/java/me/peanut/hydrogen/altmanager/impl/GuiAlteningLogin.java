package me.peanut.hydrogen.altmanager.impl;

import java.io.IOException;
import java.util.Objects;

import com.thealtening.auth.AlteningAlt;
import com.thealtening.auth.TheAltening;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.altmanager.thread.AccountLoginThread;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;

public class GuiAlteningLogin extends GuiScreen {

	private final GuiScreen previousScreen;

	public static AccountLoginThread thread;

	public static GuiTextField token;

	public static GuiTextField key;

	public GuiAlteningLogin(GuiScreen previousScreen) {
		this.previousScreen = previousScreen;
	}

	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case -1:
			if (key.getText().isEmpty() || token.getText().isEmpty())
				return;
			Hydrogen.getClient().accountManager.setAlteningKey(key.getText());
			Hydrogen.getClient().accountManager.setLastAlteningAlt(token.getText());
			thread = new AccountLoginThread(token.getText().replaceAll(" ", ""), "gaymer");
			thread.run();
			Hydrogen.getClient().accountManager.save();
			break;
		case 0:
			if (key.getText().isEmpty())
				return;
			try {
				TheAltening theAltening = new TheAltening(key.getText());
				AlteningAlt account = theAltening.generateAccount(theAltening.getUser());
				token.setText((Objects.requireNonNull(account)).getToken());
				Hydrogen.getClient().accountManager.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!token.getText().isEmpty()) {
				Hydrogen.getClient().accountManager.setAlteningKey(key.getText());
				Hydrogen.getClient().accountManager.setLastAlteningAlt(token.getText());
				thread = new AccountLoginThread(token.getText().replaceAll(" ", ""), "gaymer");
				thread.run();
				Hydrogen.getClient().accountManager.save();
			}
			break;
		case 1:
			this.mc.displayGuiScreen(this.previousScreen);
			break;
		case 2:
			this.mc.displayGuiScreen(new GuiMultiplayer(this));
			break;
		case 3:
			if (key.getText().isEmpty() || Hydrogen.getClient().accountManager.getLastAlteningAlt() == null)
				return;
			Hydrogen.getClient().accountManager.setAlteningKey(key.getText());
			thread = new AccountLoginThread(Hydrogen.getClient().accountManager.getLastAlteningAlt().replaceAll(" ", ""), "gaymer");
			thread.run();
			Hydrogen.getClient().accountManager.save();
			break;
		}
	}

	public void drawScreen(int x, int y, float z) {
		drawDefaultBackground();
		ScaledResolution sr = new ScaledResolution(this.mc);
		token.drawTextBox();
		key.drawTextBox();
		drawCenteredString(this.mc.fontRendererObj, "The Altening Login", this.width / 2, sr.getScaledHeight() / 5, 16777215);
		drawCenteredString(this.fontRendererObj, "Logged in as: §7" + this.mc.session.getUsername(), this.width / 2, sr.getScaledHeight() / 4 + 12, 16777215);
		if (token.getText().isEmpty() && !token.isFocused())
			drawString(this.mc.fontRendererObj, "§7Token", this.width / 2 - 94, this.height / 4 + 48, 16777215);
		if (key.getText().isEmpty() && !key.isFocused())
			drawString(this.mc.fontRendererObj, "§7Altening Key", this.width / 2 - 94, this.height / 4 + 78, 16777215);
		super.drawScreen(x, y, z);
	}

	public void initGui() {
		this.buttonList.add(new GuiButton(-1, this.width / 2 - 100, this.height / 4 + 124, "Login"));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 172, "Back"));
		this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 148, "Last Alt"));
		token = new GuiTextField(this.height / 4 + 24, this.mc.fontRendererObj, this.width / 2 - 98, this.height / 4 + 42, 196, 20);
		token.setMaxStringLength(2147483647);
		key = new GuiTextField(this.height / 4 + 22, this.mc.fontRendererObj, this.width / 2 - 98, this.height / 4 + 72, 196, 20);
		key.setMaxStringLength(2147483647);
		if (Hydrogen.getClient().accountManager.getAlteningKey() != null)
			key.setText(Hydrogen.getClient().accountManager.getAlteningKey());
		Keyboard.enableRepeatEvents(true);
	}

	protected void keyTyped(char character, int key) {
		try {
			super.keyTyped(character, key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (character == '\t' && token.isFocused())
			token.setFocused(token.isFocused());
		if (character == '\r')
			actionPerformed(this.buttonList.get(0));
		if (character == '\t' && GuiAlteningLogin.key.isFocused())
			GuiAlteningLogin.key.setFocused(GuiAlteningLogin.key.isFocused());
		token.textboxKeyTyped(character, key);
		GuiAlteningLogin.key.textboxKeyTyped(character, key);
	}

	public void updateScreen() {
		token.updateCursorCounter();
		key.updateCursorCounter();
	}

	protected void mouseClicked(int x, int y, int button) {
		try {
			super.mouseClicked(x, y, button);
		} catch (IOException e) {
			e.printStackTrace();
		}
		token.mouseClicked(x, y, button);
		key.mouseClicked(x, y, button);
	}

}