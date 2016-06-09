package org.apache.hadoop.io.erasurecode.rawcoder;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.io.erasurecode.ErasureCodeNative;
import org.apache.hadoop.io.erasurecode.ErasureCoderOptions;

import java.nio.ByteBuffer;

/**
 * A XOR raw encoder using Intel ISA-L library.
 */
@InterfaceAudience.Private
public class NativeXORRawDecoder extends AbstractNativeRawDecoder {

    static {
        ErasureCodeNative.checkNativeCodeLoaded();
    }

    public NativeXORRawDecoder(ErasureCoderOptions coderOptions) {
        super(coderOptions);
        initImpl(coderOptions.getNumDataUnits(), coderOptions.getNumParityUnits());
    }

    @Override
    protected void performDecodeImpl(ByteBuffer[] inputs, int[] inputOffsets,
                                     int dataLen, int[] erased,
                                     ByteBuffer[] outputs, int[] outputOffsets) {
        decodeImpl(inputs, inputOffsets, dataLen, erased, outputs, outputOffsets);
    }

    @Override
    public void release() {
        destroyImpl();
    }

    private native void initImpl(int numDataUnits, int numParityUnits);

    private native void decodeImpl(
            ByteBuffer[] inputs, int[] inputOffsets, int dataLen, int[] erased,
            ByteBuffer[] outputs, int[] outputOffsets);

    private native void destroyImpl();
}
