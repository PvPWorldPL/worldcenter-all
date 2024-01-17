package api.managers;

import org.bukkit.entity.Player;

import api.data.UserProfile;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import java.util.stream.Collectors;

import java.util.*;

public class KillContractManager {
    private final Map<UUID, ContractData> contracts = new HashMap<>();

    public void addContract(UUID zleceniodawca, UUID cel) {
        contracts.put(cel, new ContractData(zleceniodawca, System.currentTimeMillis()));
    }

    public boolean handleKill(Player killer, Player zleceniodawca) {
        ContractData contractData = contracts.get(zleceniodawca.getUniqueId());
        if (contractData != null && contractData.zleceniodawca.equals(zleceniodawca.getUniqueId()) && contractData.isExpired()) {
            final double amount = 65.0;
            final UserProfile userKiller = UserAccountManager.getUser(killer);

            contracts.remove(zleceniodawca.getUniqueId());

            // Sprawdzenie czy killer i zleceniodawca to różni gracze
            if (!killer.getUniqueId().equals(zleceniodawca.getUniqueId())) {
                userKiller.addBalance(amount);
                killer.sendMessage(ChatUtil.translateHexColorCodes("&aZdobywasz nagrodę za wykonane zlecenie!"));
            } else {
                UserProfile userZleceniodawca = UserAccountManager.getUser(zleceniodawca);
                if (userZleceniodawca != null) {
                    userZleceniodawca.setKills(userZleceniodawca.getKills() - 1);
                    userZleceniodawca.save();
                }
            }

            removeExpiredContracts();
        }
        return false;
    }



    public boolean hasContracts(UUID zleceniodawca) {
        return contracts.values().stream()
                .anyMatch(contract -> contract.zleceniodawca.equals(zleceniodawca) && contract.isExpired());
    }

    public void removeExpiredContracts() {
        long currentTime = System.currentTimeMillis();
        contracts.entrySet().removeIf(entry -> {
            if (entry.getValue().isExpired(currentTime)) {
                Player killer = Main.getPlugin().getServer().getPlayer(entry.getKey());
                if (killer != null) {
                    killer.sendMessage(ChatUtil.translateHexColorCodes("&cNie wykonałeś zlecenia na czas, zlecenie jest przedawnione."));
                }
                return true;
            }
            return false;
        });
    }

    public List<String> getActiveContractsForPlayer(Player zleceniodawca) {
        List<String> activeContracts = new ArrayList<>();
        UUID zleceniodawcaUUID = zleceniodawca.getUniqueId();

        for (Map.Entry<UUID, ContractData> entry : contracts.entrySet()) {
            if (entry.getValue().zleceniodawca.equals(zleceniodawcaUUID) && entry.getValue().isExpired()) {
                Player cel = Main.getPlugin().getServer().getPlayer(entry.getKey());
                if (cel != null) {
                    activeContracts.add(cel.getName());
                }
            }
        }

        return activeContracts;
    }

    private static class ContractData {
        public UUID zleceniodawca;
        public long timestamp;

        public ContractData(UUID zleceniodawca, long timestamp) {
            this.zleceniodawca = zleceniodawca;
            this.timestamp = timestamp;
        }

        private boolean isExpired() {
            return !isExpired(System.currentTimeMillis());
        }

        public boolean isExpired(long currentTime) {
            return currentTime - timestamp > 600000; // 600000 ms (10 minutes)
        }
    }
}
