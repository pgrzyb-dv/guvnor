/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.guvnor.client.util;

import java.util.Date;

import org.drools.guvnor.client.explorer.Preferences;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Convenience class to handle date conversion when running under GWT.
 */
public class GWTDateConverter
    implements
    DateConverter {

    private final static String         DATE_FORMAT = Preferences.getStringPref( "drools.dateformat" );

    private final static DateTimeFormat FORMATTER   = DateTimeFormat.getFormat( DATE_FORMAT );

    //Singleton
    private static DateConverter        INSTANCE;

    public static synchronized DateConverter getInstance() {
        if ( INSTANCE == null ) {
            INSTANCE = new GWTDateConverter();
        }
        return INSTANCE;
    }

    private GWTDateConverter() {
    }

    public String format(Date date) {
        return FORMATTER.format( date );
    }

    public Date parse(String text) {
        return FORMATTER.parse( text );
    }

}