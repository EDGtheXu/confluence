package org.confluence.mod.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.confluence.mod.Confluence;
import org.confluence.mod.capability.ability.AbilityProvider;
import org.confluence.mod.client.handler.GravitationHandler;
import org.confluence.mod.item.IRangePickup;
import org.confluence.mod.item.curio.combat.ICriticalHit;
import org.confluence.mod.item.curio.combat.IFireAttack;
import org.confluence.mod.network.s2c.InfoCurioCheckPacketS2C;
import org.confluence.mod.util.ModUtils;

@Mod.EventBusSubscriber(modid = Confluence.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class PlayerEvents {
    @SubscribeEvent
    public static void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            InfoCurioCheckPacketS2C.send(serverPlayer, serverPlayer.getInventory());
            ModUtils.resetClientPacket(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if (event.player.isLocalPlayer()) {
                GravitationHandler.unCrouching(event.player);
            }
        } else {
            Player player = event.player;
            IRangePickup.Drops.apply(player);
            if (player instanceof ServerPlayer serverPlayer) {
                if (serverPlayer.level().getGameTime() % 200 == 0) {
                    // 每十秒向周围玩家共享一次信息配饰
                    InfoCurioCheckPacketS2C.sendToOthers(serverPlayer);
                }
            }
        }
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;
        Player oldPlayer = event.getOriginal();
        Player neoPlayer = event.getEntity();
        oldPlayer.revive();

        oldPlayer.getCapability(AbilityProvider.CAPABILITY).ifPresent(old -> neoPlayer.getCapability(AbilityProvider.CAPABILITY).ifPresent(neo -> neo.copyFrom(old)));

        if (neoPlayer instanceof ServerPlayer serverPlayer) {
            ModUtils.resetClientPacket(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void attackEntity(AttackEntityEvent event) {
        IFireAttack.apply(event.getEntity(), event.getTarget());
    }

    @SubscribeEvent
    public static void criticalHit(CriticalHitEvent event) {
        ICriticalHit.apply(event);
    }

    @SubscribeEvent
    public static void breakSpeed(PlayerEvent.BreakSpeed event) {
        MutableFloat speed = new MutableFloat(event.getNewSpeed());
        event.getEntity().getCapability(AbilityProvider.CAPABILITY).ifPresent(playerAbility -> {
            float value = speed.floatValue();
            value *= (1.0F + playerAbility.getBreakSpeedBonus());
            speed.setValue(value);
        });
        event.setNewSpeed(speed.floatValue());
    }
}
