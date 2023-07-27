/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.ethereum.beacon.discovery.schema;

import com.google.common.base.Objects;
import io.libp2p.etc.types.ByteBufExtKt;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.units.bigints.UInt64;

public class OpStackEnrData {

  private UInt64 chainId;

  private UInt64 version;

  public OpStackEnrData(UInt64 chainId, UInt64 version) {
    this.chainId = chainId;
    this.version = version;
  }

  public UInt64 getChainId() {
    return chainId;
  }

  public void setChainId(UInt64 chainId) {
    this.chainId = chainId;
  }

  public UInt64 getVersion() {
    return version;
  }

  public void setVersion(UInt64 version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OpStackEnrData)) {
      return false;
    }
    OpStackEnrData that = (OpStackEnrData) o;
    return Objects.equal(chainId, that.chainId) && Objects.equal(version, that.version);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(chainId, version);
  }

  @Override
  public String toString() {
    return "OpStackEnrData{" + "chainId=" + chainId + ", version=" + version + '}';
  }

  public Bytes encode() {
    ByteBuf buffer = Unpooled.buffer(20);
    ByteBufExtKt.writeUvarint(buffer, chainId.toLong());
    ByteBufExtKt.writeUvarint(buffer, version.toLong());
    return Bytes.wrap(ByteBufUtil.getBytes(buffer));
  }

  public static OpStackEnrData decode(Bytes value) {
    ByteBuf buffer = Unpooled.wrappedBuffer(value.toArray());
    UInt64 chainId = UInt64.valueOf(ByteBufExtKt.readUvarint(buffer));
    UInt64 version = UInt64.valueOf(ByteBufExtKt.readUvarint(buffer));
    return new OpStackEnrData(chainId, version);
  }
}
