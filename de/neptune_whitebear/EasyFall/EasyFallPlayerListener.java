/*
 *   This file is part of EasyFall.
 *
 *
 *   EasyFall is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   EasyFall is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with EasyFall. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package de.neptune_whitebear.EasyFall;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;


public class EasyFallPlayerListener extends PlayerListener
{

    public EasyFallPlayerListener( EasyFallListHandler efListHandler )
    {
        listHandler = efListHandler;
    }


    public void onPlayerJoin( PlayerJoinEvent ev )
    {
        if( listHandler.hasPermissions( ev.getPlayer() ) ) listHandler.addPlayer( ev.getPlayer() );
    }

    public void onPlayerQuit( PlayerQuitEvent ev )
    {
        listHandler.removePlayer( ev.getPlayer() );
    }

    EasyFallListHandler listHandler;

}
