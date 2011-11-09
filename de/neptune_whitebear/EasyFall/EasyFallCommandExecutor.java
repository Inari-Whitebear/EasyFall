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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

class EasyFallCommandExecutor implements CommandExecutor
{

    public EasyFallCommandExecutor( EasyFallListHandler efListHandler, FileConfiguration cConfig )
    {
        listHandler = efListHandler;
        config = cConfig;
    }

    public boolean onCommand( CommandSender sender, Command command, String label, String[] args )
    {
        if( sender instanceof Player )
        {
            Player pSender = ( Player ) sender;
            if( label.toLowerCase().equals( "fallon" ) )
            {
                if( listHandler.hasPermissions( pSender ) )
                {
                    listHandler.enableFallDamage( pSender );
                } else
                {
                    pSender.sendMessage( ChatColor.RED + config.getString( "noPermissionsMessage" ) );
                }
            } else if( label.toLowerCase().equals( "falloff" ) )
            {
                if( listHandler.hasPermissions( pSender ) )
                {
                    listHandler.disableFallDamage( pSender );
                } else
                {
                    pSender.sendMessage( ChatColor.RED + config.getString( "noPermissionsMessage" ) );
                }
            }

        }

        return false;

    }

    private final EasyFallListHandler listHandler;
    private final FileConfiguration config;
}
