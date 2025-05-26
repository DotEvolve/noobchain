package net.dotevolve.noobchain;

import java.util.ArrayList;
import java.util.List;

import net.dotevolve.noobchain.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoobChain {

    private static final Logger logger = LoggerFactory.getLogger(NoobChain.class);

    private static final List<Block> blockchain = new ArrayList<>();
    private static final int DIFFICULTY = 5;

    public static void main(String[] args) {
        //add our blocks to the blockchain ArrayList:

        logger.info("Trying to Mine block 1... ");
        addBlock(new Block("Hi im the first block", "0"));

        logger.info("Trying to Mine block 2... ");
        addBlock(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));

        logger.info("Trying to Mine block 3... ");
        addBlock(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));

        logger.info("Blockchain is Valid: {} ", isChainValid());

        String blockchainJson = StringUtil.getJson(blockchain);
        logger.info("The block chain: ");
        logger.info(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = String.valueOf(new char[DIFFICULTY]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                logger.info("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                logger.info("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if (!currentBlock.hash.substring(0, DIFFICULTY).equals(hashTarget)) {
                logger.info("This block hasn't been mined");
                return false;
            }

        }
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(DIFFICULTY);
        blockchain.add(newBlock);
    }
}