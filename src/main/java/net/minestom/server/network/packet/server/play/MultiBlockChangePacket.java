package net.minestom.server.network.packet.server.play;

import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;
import net.minestom.server.utils.binary.BinaryWriter;
import net.minestom.server.utils.chunk.ChunkUtils;

public class MultiBlockChangePacket implements ServerPacket {

    public int chunkX;
    public int chunkZ;
    public int section;
    //TODO this is important prob if we add a light api
    public boolean suppressLightUpdates = true;
    public BlockChange[] blockChanges;

    @Override
    public void write(BinaryWriter writer) {
        writer.writeLong(ChunkUtils.getChunkIndexWithSection(chunkX, chunkZ, section));
        writer.writeBoolean(suppressLightUpdates);
        if (blockChanges != null) {
            final int length = blockChanges.length;
            writer.writeVarInt(length);
            for (int i = 0; i < length; i++) {
                final BlockChange blockChange = blockChanges[i];
                writer.writeVarInt(blockChange.newBlockId << 12 | ChunkUtils.getLocalBlockPosAsShort(blockChange.positionX, blockChange.positionY, blockChange.positionZ));
            }
        } else {
            writer.writeVarInt(0);
        }
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.MULTI_BLOCK_CHANGE;
    }

    public static class BlockChange {
        public int positionX;
        public int positionY;
        public int positionZ;
        public int newBlockId;

    }
}
