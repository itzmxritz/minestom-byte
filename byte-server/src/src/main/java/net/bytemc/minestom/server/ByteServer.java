package net.bytemc.minestom.server;

import lombok.Getter;
import net.bytemc.minestom.server.instances.InstanceSeverHandler;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.optifine.OptifineSupport;
import net.minestom.server.extras.velocity.VelocityProxy;

public final class ByteServer {

    @Getter
    private static ByteServer instance;

    @Getter
    private final InstanceSeverHandler instanceSeverHandler;

    public ByteServer(String[] arguments) {

        instance = this;

        var minecraftServer = MinecraftServer.init();
        this.instanceSeverHandler = new InstanceSeverHandler();

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
    }
}
