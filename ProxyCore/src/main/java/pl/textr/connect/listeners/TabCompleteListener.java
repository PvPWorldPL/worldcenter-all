package pl.textr.connect.listeners;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.*;
import pl.textr.connect.AuthPlugin;
import pl.textr.connect.lang.*;
import net.md_5.bungee.event.*;

public class TabCompleteListener implements Listener
{
    @EventHandler

    public void onTabComplete(TabCompleteEvent ev) {
      String partialPlayerName = ev.getCursor().toLowerCase();
      int lastSpaceIndex = partialPlayerName.lastIndexOf(' ');
      if (lastSpaceIndex >= 0)
        partialPlayerName = partialPlayerName.substring(lastSpaceIndex + 1); 

          for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers()) {
        if (p.getName().toLowerCase().startsWith(partialPlayerName))
          ev.getSuggestions().add(p.getName()); 
      } 
    }
}