package org.apache.hadoop.io.erasurecode.rawcoder;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.io.erasurecode.ErasureCodeNative;
import org.apache.hadoop.io.erasurecode.ErasureCoderOptions;

import java.nio.ByteBuffer;

/**
 * A XOR raw encoder using Intel ISA-L library.
 */
@InterfaceAudience.Private
public class NativeXORRawEncoder extends AbstractNativeRawEncoder {

    static {
        ErasureCodeNative.checkNativeCodeLoaded();
    }

    public NativeXORRawEncoder(ErasureCoderOptions coderOptions) {
        super(coderOptions);
        initImpl(coderOptions.getNumDataUnits(), coderOptions.getNumParityUnits());
    }

    @Override
    protected void performEncodeImpl(
            ByteBuffer[] inputs, int[] inputOffsets, int dataLen,
            ByteBuffer[] outputs, int[] outputOffsets) {
        encodeImpl(inputs, inputOffsets, dataLen, outputs, outputOffsets);
    }

    @Override
    public void release() {
        destroyImpl();
    }

    private native void initImpl(int numDataUnits, int numParityUnits);

    private native void encodeImpl(ByteBuffer[] inputs, int[] inputOffsets,
                                   int dataLen, ByteBuffer[] outputs,
                                   int[] outputOffsets);

    private native void destroyImpl();
}
