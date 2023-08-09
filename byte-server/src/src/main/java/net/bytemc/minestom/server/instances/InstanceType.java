package net.bytemc.minestom.server.instances;

import net.bytemc.minestom.server.instances.generators.FlatGenerator;
import net.bytemc.minestom.server.instances.generators.VoidGenerator;
import net.minestom.server.instance.generator.Generator;

public enum InstanceType {

    VOID(new VoidGenerator()),
    FLAT(new FlatGenerator());

    final Generator generator;

    InstanceType(Generator generator) {
        this.generator = generator;
    }
}
