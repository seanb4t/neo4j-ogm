/*
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 *
 */
package org.neo4j.ogm.domain.temporalNetwork;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.StartNode;

public abstract class AbstractTimeRelation<S extends INode, T extends INode> implements ITimeRelation<S, T> {
    @GraphId
    private Long relationshipId;

    @StartNode
    private S sourceNode;

    @EndNode
    private T targetNode;

    @Property
    private Long from;

    @Property
    private Long to;

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    @Override public S getSourceNode() {
        return sourceNode;
    }

    @Override public void setSourceNode(S sourceNode) {
        this.sourceNode = sourceNode;
    }

    @Override public T getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(T targetNode) {
        this.targetNode = targetNode;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }
}
