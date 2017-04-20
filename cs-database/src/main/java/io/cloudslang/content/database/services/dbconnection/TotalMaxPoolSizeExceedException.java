/*******************************************************************************
 * (c) Copyright 2017 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.content.database.services.dbconnection;

import java.sql.SQLException;

/**
 * Created by victor on 13.01.2017.
 */
public class TotalMaxPoolSizeExceedException extends SQLException {
    private static final long serialVersionUID = 1L;

    /**
     * constructor
     *
     * @param aMsg a message
     */
    public TotalMaxPoolSizeExceedException(final String aMsg) {
        super(aMsg);
    }

}
