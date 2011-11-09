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
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class EasyFall extends JavaPlugin
{
    public void onDisable()
    {
        logMsg( "Disabled successfully." );
    }

    public void onEnable()
    {
        PluginManager pluginMng = this.getServer().getPluginManager();
        PluginDescriptionFile pluginDesc = this.getDescription();

        prefix = new StringBuilder( "[" ).append( pluginDesc.getName() )
                                         .append( " (" )
                                         .append( pluginDesc.getVersion() )
                                         .append( ")] " )
                                         .toString();


        tryEnablePermissions();

        FileConfiguration config = this.getConfig();
        setDefaults( config );

        listHandler = new EasyFallListHandler( permHandler, usePermissions );

        cmdExec = new EasyFallCommandExecutor( listHandler );
        this.getCommand( "fallon" ).setExecutor( cmdExec );
        this.getCommand( "falloff" ).setExecutor( cmdExec );

        EasyFallEntityListener entListener = new EasyFallEntityListener( listHandler );

        pluginMng.registerEvent( Event.Type.ENTITY_DAMAGE, entListener, Event.Priority.Low, this );

        EasyFallPlayerListener playerListener = new EasyFallPlayerListener( listHandler );

        if( config.getBoolean( "AutoActivateOnLogin" ) )
            pluginMng.registerEvent( Event.Type.PLAYER_LOGIN, playerListener, Event.Priority.Normal, this );
        pluginMng.registerEvent( Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this );


        logMsg( "Enabled successfully." );
    }

    void setDefaults( FileConfiguration config )
    {
        config.getDefaults().set( "AutoActivateOnLogin", false );
    }

    void tryEnablePermissions()
    {
        if( getServer().getPluginManager().getPlugin( "Permissions" ) != null )
        {
            PermissionHandler pHandler = ( ( Permissions ) getServer().getPluginManager()
                    .getPlugin( "Permissions" ) ).getHandler();
            if( pHandler != null )
            {
                usePermissions = true;
                permHandler = pHandler;
            }
        }
    }

    public void logMsg( String msg )
    {
        mcLogger.info( prefix + msg );
    }

    EasyFallCommandExecutor cmdExec;
    EasyFallListHandler listHandler;
    Logger mcLogger;
    String prefix;
    boolean usePermissions;
    PermissionHandler permHandler;

}
