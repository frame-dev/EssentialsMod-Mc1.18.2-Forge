package ch.framedev.essentialsmod.events;

import ch.framedev.essentialsmod.utils.ChatUtils;
import ch.framedev.essentialsmod.utils.Config;
import ch.framedev.essentialsmod.commands.BackCommand;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "essentials") // Replace with your mod's ID
public class BackEventHandler {

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        // Check if the dying entity is a player
        if (event.getEntity() instanceof ServerPlayer player) {
            Config config = new Config();
            if (config.getConfig().containsKey("back") && config.getConfig().getBoolean("back")) {
                Vec3 vec3 = new Vec3(player.getX(), player.getY(), player.getZ());
                BackCommand.backMap.put(player, vec3);
                player.sendMessage(ChatUtils.getPrefix().append(new TextComponent("If you won't Back to your Death Location use /back!")), player.getUUID());
            } else {
                if (player.hasPermissions(2))
                    player.sendMessage(new TextComponent("Back to Death Location is disabled in your config.").withStyle(ChatFormatting.RED), player.getUUID());
            }
        }
    }
}
