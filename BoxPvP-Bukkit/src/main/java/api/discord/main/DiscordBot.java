package api.discord.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;

import api.discord.command.api.CommandDiscordManager;
import api.discord.listener.MessageReceivedHandler;

public class DiscordBot {
    private static JDA jda;
    private CommandDiscordManager commandManager;

    public DiscordBot(final String token) {
        this.commandManager = new CommandDiscordManager();
        DiscordBot.jda = this.createUser(token);
        this.commandManager.loadCommands();
        new MessageReceivedHandler(this);
     
    }

    public static JDA getBot() {
        return DiscordBot.jda;
    }

    private JDA createUser(final String token) {
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
     
        try {
            return jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void shutdown() {
        if (jda != null) {
            jda.shutdownNow(); // Zamknięcie bota Discord
            
        }
    }

    public CommandDiscordManager getCommandManager() {
        return this.commandManager;
    }

    public JDA getJda() {
        return DiscordBot.jda;
    }
}
