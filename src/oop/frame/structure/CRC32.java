package oop.frame.structure;

/**
 * This class represents a cyclic redundancy check
 */
public class CRC32 {
    private int crc = 0xFFFFFFFF; // initial contents

    /**
     * calculates a checksum for given input {@code byte[]}
     *
     * @param bytes {@code byte[]} to create checksum for
     */
    public void update(byte[] bytes) {

        int poly = 0xEDB88320; // reverse polynomial
        for (byte b : bytes) {
            int temp = (crc ^ b) & 0xff;

            // read 8 bits one at a time
            for (int i = 0; i < 8; i++) {
                if ((temp & 1) == 1) {
                    temp = (temp >>> 1) ^ poly;
                } else {
                    temp = (temp >>> 1);
                }
            }
            crc = (crc >>> 8) ^ temp;
        }
        // flip bits
        crc = ~crc;
    }

    /**
     * Gets the calculated checksum
     *
     * @return {@code byte[]} representing the checksum
     */
    public byte[] getCRC() {
        return
                new byte[]{
                (byte) (crc >> 24),
                (byte) (crc >> 16),
                (byte) (crc >> 8),
                (byte) crc};
    }

    /**
     * Method returns CRC as a hex string
     * @return CRC as hex string
     */
    public String toHexString() {
        String toHex = "";
        for (byte crc : getCRC()) {
            toHex += String.format("%02x", crc & 0xFF);
        }
        return toHex;
    }
}
