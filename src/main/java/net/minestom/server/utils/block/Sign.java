package net.minestom.server.utils.block;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTException;
import org.jglrxavpok.hephaistos.parser.SNBTParser;

import java.io.StringReader;

public class Sign {

    private final Instance instance;
    private final Pos position;
    private final Block block;

    private final String[] lines = new String[4];

    public Sign(Pos pos, Instance instance, Block block) {
        this.position = pos;
        this.instance = instance;
        this.block = block;
    }

    public String getLine(int index) {
        return this.lines[index];
    }

    public void setLine(int index, String line) {
        this.lines[index] = line;
    }

    public void setLines(String... lines) {
        for (int i = 0; i < lines.length; i++) {
            setLine(i, lines[i]);
        }
    }

    public String[] getLines() {
        return this.lines;
    }

    private String appendLine(int index) {
        return getLine(index) == null ? "" : getLine(index);
    }

    public void update() {
        try {
            instance.setBlock(position, block.withHandler(MinecraftServer.getBlockManager().getHandler("minecraft:sign"))
                    .withNbt((NBTCompound) new SNBTParser(new StringReader("{\"Text1\":\"{\\\"text\\\":\\\"" + appendLine(0) +
                            "\\\"}\"," + "\"Text2\":\"{\\\"text\\\":\\\"" + appendLine(1) +
                            "\\\"}\",\"Text3\":\"{\\\"text\\\":\\\"" + appendLine(2) +
                            "\\\"}\",\"Text4\":\"{\\\"text\\\":\\\"" + appendLine(3) +
                            "\\\"}\"}")).parse()
                    ));
        } catch (NBTException e) {
            throw new RuntimeException(e);
        }
    }

    public class Side {
        //todo implement 1.20 side support
    }
}
