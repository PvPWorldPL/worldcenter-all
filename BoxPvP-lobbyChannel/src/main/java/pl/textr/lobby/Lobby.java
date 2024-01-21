package pl.textr.lobby;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import pl.textr.lobby.commands.ChannelCommand;
import pl.textr.lobby.listener.InventoryClickListener;
import pl.textr.lobby.listener.PlayerInteractListener;
import pl.textr.lobby.listener.PlayerJoinListener;
import pl.textr.lobby.misc.ItemBuilder;
import pl.textr.lobby.ping.HexTransform;
import pl.textr.lobby.ping.ServerInfo;
import pl.textr.lobby.ping.ServerPing;


public class Lobby extends JavaPlugin implements PluginMessageListener {

   

	public  File file = new File("plugins/Lobby", "config.yml");
    public  FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public  HashMap<String, ServerInfo> servers = new HashMap<>();
    public  String currentServer;

    public  boolean updateAvailable = false;

    public  boolean reloading = false;



    private static Lobby instance;

    @Override
    public void onEnable() {
       instance = this;
        this.saveDefaultConfig();
        try {
        this.cfg.load(file);
        } catch (IOException | InvalidConfigurationException e) {
        e.printStackTrace();
        }
        this.registerCommands();
        this.registerListener();  
        
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        
        for (String server : cfg.getConfigurationSection("servers").getKeys(false)) {
        String host = cfg.getString("servers." + server + ".host");
        int port = cfg.getInt("servers." + server + ".port");
        String displayName = cfg.getString("servers." + server + ".displayname");
        int slot = cfg.getInt("servers." + server + ".slot");
        this.servers.put(server, new ServerInfo(server, host, port, displayName, slot));
        }

        Bukkit.getScheduler().runTaskTimerAsynchronously(Lobby.getInstance(), () -> {
       
        if (!this.reloading) {
        for (ServerInfo servers : this.servers.values()) {
        ServerPing ping = servers.getServerPing();
        ServerPing.DefaultResponse response;
      
        try {
        response = ping.fetchData();
        servers.setOnline(true);
        servers.setMotd(response.description);
        servers.setPlayerCount(response.getPlayers());
        servers.setMaxPlayers(response.getMaxPlayers());
       
         } catch (IOException ex) {
        servers.setOnline(false);
             }
         }
        
        for (Player players : Bukkit.getOnlinePlayers()) {
        	
        if (players.getOpenInventory().getTitle().equals(HexTransform.translateHexColorCodes(this.cfg.getString("inventory.title")))) {
        players.getOpenInventory().getTopInventory().clear();

        for (ServerInfo servers : this.servers.values()) {
        	
        if (servers.isOnline()) {
        if (servers.getServerName().equals(this.currentServer)) {
        String displayName = this.cfg.getString("layouts.current.displayname").replace("%server%", servers.getDisplayName());

        ArrayList<String> lore = new ArrayList<>();
        for (String string : this.cfg.getStringList("layouts.current.lore")) {
        	
        lore.add(HexTransform.translateHexColorCodes(string.replace("%players%", String.valueOf(servers.getPlayerCount())).replace("%max_players%", String.valueOf(servers.getMaxPlayers())) .replace("%motd%", servers.getMotd())));
       
        }
       
        ItemBuilder current = new ItemBuilder(Material.getMaterial(this.cfg.getString("layouts.current.material")),1, (byte) this.cfg.getInt("layouts.current.subid")).setDisplayName(HexTransform.translateHexColorCodes(displayName)).setLore(lore);
        
        if (this.cfg.getBoolean("layouts.current.glow")) 
        current.addGlowEffect();
        players.getOpenInventory().getTopInventory().setItem(servers.getSlot(), current);
       
        } else {
       
        String displayName = this.cfg.getString("layouts.online.displayname").replace("%server%", servers.getDisplayName());
        ArrayList<String> lore = new ArrayList<>();
        for (String string : this.cfg.getStringList("layouts.online.lore")) {
        lore.add(HexTransform.translateHexColorCodes(string.replace("%players%", String.valueOf(servers.getPlayerCount())) .replace("%max_players%", String.valueOf(servers.getMaxPlayers())) .replace("%motd%", servers.getMotd())));
         
        }
        
       ItemBuilder online = new ItemBuilder(Material.getMaterial(this.cfg.getString("layouts.online.material")), 1,(byte) this.cfg.getInt("layouts.online.subid")).setDisplayName(HexTransform.translateHexColorCodes(displayName)).setLore(lore);
     
       if (this.cfg.getBoolean("layouts.online.glow"))
      
      online.addGlowEffect();
      players.getOpenInventory().getTopInventory().setItem(servers.getSlot(), online);
        }
       
        } else {
        	
       String displayName = this.cfg.getString("layouts.offline.displayname").replace("%server%", servers.getDisplayName());
      ItemBuilder offline = new ItemBuilder(Material.getMaterial(this.cfg.getString("layouts.offline.material")), 1,(byte) this.cfg.getInt("layouts.offline.subid")).setDisplayName(HexTransform.translateHexColorCodes(displayName)).setLore(this.cfg.getStringList("layouts.offline.lore"));
      
      if (this.cfg.getBoolean("layouts.offline.glow"))
      offline.addGlowEffect();
      players.getOpenInventory().getTopInventory().setItem(servers.getSlot(), offline);
                            }
                        }
                    }
                }
            }
        }, 20, 20);
    }

