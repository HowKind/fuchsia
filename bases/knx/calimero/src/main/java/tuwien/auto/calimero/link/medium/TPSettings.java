/*
 * #%L
 * OW2 Chameleon - Fuchsia Framework
 * %%
 * Copyright (C) 2009 - 2014 OW2 Chameleon
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
/*
    Calimero - A library for KNX network access
    Copyright (C) 2006-2008 B. Malinowsky

    This program is free software; you can redistribute it and/or 
    modify it under the terms of the GNU General Public License 
    as published by the Free Software Foundation; either version 2 
    of the License, or at your option any later version. 
 
    This program is distributed in the hope that it will be useful, 
    but WITHOUT ANY WARRANTY; without even the implied warranty of 
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
    GNU General Public License for more details. 
 
    You should have received a copy of the GNU General Public License 
    along with this program; if not, write to the Free Software 
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA. 
 
    Linking this library statically or dynamically with other modules is 
    making a combined work based on this library. Thus, the terms and 
    conditions of the GNU General Public License cover the whole 
    combination. 
 
    As a special exception, the copyright holders of this library give you 
    permission to link this library with independent modules to produce an 
    executable, regardless of the license terms of these independent 
    modules, and to copy and distribute the resulting executable under terms 
    of your choice, provided that you also meet, for each linked independent 
    module, the terms and conditions of the license of that module. An 
    independent module is a module which is not derived from or based on 
    this library. If you modify this library, you may extend this exception 
    to your version of the library, but you are not obligated to do so. If 
    you do not wish to do so, delete this exception statement from your 
    version. 
*/

package tuwien.auto.calimero.link.medium;

import tuwien.auto.calimero.IndividualAddress;

/**
 * Provides settings necessary for communication on TP (twisted pair) medium.
 * <p>
 * This settings type is used for twisted pair medium TP0 and TP1.
 * 
 * @author B. Malinowsky
 */
public class TPSettings extends KNXMediumSettings
{
	/**
	 * Default setting for TP0, device address is 0.0.0.
	 * <p>
	 */
	public static final TPSettings TP0 = new TPSettings(false);

	/**
	 * Default setting for TP1, device address is 0.0.0.
	 * <p>
	 */
	public static final TPSettings TP1 = new TPSettings(true);

	private final boolean tp1;

	/**
	 * Creates a new settings container with TP medium specific information.
	 * <p>
	 * 
	 * @param device individual device address to use as source address in KNX messages,
	 *        specifying <code>null</code> uses the individual address 0.0.0
	 * @param mediumTP1 <code>true</code> if communicating on TP1, <code>false</code>
	 *        if communicating on TP0
	 */
	public TPSettings(IndividualAddress device, boolean mediumTP1)
	{
		super(device);
		tp1 = mediumTP1;
	}

	/**
	 * Creates a new default container with settings for TP medium.
	 * <p>
	 * The device address is initialized to 0.0.0.
	 * 
	 * @param mediumTP1 <code>true</code> if communicating on TP1, <code>false</code>
	 *        if communicating on TP0
	 */
	public TPSettings(boolean mediumTP1)
	{
		super(null);
		tp1 = mediumTP1;
	}

	/* (non-Javadoc)
	 * @see tuwien.auto.calimero.link.medium.KNXMediumSettings#getMedium()
	 */
	public short getMedium()
	{
		return tp1 ? MEDIUM_TP1 : MEDIUM_TP0;
	}

	/**
	 * Returns whether this setting is for communication on TP1 medium or TP0.
	 * <p>
	 * 
	 * @return <code>true</code> for TP1, <code>false</code> for TP0
	 */
	public final boolean isTP1()
	{
		return tp1;
	}
}
