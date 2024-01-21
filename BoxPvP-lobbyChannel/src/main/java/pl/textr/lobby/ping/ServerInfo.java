package pl.textr.lobby.ping;

import java.net.InetSocketAddress;

public class ServerInfo {

    public ServerPing serverPing;

    public String serverName;
    public String displayName;
    public String host;
    public int port;
    public int slot;

    public boolean online;
    public String motd;
    public int playerCount;
    public int maxPlayers;

    public ServerInfo(String serverName, String host, int port, String displayName, int slot) {
        this.online = false;
        this.serverName = serverName;
        this.host = host;
        this.port = port;
        this.displayName = displayName;
        this.slot = slot;

        ServerPing serverPing = new ServerPing();
        serverPing.setAddress(new InetSocketAddress(host, port));
        this.serverPing = serverPing;
    }

    public ServerPing getServerPing() {
        return serverPing;
    }

    public void setServerPing(ServerPing serverPing) {
        this.serverPing = serverPing;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

}
