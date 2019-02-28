package fr.devsylone.fallenkingdom.listeners.entity.player;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import fr.devsylone.fallenkingdom.Fk;

public class ChangeGamemodeListener implements Listener
{
	@EventHandler
	public void change(PlayerGameModeChangeEvent e)
	{
		if(e.getNewGameMode() == GameMode.CREATIVE)
		{
			Fk.getInstance().getPacketManager().sendTitle(e.getPlayer(), "§cAttention", "§6⇩ Lis le chat ⇩", 20, 100,20);
			Fk.getInstance().getPlayerManager().getPlayer(e.getPlayer()).sendMessage("§cAttention ! En créatif vous pouvez faire des actions impossible en survie au niveau du plugin, tel que casser des blocs en territoire enemi, en poser, ouvrir des coffres à crocheter, aller dans le nether, etc...");
		}
	}
}
