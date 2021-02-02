package tk.peanut.phosphor.scripting.runtime.minecraft.util;

import net.minecraft.util.Session;

public class WrapperSession {
    private Session real;

    public WrapperSession(Session var1) {
        this.real = var1;
    }

    public Session unwrap() {
        return this.real;
    }

    public String getSessionID() {
        return this.real.getSessionID();
    }

    public String getPlayerID() {
        return this.real.getPlayerID();
    }

    public String getUsername() {
        return this.real.getUsername();
    }

    public String getToken() {
        return this.real.getToken();
    }
}
