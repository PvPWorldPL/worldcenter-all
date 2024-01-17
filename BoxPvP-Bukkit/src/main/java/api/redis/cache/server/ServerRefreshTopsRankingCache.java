package api.redis.cache.server;

import api.placeholders.PlayerTabInfoUtil;
import api.redis.BroadcastType;
import api.redis.PacketListener;
import api.redis.packet.player.ServerRefreshTopsRanking;
import pl.textr.boxpvp.tasks.TabCategoryTask;

public class ServerRefreshTopsRankingCache implements PacketListener<ServerRefreshTopsRanking> {

    private TabCategoryTask tabCategoryTask;
    @Override
    public void handle(ServerRefreshTopsRanking packet) {
        BroadcastType broadcastType = packet.getType();       
        if (broadcastType == BroadcastType.TAB_UPDATE) {
            if (tabCategoryTask != null) {
            	tabCategoryTask.cancel();
            	tabCategoryTask.resetTask();
            }
    	PlayerTabInfoUtil.toggleCategory();

    	return;

    }
        if (broadcastType == BroadcastType.TAB_SYNC) {
        
            if (tabCategoryTask != null) {
            	tabCategoryTask.cancel();
            	tabCategoryTask.resetTask();
            }
        	PlayerTabInfoUtil.setCategory(1);

        }
    }
    
}