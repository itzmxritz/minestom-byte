package net.bytemc.minestom.server;

import lombok.Getter;
import net.bytemc.minestom.server.instances.BlockHandlers;
import net.bytemc.minestom.server.instances.InstanceServerHandler;
import net.bytemc.minestom.server.instances.InstanceType;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.optifine.OptifineSupport;
import net.minestom.server.extras.velocity.VelocityProxy;
import net.minestom.server.instance.Instance;
import net.minestom.server.utils.NamespaceID;

public final class ByteServer {

    @Getter
    private static ByteServer instance;

    @Getter
    private final InstanceServerHandler instanceServerHandler;

    public ByteServer(String[] arguments) {

        instance = this;

        var minecraftServer = MinecraftServer.init();
        this.instanceServerHandler = new InstanceServerHandler();


        MinecraftServer.getBlockManager().registerHandler(NamespaceID.from("minecraft:skull"), () ->  BlockHandlers.SKULL_HANDLER);
        MinecraftServer.getBlockManager().registerHandler(NamespaceID.from("minecraft:banner"),  () -> BlockHandlers.BANNER_HELPER);

        MinecraftServer.setTerminalEnabled(false);
        OptifineSupport.enable();
        int port = 25565;

        if (System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Windows")) {
            //local support (Arguments: -port=30066 -velocity_secret=...)

            for (String argument : arguments) {
                if (argument.startsWith("-port=")) {
                    port = Integer.parseInt(argument.replace("-port=", ""));
                }
                if (argument.startsWith("-velocity_secret=")) {
                    VelocityProxy.enable(argument.replace("-velocity_secret=", ""));
                    MinecraftServer.LOGGER.info("Enabled velocity forwarding!");
                }
            }
            minecraftServer.start("127.0.0.1", port);
        } else {
            minecraftServer.start("0.0.0.0", port = Integer.parseInt(arguments[3]));
        }
        MinecraftServer.LOGGER.info("Using port: " + port + " | Using hostname: " + MinecraftServer.getServer().getAddress());

        Instance polo = this.instanceServerHandler.createInstance("polo", InstanceType.FLAT);
        this.instanceServerHandler.setSpawningInstance(polo);
    }
}
