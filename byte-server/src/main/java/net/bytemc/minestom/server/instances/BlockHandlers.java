package net.bytemc.minestom.server.instances;

import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.tag.Tag;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public final class BlockHandlers {

    public static final BlockHandler BANNER_HELPER = new BlockHandler() {
        @Override
        public @NotNull NamespaceID getNamespaceId() {
            return NamespaceID.from("minecraft:banner");
        }

        @Override
        public @NotNull Collection<Tag<?>> getBlockEntityTags() {
            return List.of(Tag.NBT("Patterns"));
        }
    };

    public static final BlockHandler SKULL_HANDLER = new BlockHandler() {
        @Override
        public @NotNull NamespaceID getNamespaceId() {
            return NamespaceID.from("minecraft:skull");
        }

        @Override
        public @NotNull Collection<Tag<?>> getBlockEntityTags() {
            return List.of(Tag.NBT("SkullOwner"));
        }
    };
}
