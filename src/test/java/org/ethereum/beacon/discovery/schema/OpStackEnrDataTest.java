/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.ethereum.beacon.discovery.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.tuweni.units.bigints.UInt64;
import org.junit.jupiter.api.Test;

class OpStackEnrDataTest {

  @Test
  void encode() {
    OpStackEnrData opStackEnrData = new OpStackEnrData(UInt64.valueOf(8543L), UInt64.valueOf(0L));
    assertEquals(3, opStackEnrData.encode().toArray().length);
  }

  @Test
  void decode() {
    OpStackEnrData opStackEnrData = new OpStackEnrData(UInt64.valueOf(8543L), UInt64.valueOf(0L));
    OpStackEnrData des = OpStackEnrData.decode(opStackEnrData.encode());
    assertEquals(0, des.getVersion().intValue());
    assertEquals(8543, des.getChainId().intValue());
  }
}
