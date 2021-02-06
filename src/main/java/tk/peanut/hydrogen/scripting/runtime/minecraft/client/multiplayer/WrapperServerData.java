package tk.peanut.hydrogen.scripting.runtime.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.ServerData;

public class WrapperServerData {
    private ServerData real;

    public WrapperServerData(ServerData var1) {
        this.real = var1;
    }

    public ServerData unwrap() {
        return this.real;
    }

    public String getBase64EncodedIconData() {
        return this.real.getBase64EncodedIconData();
    }

    public void setBase64EncodedIconData(String var1) {
        this.real.setBase64EncodedIconData(var1);
    }


    public void copyFrom(WrapperServerData var1) {
        this.real.copyFrom(var1.unwrap());
    }

    public String getServerName() {
        return this.real.serverName;
    }

    public void setServerName(String var1) {
        this.real.serverName = var1;
    }

    public String getServerIP() {
        return this.real.serverIP;
    }

    public void setServerIP(String var1) {
        this.real.serverIP = var1;
    }

    public String getPopulationInfo() {
        return this.real.populationInfo;
    }

    public void setPopulationInfo(String var1) {
        this.real.populationInfo = var1;
    }

    public String getServerMOTD() {
        return this.real.serverMOTD;
    }

    public void setServerMOTD(String var1) {
        this.real.serverMOTD = var1;
    }

    public long getPingToServer() {
        return this.real.pingToServer;
    }

    public void setPingToServer(long var1) {
        this.real.pingToServer = var1;
    }

    public int getVersion() {
        return this.real.version;
    }

    public void setVersion(int var1) {
        this.real.version = var1;
    }

    public String getGameVersion() {
        return this.real.gameVersion;
    }

    public void setGameVersion(String var1) {
        this.real.gameVersion = var1;
    }

    public boolean isField_78841_f() {
        return this.real.field_78841_f;
    }

    public void setField_78841_f(boolean var1) {
        this.real.field_78841_f = var1;
    }

    public String getPlayerList() {
        return this.real.playerList;
    }

    public void setPlayerList(String var1) {
        this.real.playerList = var1;
    }
}
