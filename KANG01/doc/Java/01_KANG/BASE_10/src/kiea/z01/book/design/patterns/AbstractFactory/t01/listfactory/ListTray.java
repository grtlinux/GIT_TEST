/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package kiea.z01.book.design.patterns.AbstractFactory.t01.listfactory;

import java.util.Iterator;

import kiea.z01.book.design.patterns.AbstractFactory.t01.factory.Item;
import kiea.z01.book.design.patterns.AbstractFactory.t01.factory.Tray;

/**
 * @author KangSeok
 * @date 2014. 9. 12.
 * @file_name ListTray.java
 *
 */
public class ListTray extends Tray
{
	public ListTray(String caption)
	{
		super(caption);
	}
	
	public String makeHTML()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("<li>\n");
		sb.append(caption + "\n");
		sb.append("<ul>\n");
		
		Iterator<Item> iter = tray.iterator();
		while (iter.hasNext()) {
			Item item = iter.next();
			sb.append(item.makeHTML());
		}
		
		sb.append("</ul>\n");
		sb.append("</li>\n");
		
		return sb.toString();
	}
}
