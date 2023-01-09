package fr.whitefox;

import fr.whitefox.commands.BotCommand;
import fr.whitefox.commands.WebhookCommand;
import fr.whitefox.event.Ready;
import fr.whitefox.interactions.Modals;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class JDAInstance {
    public static JDA createJDAInstance(String token) {
        return JDABuilder.createDefault(token)
                .setActivity(Activity.playing("Webhooks sender !"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .addEventListeners(new BotCommand(), new Modals(), new WebhookCommand(), new Ready())
                .build();
    }
}