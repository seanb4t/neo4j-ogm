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
package org.neo4j.ogm.integration.temporalNetworks;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import org.neo4j.ogm.domain.temporalNetwork.ShopIN;
import org.neo4j.ogm.domain.temporalNetwork.ShopSN;
import org.neo4j.ogm.domain.temporalNetwork.ShopTimeRelation;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.Neo4jIntegrationTestRule;

@Ignore
public class NetworkIntegrationTest {
    @ClassRule
    public static Neo4jIntegrationTestRule neo4jRule = new Neo4jIntegrationTestRule();

    private Session session;

    @Before
    public void init() throws IOException {
        session = new SessionFactory("org.neo4j.ogm.domain.temporalNetwork").openSession(neo4jRule.url());
    }

    @After
    public void teardown() {
        session.purgeDatabase();
    }

    /**
     * @see #issue 40
     */
    @Test
    public void shouldReadIdentityNodeAlongWithStates() {
        // arrange

        ShopIN shopIN = new ShopIN();

        ShopSN shopState1 = new ShopSN();
        shopState1.setName("Good graph databases");
        shopState1.setDescription("We sell World's Leading Graph Database");

        ShopTimeRelation stateRel1 = new ShopTimeRelation();
        stateRel1.setFrom(10L);
        stateRel1.setTo(20L);
        stateRel1.setSourceNode(shopIN);
        stateRel1.setTargetNode(shopState1);

        ShopSN shopState2 = new ShopSN();
        shopState2.setName("Great graph databases");
        shopState2.setDescription("We sell World's Leading Graph Database");

        ShopTimeRelation stateRel2 = new ShopTimeRelation();
        stateRel2.setFrom(20L);
        stateRel2.setTo(Long.MAX_VALUE);
        stateRel2.setSourceNode(shopIN);
        stateRel2.setTargetNode(shopState2);

        Set<ShopTimeRelation> relations = new HashSet<>();
        relations.add(stateRel1);
        relations.add(stateRel2);

        shopIN.setStates(relations);

        session.save(shopIN);
        session.clear();

        // act
        ShopIN loadedShopIN = session.load(ShopIN.class, shopIN.getId(), 3);

        // assert
        Assert.assertEquals(2, loadedShopIN.getStates().size());

    }
}
