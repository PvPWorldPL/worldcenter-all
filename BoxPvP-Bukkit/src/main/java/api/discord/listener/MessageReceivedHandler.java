// 
// Decompiled by Procyon v0.5.36
// 

package api.discord.listener;

import api.discord.command.api.CommandDiscord;
import api.discord.main.DiscordBot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedHandler extends ListenerAdapter
{
    private final DiscordBot bot;
    
    public MessageReceivedHandler(final DiscordBot bot) {
        this.bot = bot;
        this.bot.getJda().addEventListener(new Object[] { this });
    }
    
    public void onMessageReceived(final MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentDisplay().split(" ");
        if (event.getChannel().getId().equals("1165651577701412984")) {
        if (!args[0].startsWith("!")) {
        	if (!event.getMessage().getAuthor().isBot()) {
        	event.getMessage().delete().queue();
        	return;
        }  	
   }
}
        if (event.getChannel().getId().equals("1165651577701412984")) {
    	if (args.length < 0) {
    		if (!event.getMessage().getAuthor().isBot()) {
    		event.getMessage().delete().queue();
    		return;
    	}     

    	}}
    	
    	if (args.length > 0 && args[0].startsWith("!")) {
    	    String[] splitArgs = args[0].split("!");
    	    if (splitArgs.length > 1) {
    	        String commandName = splitArgs[1]; // Pobranie nazwy komendy
    	        final CommandDiscord command = this.bot.getCommandManager().getCommand(commandName);
    	        if (command != null) {
    	            command.execute(event, args);
    	            if (event.getChannel().getId().equals("1165651577701412984")) {
    	                event.getMessage().delete().queue();
    	            }
    	        }
        	    if (command == null) {
        	    	event.getMessage().delete().queue();
        	    	return;
        	    }
    	    }
    	}
    }
}
