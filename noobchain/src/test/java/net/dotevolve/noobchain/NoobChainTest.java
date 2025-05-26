package net.dotevolve.noobchain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoobChainTest {

    @Test
    @DisplayName("IsChainValid returns true for a valid blockchain")
    void isChainValidReturnsTrueForValidBlockchain() {
        NoobChain.addBlock(new Block("Block 1", "0"));
        NoobChain.addBlock(new Block("Block 2", NoobChain.blockchain.get(NoobChain.blockchain.size() - 1).hash));
        assertTrue(NoobChain.isChainValid());
    }

    @Test
    @DisplayName("IsChainValid returns false if a block's hash is tampered")
    void isChainValidReturnsFalseForTamperedHash() {
        NoobChain.addBlock(new Block("Block 1", "0"));
        NoobChain.blockchain.get(0).hash = "tamperedHash";
        assertFalse(NoobChain.isChainValid());
    }

    @Test
    @DisplayName("IsChainValid returns false if a block's previous hash is tampered")
    void isChainValidReturnsFalseForTamperedPreviousHash() {
        NoobChain.addBlock(new Block("Block 1", "0"));
        NoobChain.addBlock(new Block("Block 2", "tamperedPreviousHash"));
        assertFalse(NoobChain.isChainValid());
    }

    @Test
    @DisplayName("IsChainValid returns false if a block is not mined correctly")
    void isChainValidReturnsFalseForUnminedBlock() {
        Block block = new Block("Block 1", "0");
        block.hash = "invalidHash";
        NoobChain.blockchain.add(block);
        assertFalse(NoobChain.isChainValid());
    }

    @Test
    @DisplayName("AddBlock successfully mines and adds a block to the blockchain")
    void addBlockSuccessfullyAddsBlock() {
        int initialSize = NoobChain.blockchain.size();
        NoobChain.addBlock(new Block("New Block", "0"));
        assertEquals(initialSize + 1, NoobChain.blockchain.size());
        assertTrue(NoobChain.blockchain.get(initialSize).hash.startsWith("00000"));
    }
}
