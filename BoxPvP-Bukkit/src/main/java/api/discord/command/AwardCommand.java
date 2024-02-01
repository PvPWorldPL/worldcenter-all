package api.discord.command;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import api.discord.command.api.CommandBase;
import api.discord.command.api.CommandDiscord;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import pl.textr.boxpvp.Main;

public class AwardCommand implements CommandDiscord
{
    @Override
    public CommandBase getInfo() {
        return new CommandBase("odbierz");
    }

    @Override
    public void execute(final MessageReceivedEvent event, final String... args) {
        if (args.length < 2) {
            return;
        }

        final EmbedBuilder offlinePlayer = new EmbedBuilder();
        offlinePlayer.setTitle("WORLDCENTER.PL - NAGRODY (BOXPVP)");
        offlinePlayer.setColor(Color.RED);
        offlinePlayer.addField("BŁĄD! :interrobang: Podany gracz ``" + args[1] + "`` jest offline", "Żeby odebrać nagrodę musisz być online na boxpvp_1", false);      
        offlinePlayer.setFooter("Administracja WORLDCENTER pozdrawia!");
        offlinePlayer.setThumbnail("https://cravatar.eu/helmhead/" + args[1] + "/190.png");

        final EmbedBuilder wrongChannel = new EmbedBuilder();
        wrongChannel.setTitle("WORLDCENTER.PL - NAGRODY (BOXPVP)");
        wrongChannel.setColor(Color.RED);
        wrongChannel.addField("BŁĄD! :interrobang: Błędny kanał do odebrania nagrody", "Prawidłowy kanał to <#1165651577701412984>!", false);
        wrongChannel.setFooter("Administracja WORLDCENTER pozdrawia!");

        
        final EmbedBuilder awardedBefore = new EmbedBuilder();
        awardedBefore.setTitle("WORLDCENTER.PL - NAGRODY (BOXPVP)");
        awardedBefore.setColor(Color.RED);
        awardedBefore.addField("BŁĄD! :interrobang: Odebrałeś już nagrodę !", "nie możesz jej odebrać drugi raz!", false);  
        awardedBefore.setFooter("Administracja WORLDCENTER pozdrawia!");
        awardedBefore.setThumbnail("https://cravatar.eu/helmhead/" + args[1] + "/190.png");

        
     
        final EmbedBuilder awardedPlayer = new EmbedBuilder();
        awardedPlayer.setTitle("WORLDCENTER.PL - NAGRODY (BOXPVP)");
        awardedPlayer.setColor(Color.GREEN);
        awardedPlayer.addField("POMYŚLNIE ODEBRANO! ✅", "Podany nick ``" + args[1] + "`` pomyślnie odebrał nagrodę!", false);
        awardedPlayer.setFooter("Administracja WORLDCENTER pozdrawia!");
        awardedPlayer.setThumbnail("https://cravatar.eu/helmhead/" + args[1] + "/190.png");

        
        
        
        if (!event.getChannel().getId().equals("1165651577701412984")) {
            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(wrongChannel.build()).queue();
            });
            return; // Dodaj to, aby przerwać wykonanie kodu po wysłaniu komunikatu
        }

        if (args.length == 1) {
            return; 
        }

        final Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(offlinePlayer.build()).queue();
                return; // Dodaj to, aby przerwać wykonanie kodu po wysłaniu komunikatu
            });
            return; // Dodaj to, aby przerwać wykonanie kodu po sprawdzeniu gracza
        }


        
        String authorId = event.getAuthor().getId();

        if (!Main.getPlugin().getDiscordConfiguration().getUsers().containsKey(player.getName()) && !Main.getPlugin().getDiscordConfiguration().getUsers().containsKey(authorId)) {
            Map<String, String> userData = new HashMap<>();
            userData.put("discordId", event.getAuthor().getId());
            userData.put("nick", player.getName());
            Main.getPlugin().getDiscordConfiguration().getUsers().put(player.getName(), userData);
            Main.getPlugin().getDiscordConfiguration().save();
            synchronized (this) {
                new BukkitRunnable() {
                    public void run() {
                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "key give " + player.getName() + " epicka 5");
                    }
                }.runTask(Main.getPlugin());
            }
            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(awardedPlayer.build()).queue();
                return; // Dodaj to, aby przerwać wykonanie kodu po wysłaniu wiadomości
            });
        } else {
            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(awardedBefore.build()).queue();
                return; // Dodaj to, aby przerwać wykonanie kodu po wysłaniu wiadomości
            });
        }
    }
}
    

