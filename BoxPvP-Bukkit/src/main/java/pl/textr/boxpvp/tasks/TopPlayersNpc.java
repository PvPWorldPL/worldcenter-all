package pl.textr.boxpvp.tasks;

import java.math.BigDecimal;
import java.util.UUID;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import api.rankings.UserBalanceManager;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import api.rankings.UserDeathManager;
import api.rankings.UserKillManager;
import api.rankings.UserRankingManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.HologramTrait;
import net.citizensnpcs.trait.SkinTrait;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class TopPlayersNpc implements Runnable {

    private int topRanking1NPCId = 48; // NPC "top1" dla topki rankingu
    private int topRanking2NPCId = 49; // NPC "top2" dla topki rankingu
    private int topRanking3NPCId = 50; // NPC "top3" dla topki rankingu
	    
    private int topKill1NPCId = 51; // NPC "top1" dla topki zabójstw
    private int topKill2NPCId = 52; // NPC "top2" dla topki zabójstw
    private int topKill3NPCId = 53; // NPC "top3" dla topki zabójstw
    
    private int topDeath1NPCId = 54; // NPC "top1" dla topki śmierci
    private int topDeath2NPCId = 55; // NPC "top2" dla topki śmierci
    private int topDeath3NPCId = 56; // NPC "top3" dla topki śmierci


    private int topMonety1NPCId = 58; // NPC "top1" dla topki Monety
    private int topMonety2NPCId = 59; // NPC "top2" dla topki Monety
    private int topMonety3NPCId = 60; // NPC "top3" dla topki Monety


    @Override
    public void run() {
    	updateTopRankingNPCs();
        updateTopKillNPCs();
        updateTopDeathNPCs();
        updateTopMonetyNPCs();
    }
    
    


    private void updateTopRankingNPCs() {
        NPC topRanking1NPC = CitizensAPI.getNPCRegistry().getById(topRanking1NPCId);
        NPC topRanking2NPC = CitizensAPI.getNPCRegistry().getById(topRanking2NPCId);
        NPC topRanking3NPC = CitizensAPI.getNPCRegistry().getById(topRanking3NPCId);

      UserRankingManager.sortUserRankings();
        
        
        
        if (topRanking1NPC != null && UserRankingManager.getRankings().size() >= 1) {
            String playerName = UserRankingManager.getRankings().get(0).getName();
            int playerPoints = UserRankingManager.getRankings().get(0).getPoints();
            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserRankingManager.getPlaceUser(u);

            updateNPCTopRanking(topRanking1NPC, playerName, playerPoints, position);
            // Bukkit.getLogger().info("update npc topRanking1");
        } else if (topRanking1NPC != null) {
            brakgraczatop(topRanking1NPC, "topRanking1");
            // Bukkit.getLogger().info("npc topRanking1 (brak graczy)");
        }

        if (topRanking2NPC != null && UserRankingManager.getRankings().size() >= 2) {
            String playerName = UserRankingManager.getRankings().get(1).getName();
            int playerPoints = UserRankingManager.getRankings().get(1).getPoints();
            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserRankingManager.getPlaceUser(u);

            updateNPCTopRanking(topRanking2NPC, playerName, playerPoints, position);
            // Bukkit.getLogger().info("update npc topRanking2");
        } else if (topRanking2NPC != null) {
            brakgraczatop(topRanking2NPC, "topRanking2");
            // Bukkit.getLogger().info("npc topRanking2 (brak graczy)");
        }

        if (topRanking3NPC != null && UserRankingManager.getRankings().size() >= 3) {
            String playerName = UserRankingManager.getRankings().get(2).getName();
            int playerPoints = UserRankingManager.getRankings().get(2).getPoints();
            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserRankingManager.getPlaceUser(u);

            updateNPCTopRanking(topRanking3NPC, playerName, playerPoints, position);
            //Bukkit.getLogger().info("update npc topRanking3");
        } else if (topRanking3NPC != null) {
            brakgraczatop(topRanking3NPC, "topRanking3");
          //  Bukkit.getLogger().info("npc topRanking3 (brak graczy)");
        }
    }

    private void updateTopKillNPCs() {
        NPC topKill1NPC = CitizensAPI.getNPCRegistry().getById(topKill1NPCId);
        NPC topKill2NPC = CitizensAPI.getNPCRegistry().getById(topKill2NPCId);
        NPC topKill3NPC = CitizensAPI.getNPCRegistry().getById(topKill3NPCId);

      UserKillManager.sortUserKills();
        
        
        if (topKill1NPC != null && UserKillManager.getKills().size() >= 1) {
            String playerName = UserKillManager.getKills().get(0).getName();
            int playerKills = UserKillManager.getKills().get(0).getKills();
            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserKillManager.getPlaceUser(u);
            updateNPCTopKill(topKill1NPC, playerName, playerKills, position);
         //   Bukkit.getLogger().info("update npc topKill1");
        } else if (topKill1NPC != null) {
        	brakgraczatop(topKill1NPC, "topKill1");
         //   Bukkit.getLogger().info("npc topKill1 (brak graczy)");
        }

        if (topKill2NPC != null && UserKillManager.getKills().size() >= 2) {
            String playerName = UserKillManager.getKills().get(1).getName();
            int playerKills = UserKillManager.getKills().get(1).getKills();
            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserKillManager.getPlaceUser(u);
            updateNPCTopKill(topKill2NPC, playerName, playerKills, position);
       //     Bukkit.getLogger().info("update npc topKill2");
        } else if (topKill2NPC != null) {
        	brakgraczatop(topKill2NPC, "topKill2");

         //   Bukkit.getLogger().info("npc topKill2 (brak graczy)");
        }

        if (topKill3NPC != null && UserKillManager.getKills().size() >= 3) {
            String playerName = UserKillManager.getKills().get(2).getName();
            int playerKills = UserKillManager.getKills().get(2).getKills();
            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserKillManager.getPlaceUser(u);

            updateNPCTopKill(topKill3NPC, playerName, playerKills, position);
       //     Bukkit.getLogger().info("update npc topKill3");
        } else if (topKill3NPC != null) {
        	brakgraczatop(topKill3NPC, "topKill3");

           // Bukkit.getLogger().info("npc topKill3 (brak graczy)");
        }
    }

    private void updateTopDeathNPCs() {
        NPC topDeath1NPC = CitizensAPI.getNPCRegistry().getById(topDeath1NPCId);
        NPC topDeath2NPC = CitizensAPI.getNPCRegistry().getById(topDeath2NPCId);
        NPC topDeath3NPC = CitizensAPI.getNPCRegistry().getById(topDeath3NPCId);

  UserDeathManager.sortUserDeaths();
        
        if (topDeath1NPC != null && UserDeathManager.getDeaths().size() >= 1) {
            String playerName = String.valueOf(UserDeathManager.getDeaths().get(0).getName());
            int playerDeaths = UserDeathManager.getDeaths().get(0).getDeaths();
            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserDeathManager.getPlaceUser(u);
            updateNPCTopDeath(topDeath1NPC, playerName, playerDeaths, position);
           // Bukkit.getLogger().info("update npc topDeath1");
        } else if (topDeath1NPC != null) {
            brakgraczatop(topDeath1NPC, "topDeath1");
           // Bukkit.getLogger().info("npc topDeath1 (brak graczy)");
        }

        if (topDeath2NPC != null && UserDeathManager.getDeaths().size() >= 2) {
            String playerName = String.valueOf(UserDeathManager.getDeaths().get(1).getName());
            int playerDeaths = UserDeathManager.getDeaths().get(1).getDeaths();
            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserDeathManager.getPlaceUser(u);
            updateNPCTopDeath(topDeath2NPC, playerName, playerDeaths, position);
        	
      	    // Bukkit.getLogger().info("update npc topDeath2");
  		
        } else if (topDeath2NPC != null) {
            brakgraczatop(topDeath2NPC, "topDeath2");
         //   Bukkit.getLogger().info("npc topDeath2 (brak graczy)");
        }

        if (topDeath3NPC != null && UserDeathManager.getDeaths().size() >= 3) {
            String playerName = String.valueOf(UserDeathManager.getDeaths().get(2).getName());
            int playerDeaths = UserDeathManager.getDeaths().get(2).getDeaths();

            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserDeathManager.getPlaceUser(u);

            updateNPCTopDeath(topDeath3NPC, playerName, playerDeaths, position);    
        	   //  Bukkit.getLogger().info("update npc topDeath3");    	     
        } else if (topDeath3NPC != null) {
            brakgraczatop(topDeath3NPC, "topDeath3");
          //  Bukkit.getLogger().info("npc topDeath3 (brak graczy)");
        }
    }

    private void updateTopMonetyNPCs() {
        NPC topMonety1NPC = CitizensAPI.getNPCRegistry().getById(topMonety1NPCId);
        NPC topMonety2NPC = CitizensAPI.getNPCRegistry().getById(topMonety2NPCId);
        NPC topMonety3NPC = CitizensAPI.getNPCRegistry().getById(topMonety3NPCId);

        UserBalanceManager.sortUserBalance();

        if (topMonety1NPC != null && UserBalanceManager.getBalance().size() >= 1) {
            String playerName = UserBalanceManager.getBalance().get(0).getName();
            String playerMonety = ChatUtil.formatAmount(UserBalanceManager.getBalance().get(0).getBalance());

            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserDeathManager.getPlaceUser(u);
            updateNPCTopMonety(topMonety1NPC, playerName, playerMonety, position);
            // Bukkit.getLogger().info("update npc topMonety1");
        } else if (topMonety1NPC != null) {
            brakgraczatop(topMonety1NPC, "topMonety1");
            // Bukkit.getLogger().info("npc topMonety1 (brak graczy)");
        }

        if (topMonety2NPC != null && UserDeathManager.getDeaths().size() >= 2) {
            String playerName = UserBalanceManager.getBalance().get(1).getName();
            String playerMonety = ChatUtil.formatAmount(UserBalanceManager.getBalance().get(1).getBalance());

            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserDeathManager.getPlaceUser(u);
            updateNPCTopMonety(topMonety2NPC, playerName, playerMonety, position);

            // Bukkit.getLogger().info("update npc topMonety2");

        } else if (topMonety2NPC != null) {
            brakgraczatop(topMonety2NPC, "topMonety2");
            //   Bukkit.getLogger().info("npc topMonety2 (brak graczy)");
        }

        if (topMonety3NPC != null && UserDeathManager.getDeaths().size() >= 3) {
            String playerName = UserBalanceManager.getBalance().get(2).getName();
            String playerMonety = ChatUtil.formatAmount(UserBalanceManager.getBalance().get(2).getBalance());

            final UserProfile u = UserAccountManager.getUser(playerName);
            int position = UserDeathManager.getPlaceUser(u);

            updateNPCTopMonety(topMonety3NPC, playerName, playerMonety, position);
            //  Bukkit.getLogger().info("update npc topMonety3");
        } else if (topMonety3NPC != null) {
            brakgraczatop(topMonety3NPC, "topMonety3");
            //  Bukkit.getLogger().info("npc topMonety3 (brak graczy)");
        }
    }

    private void updateNPCTopRanking(NPC npc, String playerName, int playerPoints, int position) {
        SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
        skinTrait.setSkinName(playerName);
        HologramTrait hologramTrait = npc.getTraitNullable(HologramTrait.class);
        if (hologramTrait == null) {
            hologramTrait = new HologramTrait();
            npc.addTrait(hologramTrait);
        }
        updateHologramTopRanking(hologramTrait, playerName, playerPoints, position);
      //  Bukkit.getLogger().info("updateTopRanking");
    }

    private void updateNPCTopKill(NPC npc, String playerName, int playerKills, int position) {
        SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
        skinTrait.setSkinName(playerName);
        HologramTrait hologramTrait = npc.getTraitNullable(HologramTrait.class);
        if (hologramTrait == null) {
            hologramTrait = new HologramTrait();
            npc.addTrait(hologramTrait);
        }
        updateHologramTopKill(hologramTrait, playerName, playerKills, position);
      //  Bukkit.getLogger().info("updateKill");
    }

    private void updateNPCTopDeath(NPC npc, String playerName, int playerDeaths, int position) {
        SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
        skinTrait.setSkinName(playerName);
        HologramTrait hologramTrait = npc.getTraitNullable(HologramTrait.class);
        if (hologramTrait == null) {
            hologramTrait = new HologramTrait();
            npc.addTrait(hologramTrait);
        }
        updateHologramTopDeath(hologramTrait, playerName, playerDeaths , position);
     //   Bukkit.getLogger().info("updateDeath");
    }

    private void updateNPCTopMonety(NPC npc, String playerName, String playerMonety, int position) {
        SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
        skinTrait.setSkinName(playerName);
        HologramTrait hologramTrait = npc.getTraitNullable(HologramTrait.class);
        if (hologramTrait == null) {
            hologramTrait = new HologramTrait();
            npc.addTrait(hologramTrait);
        }
        updateHologramTopMonety(hologramTrait, playerName, playerMonety , position);
        //   Bukkit.getLogger().info("updateDeath");
    }

    private void updateHologramTopRanking(HologramTrait hologramTrait, String playerName, int playerPoints, int position) {
        hologramTrait.clear();
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&f" + playerPoints + "&8)"));
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#F59E58" + playerName));
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#FF7200&lT&#FF7200&lO&#FF7200&lP&#FF7200&lK&#FF7200&lA &#FF7200&lR&#FF7200&lA&#FF7200&lN&#FF7200&lK&#FF7200&lI&#FF7200&lN&#FF7200&lG&#FF7200&lU &8&l◀"));    
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#FF9100M&#FF9100i&#FF9100e&#FF9100j&#FF9100s&#FF9100c&#FF9100e &f" + (position == 1 ? "➊" : position == 2 ? "➋" : position == 3 ? "➌" : position) + "&8)"));
    }
     

    private void updateHologramTopKill(HologramTrait hologramTrait, String playerName, int playerKills, int position) {
        hologramTrait.clear();
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&f" + playerKills + "&8)"));
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#E63239" + playerName));
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#F01F27&lT&#F01F27&lO&#F01F27&lP&#F01F27&lK&#F01F27&lA &#F01F27&lZ&#F01F27&lA&#F01F27&lB&#F01F27&lO&#F01F27&lJ&#F01F27&lS&#F01F27&lT&#F01F27&lW &8&l◀"));    
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#E63239M&#E63239i&#E63239e&#E63239j&#E63239s&#E63239c&#E63239e &f" + (position == 1 ? "➊" : position == 2 ? "➋" : position == 3 ? "➌" : position) + "&8)"));
    }

    private void updateHologramTopDeath(HologramTrait hologramTrait, String playerName, int playerDeaths, int position) {
        hologramTrait.clear();
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&f" + playerDeaths + "&8)"));
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF66FB" + playerName));
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#BE00FF&lT&#BE00FF&lO&#BE00FF&lP&#BE00FF&lK&#BE00FF&lA &#BE00FF&lS&#BE00FF&lM&#BE00FF&lI&#BE00FF&lE&#BE00FF&lR&#BE00FF&lC&#BE00FF&lI &8&l◀"));  
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#FF66FBM&#FF66FBi&#FF66FBe&#FF66FBj&#FF66FBs&#FF66FBc&#FF66FBe &f" + (position == 1 ? "➊" : position == 2 ? "➋" : position == 3 ? "➌" : position) + "&8)"));

    }
    
    private void updateHologramTopMonety(HologramTrait hologramTrait, String playerName, String playerBalance, int position) {
        hologramTrait.clear();
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&f" + playerBalance + "&8)"));
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#00FF1C" + playerName));
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#18A828&lT&#18A828&lO&#18A828&lP&#18A828&lK&#18A828&lA &#18A828&lM&#18A828&lO&#18A828&lN&#18A828&lE&#18A828&lT &8&l◀"));  
        hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#00FF1CM&#00FF1Ci&#00FF1Ce&#00FF1Cj&#00FF1Cs&#00FF1Cc&#00FF1Ce &f" + (position == 1 ? "➊" : position == 2 ? "➋" : position == 3 ? "➌" : position) + "&8)"));

    }

    private void brakgraczatop(NPC npc, String category) {
    
        HologramTrait hologramTrait = npc.getTraitNullable(HologramTrait.class);
        if (hologramTrait == null) {
            hologramTrait = new HologramTrait();
            npc.addTrait(hologramTrait);
        
        }
        hologramTrait.clear();
     
        switch (category) {
            case "topKill1":
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#F01F27&lT&#F01F27&lO&#F01F27&lP&#F01F27&lK&#F01F27&lA &#F01F27&lZ&#F01F27&lA&#F01F27&lB&#F01F27&lO&#F01F27&lJ&#F01F27&lS&#F01F27&lT&#F01F27&lW &8&l◀"));    
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#E63239M&#E63239i&#E63239e&#E63239j&#E63239s&#E63239c&#E63239e &f➊&8)"));
                break;
            case "topKill2":
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
            	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#F01F27&lT&#F01F27&lO&#F01F27&lP&#F01F27&lK&#F01F27&lA &#F01F27&lZ&#F01F27&lA&#F01F27&lB&#F01F27&lO&#F01F27&lJ&#F01F27&lS&#F01F27&lT&#F01F27&lW &8&l◀"));    
            	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#E63239M&#E63239i&#E63239e&#E63239j&#E63239s&#E63239c&#E63239e &f➋&8)"));
                break;
            case "topKill3":
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
            	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#F01F27&lT&#F01F27&lO&#F01F27&lP&#F01F27&lK&#F01F27&lA &#F01F27&lZ&#F01F27&lA&#F01F27&lB&#F01F27&lO&#F01F27&lJ&#F01F27&lS&#F01F27&lT&#F01F27&lW &8&l◀"));    
            	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#E63239M&#E63239i&#E63239e&#E63239j&#E63239s&#E63239c&#E63239e &f➌&8)"));
                break;
            case "topDeath1":
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#BE00FF&lT&#BE00FF&lO&#BE00FF&lP&#BE00FF&lK&#BE00FF&lA &#BE00FF&lS&#BE00FF&lM&#BE00FF&lI&#BE00FF&lE&#BE00FF&lR&#BE00FF&lC&#BE00FF&lI &8&l◀"));    
                
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#FF66FBM&#FF66FBi&#FF66FBe&#FF66FBj&#FF66FBs&#FF66FBc&#FF66FBe &f➊&8)"));
                break;
            case "topDeath2":
            	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#BE00FF&lT&#BE00FF&lO&#BE00FF&lP&#BE00FF&lK&#BE00FF&lA &#BE00FF&lS&#BE00FF&lM&#BE00FF&lI&#BE00FF&lE&#BE00FF&lR&#BE00FF&lC&#BE00FF&lI &8&l◀"));        
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#FF66FBM&#FF66FBi&#FF66FBe&#FF66FBj&#FF66FBs&#FF66FBc&#FF66FBe &f➋&8)"));
                break;
            case "topDeath3":
            	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#BE00FF&lT&#BE00FF&lO&#BE00FF&lP&#BE00FF&lK&#BE00FF&lA &#BE00FF&lS&#BE00FF&lM&#BE00FF&lI&#BE00FF&lE&#BE00FF&lR&#BE00FF&lC&#BE00FF&lI &8&l◀"));        
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#FF66FBM&#FF66FBi&#FF66FBe&#FF66FBj&#FF66FBs&#FF66FBc&#FF66FBe &f➌&8)"));
                break;
            case "topRanking1":
             	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
            	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#FF7200&lT&#FF7200&lO&#FF7200&lP&#FF7200&lK&#FF7200&lA &#FF7200&lR&#FF7200&lA&#FF7200&lN&#FF7200&lK&#FF7200&lI&#FF7200&lN&#FF7200&lG&#FF7200&lU &8&l◀"));    
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#FF9100M&#FF9100i&#FF9100e&#FF9100j&#FF9100s&#FF9100c&#FF9100e &f➊&8)"));
                break;
            case "topRanking2":
             	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
            	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#FF7200&lT&#FF7200&lO&#FF7200&lP&#FF7200&lK&#FF7200&lA &#FF7200&lR&#FF7200&lA&#FF7200&lN&#FF7200&lK&#FF7200&lI&#FF7200&lN&#FF7200&lG&#FF7200&lU &8&l◀"));    
           	    hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#FF9100M&#FF9100i&#FF9100e&#FF9100j&#FF9100s&#FF9100c&#FF9100e &f➋&8)"));
                break;
            case "topRanking3":
             	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
            	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#FF7200&lT&#FF7200&lO&#FF7200&lP&#FF7200&lK&#FF7200&lA &#FF7200&lR&#FF7200&lA&#FF7200&lN&#FF7200&lK&#FF7200&lI&#FF7200&lN&#FF7200&lG&#FF7200&lU &8&l◀"));    
             	hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#FF9100M&#FF9100i&#FF9100e&#FF9100j&#FF9100s&#FF9100c&#FF9100e &f➌&8)"));
                break;
            case "topMonety1":
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#18A828&lT&#18A828&lO&#18A828&lP&#18A828&lK&#18A828&lA &#18A828&lM&#18A828&lO&#18A828&lN&#18A828&lE&#18A828&lT &8&l◀"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#00FF1CM&#00FF1Ci&#00FF1Ce&#00FF1Cj&#00FF1Cs&#00FF1Cc&#00FF1Ce &f➊&8)"));
                break;
            case "topMonety2":
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#18A828&lT&#18A828&lO&#18A828&lP&#18A828&lK&#18A828&lA &#18A828&lM&#18A828&lO&#18A828&lN&#18A828&lE&#18A828&lT &8&l◀"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#00FF1CM&#00FF1Ci&#00FF1Ce&#00FF1Cj&#00FF1Cs&#00FF1Cc&#00FF1Ce &f➋&8)"));
                break;
            case "topMonety3":
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&#FF0000&lB&#FF0000&lR&#FF0000&lA&#FF0000&lK"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8&l▶ &#18A828&lT&#18A828&lO&#18A828&lP&#18A828&lK&#18A828&lA &#18A828&lM&#18A828&lO&#18A828&lN&#18A828&lE&#18A828&lT &8&l◀"));
                hologramTrait.addLine(ChatUtil.translateHexColorCodes("&8(&#00FF1CM&#00FF1Ci&#00FF1Ce&#00FF1Cj&#00FF1Cs&#00FF1Cc&#00FF1Ce &f➌&8)"));
                break;
        }
    }
}


