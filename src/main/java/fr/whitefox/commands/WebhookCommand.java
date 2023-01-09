package fr.whitefox.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;

public class WebhookCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("webhooks")) {
            TextInput token = TextInput.create("token", "token", TextInputStyle.SHORT)
                    .setPlaceholder("Token du Webhooks")
                    .build();
            TextInput json = TextInput.create("json", "json", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Code JSON")
                    .build();

            Modal modal = Modal.create("info", "Informations du Webhooks")
                    .addActionRows(ActionRow.of(token))
                    .addActionRows(ActionRow.of(json))
                    .build();

            event.replyModal(modal).queue();
        }
    }
}
