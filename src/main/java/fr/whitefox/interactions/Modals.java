package fr.whitefox.interactions;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;

public class Modals extends ListenerAdapter {

    private String json;
    private String token;

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals("info")) {
            token = event.getValue("token").getAsString();
            json = event.getValue("json").getAsString();

            event.reply("Souhaitez-vous vraiment envoyer ce Webhook ?")
                    .addActionRow(
                            Button.success("yes", "Oui"),
                            Button.danger("no", "Non"))
                    .queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("no")) {
            event.getChannel().sendMessage("<a:non:945329395256659968> Envoi annulé !").queue();
            event.getInteraction().getMessage().delete().queue();
        }
        if (event.getComponentId().equals("yes")) {
            event.getChannel().sendMessage("<a:valide:945329415833927700> Webhook envoyé !").queue();
            event.getInteraction().getMessage().delete().queue();
            sendWebhooks(token, json);
        }
    }

    public static void sendWebhooks(String token, String json) {
        try {
            URL url = new URL(token);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "WebhooksSender");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(json.getBytes());
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}