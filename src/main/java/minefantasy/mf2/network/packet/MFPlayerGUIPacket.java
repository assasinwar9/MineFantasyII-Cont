package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;
import minefantasy.mf2.MineFantasyII;
import net.minecraft.entity.player.EntityPlayer;

public class MFPlayerGUIPacket extends PacketMF {
    public static final String packetName = "MF2_PlayerGUIPacket";

	@Override
	public String getChannel() {
		return packetName;
	}

	@Override
	public void write(ByteBuf out) {
	}

	@Override
	public void process(ByteBuf in, EntityPlayer user) {
		user.openGui(MineFantasyII.instance, 1, user.worldObj, 10, 0, 0);
	}
    

}
