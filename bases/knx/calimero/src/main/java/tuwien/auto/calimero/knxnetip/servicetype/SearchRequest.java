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
    Copyright (C) 2005 Bernhard Erb
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

package tuwien.auto.calimero.knxnetip.servicetype;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import tuwien.auto.calimero.exception.KNXFormatException;
import tuwien.auto.calimero.knxnetip.util.HPAI;

/**
 * Represents a KNXnet/IP search request.
 * <p>
 * Such request is sent during KNXnet/IP device discovery destined to the discovery
 * endpoint of listening servers. The communication is done multicast, i.e. using the UDP
 * transport protocol.<br>
 * The counterpart sent in reply to the request are search responses.
 * <p>
 * Objects of this type are immutable.
 * 
 * @author Bernhard Erb
 * @author B. Malinowsky
 * @see tuwien.auto.calimero.knxnetip.servicetype.SearchResponse
 * @see tuwien.auto.calimero.knxnetip.Discoverer
 */
public class SearchRequest extends ServiceType
{
	private final HPAI endpoint;

	/**
	 * Creates a search request out of a byte array.
	 * <p>
	 * 
	 * @param data byte array containing a search request structure
	 * @param offset start offset of request in <code>data</code>
	 * @throws KNXFormatException if no valid host protocol address information was found
	 */
	public SearchRequest(byte[] data, int offset) throws KNXFormatException
	{
		super(KNXnetIPHeader.SEARCH_REQ);
		endpoint = new HPAI(data, offset);
	}

	/**
	 * Creates a new search request with the given client response address.
	 * <p>
	 * 
	 * @param responseAddr address of the client discovery endpoint used for the response,
	 *        use <code>null</code> to create a NAT aware search request
	 */
	public SearchRequest(InetSocketAddress responseAddr)
	{
		super(KNXnetIPHeader.SEARCH_REQ);
		endpoint = new HPAI(HPAI.IPV4_UDP, responseAddr);
	}

	/**
	 * Convenience constructor to create a new search request using the system default
	 * local host with the given client port.
	 * <p>
	 * 
	 * @param responsePort port number of the client control endpoint used for the
	 *        response, 0 &lt;= port &lt;= 0xFFFF
	 */
	public SearchRequest(int responsePort)
	{
		super(KNXnetIPHeader.SEARCH_REQ);
		endpoint = new HPAI((InetAddress) null, responsePort);
	}

	/**
	 * Returns the client discovery endpoint.
	 * <p>
	 * 
	 * @return discovery endpoint in a HPAI
	 */
	public final HPAI getEndpoint()
	{
		return endpoint;
	}

	/* (non-Javadoc)
	 * @see tuwien.auto.calimero.knxnetip.servicetype.ServiceType#getStructLength()
	 */
	short getStructLength()
	{
		return endpoint.getStructLength();
	}

	/* (non-Javadoc)
	 * @see tuwien.auto.calimero.knxnetip.servicetype.ServiceType#toByteArray
	 *      (java.io.ByteArrayOutputStream)
	 */
	byte[] toByteArray(ByteArrayOutputStream os)
	{
		final byte[] buf = endpoint.toByteArray();
		os.write(buf, 0, buf.length);
		return os.toByteArray();
	}
}
