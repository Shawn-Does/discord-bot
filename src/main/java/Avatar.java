import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class Avatar extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().startsWith("!avatar")) {
            List<Member> mentionedMembers = e.getMessage().getMentionedMembers();
            if (mentionedMembers.isEmpty()) {
                e.getChannel().sendMessage(createEmbed(e.getAuthor())).queue();
            }
            else {
                e.getChannel().sendMessage(createEmbed(mentionedMembers.get(0).getUser())).queue();
            }
        }
    }

    public MessageEmbed createEmbed(User user) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setImage(user.getAvatarUrl());
        embedBuilder.setTitle(user.getName(), user.getAvatarUrl());
        return embedBuilder.build();
    }

}
