package pig.rustexj;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Loader.MODID, name = Loader.NAME, version = Loader.VERSION)
public class Loader
{
    public static final String MODID = "rust";
    public static final String NAME = "exj";
    public static final String VERSION = "1.0";
    @EventHandler
    public void init(FMLInitializationEvent event) {}
}
