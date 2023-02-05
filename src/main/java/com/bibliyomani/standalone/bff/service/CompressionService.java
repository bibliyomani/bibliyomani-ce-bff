package com.bibliyomani.standalone.bff.service;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4SafeDecompressor;
import org.springframework.stereotype.Service;

@Service
public class CompressionService {

    private final static LZ4Factory factory = LZ4Factory.safeInstance();
    private final static LZ4Compressor compressor = factory.highCompressor();
    private final static LZ4SafeDecompressor decompressor = factory.safeDecompressor();

    public final Object[] compress(final byte[] data, final int decompressedSize) {
        final int maxCompressedLength = compressor.maxCompressedLength(decompressedSize);
        final byte[] compressed = new byte[maxCompressedLength];
        final int compressedSize = compressor.compress(data, 0, decompressedSize, compressed, 0, maxCompressedLength);

        return new Object[]{compressed, compressedSize};
    }

    public final byte[] decompress(final byte[] compressed,
                                   final int decompressedSize,
                                   final int compressedSize) {
        final byte[] restored = new byte[decompressedSize];
        decompressor.decompress(compressed, 0, compressedSize, restored, 0);

        return restored;
    }

}
