package horse.moon.kittens;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RainingKittens implements ModInitializer {
	public static final String MOD_ID = "raining-kittens";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loaded RainingKittens!");

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerWorld world : server.getWorlds()) {
				world.getPlayers().forEach(player -> {
					if (server.getTicks() % 5 == 0) {
						MakeItRain(world, player.getBlockPos().add(0, 10, 0), 4);
					}
				});
			}
		});
	}

	private void MakeItRain(ServerWorld world, BlockPos position, int maxNumber) {
		var random = new Random();

		for (var i = 0; i < Math.max(random.nextInt(maxNumber), 1); ++i) {
			EntityType.CAT.spawn(world, position.add(random.nextInt(4), 0, random.nextInt(4)), SpawnReason.COMMAND);
		}
	}
}