package pl.textr.configurations;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import org.bukkit.Material;
import java.util.Collections;
import java.util.List;
@Header("# BOXPVP-DENYBLOCK")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfiguration extends OkaeriConfig {
    @Comment("")
    @Comment("Uprawnienie odpowiedzialne za blokowanie interakcji z blokami")
    @CustomKey("permission")
    public String permissionBreak = "core.block";
    @Comment("Uprawnienie odpowiedzialne za blokowanie interakcji z blokami")
    @CustomKey("permission")
    public String permissionPlace = "core.block";
    @Comment("Lista światów, na których ma być zablokowane niszczenie i budowanie")
    @CustomKey("worlds")
    public List<String> Worlds = Collections.singletonList("world");
    @Comment("Wiadomość w przypadku zniszczenia niedozwolonego bloku")
    @CustomKey("block-break-messages")
    public String BlockBreakMessages = "&#FF0000Ta interakcja została wyłączona";
    @Comment("Wiadomość w przypadku postawienia niedozwolonego bloku")
    @CustomKey("block-place-messages")
    public String BlockPlaceMessages = "&#FF0000Ta interakcja została wyłączona";
    @Comment("Dozwolone bloki do niszczenia")
    @CustomKey("allowed-break-blocks")
    public List<Material> allowedBreakBlocks = Collections.singletonList(Material.COBWEB);
    @Comment("Dozwolone bloki do stawiania")
    @CustomKey("allowed-place-blocks")
    public List<Material> allowedPlaceBlocks = Collections.singletonList(Material.COBWEB);
}