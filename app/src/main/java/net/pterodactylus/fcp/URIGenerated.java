/*
 * jFCPlib - URIGenerated.java - Copyright © 2008 David Roden
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 */

package net.pterodactylus.fcp;

/**
 * The “URIGenerated” message signals the client that an URI was generated for
 * a {@link ClientPut} (or {@link ClientPutDiskDir} or
 * {@link ClientPutComplexDir} ) request.
 *
 * @author David ‘Bombe’ Roden &lt;bombe@freenetproject.org&gt;
 */
public class URIGenerated extends BaseMessage implements Identifiable {

	/**
	 * Creates a new “URIGenerated” message that wraps the received message.
	 *
	 * @param receivedMessage
	 *            The received message
	 */
	URIGenerated(FcpMessage receivedMessage) {
		super(receivedMessage);
	}

	/**
	 * Returns the identifier of the request that generated an URI.
	 *
	 * @return The identifier of the request
	 */
	@Override
	public String getIdentifier() {
		return getField("Identifier");
	}

	/**
	 * Returns the URI that was generated by the request.
	 *
	 * @return The URI that was generated by the request
	 */
	public String getURI() {
		return getField("URI");
	}

	/**
	 * Returns whether the request that generated the URI is on the global
	 * queue or on the client-local queue.
	 *
	 * @return <code>true</code> if the request is on the global queue,
	 *         <code>false</code> if it is on the client-local queue
	 */
	public boolean isGlobal() {
		return Boolean.parseBoolean(getField("Global"));
	}

}
