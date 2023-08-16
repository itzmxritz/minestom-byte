package net.bytemc.minestom.server.instances;

import lombok.Getter;
import lombok.Setter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.world.DimensionType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class InstanceSeverHandler {

    @Setter
    @Getter
    private Instance spawningInstance;

    private final Map<String, Instance> worlds = new ConcurrentHashMap<>();

    public InstanceSeverHandler() {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            if (spawningInstance == null) {
                event.getPlayer().kick("§cThis service is currently unavailable");
            } else {
                event.setSpawningInstance(spawningInstance);
            }
        });
    }

    public Instance createInstance(String name, InstanceType type) {
        var container = new InstanceContainer(UUID.randomUUID(), DimensionType.OVERWORLD, new AnvilLoader("instances/" + name));
        MinecraftServer.getInstanceManager().registerInstance(container);
        container.setGenerator(type.generator);
        this.worlds.put(name, container);
        return container;
    }

    public Instance registerInstance(String name) {
        var container = new InstanceContainer(UUID.randomUUID(), DimensionType.OVERWORLD, new AnvilLoader("instances/" + name));
        MinecraftServer.getInstanceManager().registerInstance(container);
        container.setGenerator(InstanceType.VOID.generator);
        this.worlds.put(name, container);
        return container;
    }

    public Collection<Instance> getWorlds() {
        return this.worlds.values();
    }

    public Optional<Instance> getContainer(String name) {
        return Optional.ofNullable(this.worlds.get(name));
    }

    public Instance getContainerOrNull(String name) {
        return this.worlds.get(name);
    }
}
