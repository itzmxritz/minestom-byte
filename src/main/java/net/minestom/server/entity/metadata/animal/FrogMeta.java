package net.minestom.server.entity.metadata.animal;

import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrogMeta extends AnimalMeta {
    public static final byte OFFSET = AnimalMeta.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 2;

    public FrogMeta(@NotNull Entity entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public Variant getVariant() {
        return super.metadata.getIndex(OFFSET, Variant.TEMPERATE);
    }

    public void setVariant(@NotNull Variant value) {
        super.metadata.setIndex(OFFSET, Metadata.FrogVariant(value));
    }

    public @Nullable Integer getTongueTarget() {
        return super.metadata.getIndex(OFFSET + 1, null);
    }


    public void setTongueTarget(@Nullable Integer value) {
        super.metadata.setIndex(OFFSET + 1, Metadata.OptVarInt(value));
    }


    public enum Variant {
        TEMPERATE,
        WARM,
        COLD;

        private final static Variant[] VALUES = values();
    }
}
