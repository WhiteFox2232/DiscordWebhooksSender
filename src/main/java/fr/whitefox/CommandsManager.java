package fr.whitefox;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class CommandsManager {

    private static final JDA jda = Main.getJDAInstance();

    public static void deploySlashCommands() {
        jda.updateCommands().addCommands(
                Commands.slash("webhooks", "Envoyez un Webhook"),
                Commands.slash("bot", "Obtenez les informations principales du bot")
        ).queue();
    }
}