package me.peanut.hydrogen.altmanager.thread;

import java.net.Proxy;
import java.util.UUID;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import com.thealtening.auth.AltService;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.altmanager.account.Account;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.util.Session;
import me.peanut.hydrogen.altmanager.GuiAltManager;
import me.peanut.hydrogen.altmanager.impl.GuiAlteningLogin;

public class AccountLoginThread extends Thread {

	private final String email;

	private final String password;

	public static boolean unknownBoolean1;

	private String status = "§eWaiting for login...";

	public AccountLoginThread(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public void run() {
		if ((Minecraft.getMinecraft()).currentScreen instanceof GuiAlteningLogin) {
			try {
				Hydrogen.getClient().altService.switchService(AltService.EnumAltService.THEALTENING);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				Utils.errorLog("Couldn't switch to TheAltening AltService");
			}
			unknownBoolean1 = false;
		} else if (unknownBoolean1) {
			try {
				Hydrogen.getClient().altService.switchService(AltService.EnumAltService.MOJANG);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				Utils.errorLog("Couldn't switch to Mojang AltService");
			}
		}

		if (password == null || password.isEmpty()) {
			Minecraft.getMinecraft().session = new Session(this.email, "", "", "mojang");
			this.status = "§aLogged in as §7" + Minecraft.getMinecraft().session.getUsername() + " §e(Cracked)§a.";
			return;
		}

		unknownBoolean1 = true;
		this.status = "§6Logging in...";
		YggdrasilAuthenticationService yService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
		UserAuthentication userAuth = yService.createUserAuthentication(Agent.MINECRAFT);
		if (userAuth == null) {
			this.status = "§4Unknown error.";
			return;
		}
		userAuth.setUsername(this.email);
		userAuth.setPassword(this.password);
		try {
			userAuth.logIn();
			Session session = new Session(userAuth.getSelectedProfile().getName(), userAuth.getSelectedProfile().getId().toString(), userAuth.getAuthenticatedToken(), this.email.contains("@") ? "mojang" : "legacy");
			Minecraft.getMinecraft().session = session;
			Account account = Hydrogen.getClient().accountManager.getAccountByEmail(this.email);
			account = (account == null ? new Account(email, password, session.getUsername()) : account);
			account.setName(session.getUsername());
			if (!((Minecraft.getMinecraft()).currentScreen instanceof GuiAlteningLogin) && !((Minecraft.getMinecraft()).currentScreen instanceof GuiDisconnected))
				Hydrogen.getClient().accountManager.setLastAlt(account);
			Hydrogen.getClient().accountManager.save();
			GuiAltManager.INSTANCE.currentAccount = account;
			if (unknownBoolean1) {
				this.status = String.format("§aLogged in as §7%s§a.", account.getName());
			}
		} catch (AuthenticationException exception) {
			this.status = "§4Login failed.";
		} catch (NullPointerException exception) {
			this.status = "§4Unknown error.";
		}
	}

	public String getStatus() {
		return this.status;
	}

}