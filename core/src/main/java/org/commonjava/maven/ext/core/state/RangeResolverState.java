/*
 * Copyright (C) 2012 Red Hat, Inc. (jcasey@redhat.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.maven.ext.core.state;

import java.util.Properties;

/**
 * Captures configuration relating to resolving Maven ranges
 * Used by {@link org.commonjava.maven.ext.core.impl.RangeResolver}.
 */
public class RangeResolverState
    implements State
{
    /**
     * Suffix to enable this modder
     */
    private static final String RESOLVE_RANGES_PROPERTY = "resolveRanges";

    static
    {
        State.activeByDefault.add( RangeResolverState.class );
    }

    private final Boolean enabled;

    public RangeResolverState( final Properties userProps )
    {
         enabled = Boolean.parseBoolean( userProps.getProperty( RESOLVE_RANGES_PROPERTY, "true" ) );
    }

    /**
     * Enabled by default
     *
     * @see #RESOLVE_RANGES_PROPERTY
     * @see State#isEnabled()
     */
    @Override
    public boolean isEnabled()
    {
        return enabled;
    }
}

