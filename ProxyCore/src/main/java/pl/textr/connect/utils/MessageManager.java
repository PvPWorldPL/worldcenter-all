package pl.textr.connect.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessageManager {
    private final Map<UUID, UUID> replyMap = new HashMap<>();

    public void setReplyTarget(UUID senderId, UUID targetId) {
        replyMap.put(senderId, targetId);
    }

    public UUID getReplyTarget(UUID senderId) {
        return replyMap.get(senderId);
    }

    public void clearReplyTarget(UUID senderId) {
        replyMap.remove(senderId);
    }
}
