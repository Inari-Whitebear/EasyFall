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

import com.nijiko.permissions.PermissionHandler;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class EasyFallListHandler
{

    public EasyFallListHandler( PermissionHandler pPermHandler, boolean bUsePermissions )
    {
        usePermissions = bUsePermissions;
        permHandler = pPermHandler;
    }

    public boolean hasPermissions( Player player )
    {
        if( usePermissions )
        {
            return permHandler.has( player, "easyfall.use" );
        } else return player.hasPermission( "easyfall.use" );
    }

    public boolean addPlayer( Player player )
    {
        if( !enabledPlayers.contains( player.getName() ) )
        {
            enabledPlayers.add( player.getName() );
            return true;
        }
        return false;
    }

    public boolean removePlayer( Player player )
    {
        if( enabledPlayers.contains( player.getName() ) )
        {
            enabledPlayers.remove( player.getName() );
            return true;
        }
        return false;
    }

    public boolean hasEnabled( Player player )
    {
        return enabledPlayers.contains( player.getName() );
    }

    boolean usePermissions;
    PermissionHandler permHandler;
    HashSet<String> enabledPlayers;

}
