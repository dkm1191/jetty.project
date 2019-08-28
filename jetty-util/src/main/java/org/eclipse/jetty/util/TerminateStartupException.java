//
//  ========================================================================
//  Copyright (c) 1995-2019 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.util;

/**
 * TerminateStartupException
 * 
 * Can be thrown during the startup sequence which will
 * cause the startup of the server to terminate.
 *
 */
public class TerminateStartupException extends Exception
{
    public static boolean isTerminateStartup(Throwable e)
    {
        if (e instanceof TerminateStartupException)
            return true;
        
        if (e instanceof MultiException)
        {
            for (Throwable t: ((MultiException)e).getThrowables())
            {
                if (t instanceof TerminateStartupException)
                    return true;
            }
            return false;
        }

        if (e instanceof Exception)
        {
            return isTerminateStartup(e.getCause());
        }

        return false;
    }
}
