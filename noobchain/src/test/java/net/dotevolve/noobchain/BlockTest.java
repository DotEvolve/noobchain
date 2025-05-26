package net.dotevolve.noobchain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlockTest {

    @Test
    @DisplayName("Calculate hash returns consistent hash for same block data")
    void calculateHashReturnsConsistentHash() {
        Block block = new Block("Test Data", "PreviousHash");
        String hash1 = block.calculateHash();
        String hash2 = block.calculateHash();
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Calculate hash generates different hash for different data")
    void calculateHashGeneratesDifferentHashForDifferentData() {
        Block block1 = new Block("Data1", "PreviousHash");
        Block block2 = new Block("Data2", "PreviousHash");
        assertNotEquals(block1.calculateHash(), block2.calculateHash());
    }

    @Test
    @DisplayName("Mine block successfully mines a block with valid hash")
    void mineBlockSuccessfullyMinesBlock() {
        Block block = new Block("Test Data", "PreviousHash");
        block.mineBlock(2);
        assertTrue(block.hash.startsWith("00"));
    }

    @Test
    @DisplayName("Mine block handles difficulty of zero")
    void mineBlockHandlesZeroDifficulty() {
        Block block = new Block("Test Data", "PreviousHash");
        block.mineBlock(0);
        assertNotNull(block.hash);
    }

    @Test
    @DisplayName("Mine block handles high difficulty without infinite loop")
    void mineBlockHandlesHighDifficulty() {
        Block block = new Block("Test Data", "PreviousHash");
        block.mineBlock(5); // Adjust difficulty based on system performance
        assertTrue(block.hash.startsWith("00000"));
    }
}
