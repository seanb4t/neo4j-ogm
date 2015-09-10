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

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "ShopSN")
public class ShopSN extends AbstractStateNode<ShopTimeRelation> {

    @Relationship(direction = Relationship.INCOMING, type = "ShopState")
    private ShopTimeRelation identityRelation;

    @Relationship(direction = Relationship.OUTGOING, type = "PREV")
    private ShopSN previous;

    private String name;
    private String description;

    @Override public ShopTimeRelation getIdentityRelation() {
        return identityRelation;
    }

    @Override public void setIdentityRelation(ShopTimeRelation identityRelation) {
        this.identityRelation = identityRelation;
    }

    public ShopSN getPrevious() {
        return previous;
    }

    public void setPrevious(ShopSN previous) {
        this.previous = previous;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}