    private void registerCommands() {
        Lobby.getInstance().getCommand("ch").setExecutor(new ChannelCommand());
        }

        private void registerListener() {
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), Lobby.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), Lobby.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), Lobby.getInstance());
        }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
    
    if (!channel.equals("BungeeCord")) {
     return;
     }
     ByteArrayDataInput in = ByteStreams.newDataInput(message);
     String subchannel = in.readUTF();
     if (subchannel.equals("GetServer")) {
    	 this.currentServer = in.readUTF();
        }
    }

    public void openGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, this.cfg.getInt("inventory.rows") * 9,  HexTransform.translateHexColorCodes(this.cfg.getString("inventory.title")));

        if (this.currentServer == null)
          Lobby.getInstance().getServer(player);

        for (ServerInfo servers : this.servers.values()) {

        if (servers.isOnline()) {
        	
         // CURRENT >
         if (servers.getServerName().equals(this.currentServer)) { String displayName = this.getString("layouts.current.displayname").replace("%server%", servers.getDisplayName());
         ArrayList<String> lore = new ArrayList<>();
         for (String string : this.cfg.getStringList("layouts.current.lore")) {
        	 
         lore.add(HexTransform.translateHexColorCodes(string.replace("%players%", String.valueOf(servers.getPlayerCount())).replace("%max_players%", String.valueOf(servers.getMaxPlayers())).replace("%motd%", servers.getMotd())));
             
         }
         
         ItemBuilder current = new ItemBuilder(Material.getMaterial(this.cfg.getString("layouts.current.material")),1, (byte) this.cfg.getInt("layouts.current.subid")).setDisplayName(displayName).setLore(lore);
        
         if (this.cfg.getBoolean("layouts.current.glow"))
         current.addGlowEffect();
         inventory.setItem(servers.getSlot(), current);
          // < CURRENT
         
         } else {
          // ONLINE >
        	 
          String displayName = this.getString("layouts.online.displayname").replace("%server%", servers.getDisplayName());
          ArrayList<String> lore = new ArrayList<>();
          for (String string : this.cfg.getStringList("layouts.online.lore")) {
          lore.add(HexTransform.translateHexColorCodes(string.replace("%players%", String.valueOf(servers.getPlayerCount())).replace("%max_players%", String.valueOf(servers.getMaxPlayers())).replace("%motd%", servers.getMotd())));
                    }
          ItemBuilder online = new ItemBuilder(Material.getMaterial(this.cfg.getString("layouts.online.material")), 1, (byte) this.cfg.getInt("layouts.online.subid")) .setDisplayName(displayName).setLore(lore);
           
          if (this.cfg.getBoolean("layouts.online.glow")) online.addGlowEffect();
          inventory.setItem(servers.getSlot(), online);
          
           // < ONLINE
                }
            } else {
          // OFFLINE >
           String displayName = this.getString("layouts.offline.displayname") .replace("%server%", servers.getDisplayName());
           ItemBuilder offline = new ItemBuilder( Material.getMaterial(this.cfg.getString("layouts.offline.material")), 1,(byte) this.cfg.getInt("layouts.offline.subid")) .setDisplayName(displayName).setLore(this.cfg.getStringList("layouts.offline.lore"));
          
           if (this.cfg.getBoolean("layouts.offline.glow"))
           offline.addGlowEffect();
           inventory.setItem(servers.getSlot(), offline);
                // < OFFLINE
            }

        }
        player.openInventory(inventory);
    }

    public void sendToServer(Player player, String serverName) {
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("Connect");
    out.writeUTF(serverName);
    player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
   // ChannelPacket packet = new ChannelPacket(player.getPlayer().getName(), player.getWorld().getName(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    
   // GuildPlugin.getPlugin().getRedisService().publishAsync("channel", packet);

    }

    public void getServer(Player player) {
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("GetServer");
    player.sendPluginMessage(this, "BungeeCord", out.toByteArray());


    }

    public boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public String getString(String path) {
        return HexTransform.translateHexColorCodes(this.cfg.getString(path));
    }

    public static Lobby getInstance() {
        return instance;
    }

}
