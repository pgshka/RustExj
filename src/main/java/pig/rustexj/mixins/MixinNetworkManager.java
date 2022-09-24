package pig.rustexj.mixins;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin( NetworkManager.class )
public class MixinNetworkManager {


    @Shadow private INetHandler packetListener;

    @Inject( method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At( "HEAD" ), cancellable = true )
    public void onPacketSend( final Packet<?> packet, final CallbackInfo ci ) {
        if ( Minecraft.getMinecraft().player == null && Minecraft.getMinecraft().world == null ) return;
        if (packet instanceof FMLProxyPacket && !Minecraft.getMinecraft().isSingleplayer()) ci.cancel();
        if (packet instanceof CPacketCustomPayload) {
            final CPacketCustomPayload payload = (CPacketCustomPayload) packet;
            if (payload.getChannelName().equals("MC|Brand")) {
                ((AccessorCPacketCustomPayload) payload).setData(new PacketBuffer(Unpooled.buffer()).writeString("vanilla"));
            }
        }
    }
}