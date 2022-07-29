/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.graalvm.bisect.core.optimization;

import org.graalvm.bisect.util.Writer;
import org.graalvm.collections.UnmodifiableEconomicMap;

/**
 * Represents an optimization in a compilation unit.
 */
public interface Optimization extends OptimizationTreeNode {
    /**
     * Gets the name of this optimization. Corresponds to the name of the compiler phase or another
     * class which performed this optimization.
     *
     * @return the name of this optimization
     */
    String getOptimizationName();

    /**
     * Gets the map of additional properties of this optimization, mapped by property name.
     *
     * @return the map of additional properties
     */
    UnmodifiableEconomicMap<String, Object> getProperties();

    /**
     * Gets an ordered map that represents the position of a significant node related to this
     * optimization. It maps method names to byte code indices, starting with the method containing
     * the significant node and its bci. If the node does not belong the root method in the
     * compilation unit, the map also contains the method names of the method's callsites mapped to
     * the byte code indices of their invokes.
     *
     * @return an ordered map that represents the position of a significant node
     */
    UnmodifiableEconomicMap<String, Integer> getPosition();

    /**
     * Writes the representation of this subtree to the destination writer. This is equivalent to
     * writing the representation of this node, because an optimization has no children.
     *
     * @param writer the destination writer
     */
    @Override
    default void writeRecursive(Writer writer) {
        writeHead(writer);
    }
}
