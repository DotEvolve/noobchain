package net.dotevolve.noobchain;

import java.util.Date;

import net.dotevolve.noobchain.exception.SHA256Exception;
import net.dotevolve.noobchain.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Block {

    private static final Logger logger = LoggerFactory.getLogger(Block.class);

    String hash;
    final String previousHash;
    private final String data; //our data will be a simple message.
    private final long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;

    //Block Constructor.
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }

    //Calculate new hash based on blocks contents
    public String calculateHash() {
        try {
            return StringUtil.applySha256(
                    previousHash +
                            timeStamp +
                            nonce +
                            data
            );
        } catch (SHA256Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        logger.info("Block Mined!!! : {} ", hash);
    }

}