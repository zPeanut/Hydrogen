/*
 * Copyright (C) 2019 TheAltening
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.thealtening.auth.service;

/**
 * @author Vladymyr
 * @since 10/08/2019
 */
public enum AlteningServiceType {

    MOJANG("https://authserver.mojang.com/", "https://sessionserver.mojang.com/"),
    THEALTENING("http://authserver.thealtening.com/", "http://sessionserver.thealtening.com/");

    private final String authServer;
    private final String sessionServer;

    AlteningServiceType(String authServer, String sessionServer) {
        this.authServer = authServer;
        this.sessionServer = sessionServer;
    }

    public String getAuthServer() {
        return this.authServer;
    }

    public String getSessionServer() {
        return sessionServer;
    }
}
