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

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;


class EasyFallEntityListener extends EntityListener
{

    public EasyFallEntityListener( EasyFallListHandler efListHandler )
    {
        listHandler = efListHandler;
    }

    public void onEntityDamage( EntityDamageEvent event )
    {
        if( event.isCancelled() ) return;

        if( event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL )
        {
            Player ePlayer = ( Player ) event.getEntity();
            if( listHandler.hasDamageDisabled( ePlayer ) ) event.setCancelled( true );
        }

    }


    private final EasyFallListHandler listHandler;
}
