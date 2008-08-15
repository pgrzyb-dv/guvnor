package org.drools.guvnor.server.util;
/*
 * Copyright 2005 JBoss Inc
 *
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
 */
import org.drools.guvnor.client.ruleeditor.PropertiesHolder;
import org.drools.guvnor.client.ruleeditor.PropertyHolder;

import java.util.List;
import java.util.ArrayList;

/**
 * used to convert PropertiesHolder to text and back
 *
 * @author Anton Arhipov
 */
public class PropertiesPersistence {

    private static PropertiesPersistence INSTANCE = new PropertiesPersistence();

    private PropertiesPersistence() {
    }

    public static PropertiesPersistence getInstance() {
        return INSTANCE;
    }

    public String marshal(PropertiesHolder holder) {
        StringBuilder sb = new StringBuilder();
        for (PropertyHolder propertyHolder : holder.list) {
            sb.append(propertyHolder.name).append("=").append(propertyHolder.value).append("\n");
        }
        return sb.toString();
    }

    public PropertiesHolder unmarshal(String properties) {
        List<PropertyHolder> list = new ArrayList<PropertyHolder>();
        String[] props = properties.split("\n");
        for (String s : props) {
            String[] pair = s.split("=");
            list.add(new PropertyHolder(pair[0], pair[1]));
        }
        PropertiesHolder result = new PropertiesHolder();
        result.list = list;
        return result;
    }

}