package com.research.misc.netty.pingpang;

import java.io.Serializable;

/**
 * @author yicheng
 * @since 2014年6月24日
 * 
 */
public class PingPong implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Current rank of the ping-pong
     */
    public int rank;

    /**
     * Array of bytes for the ping pong message
     */
    public byte[] status;

    /**
     * Default String (for instance for MD5 of the array of bytes)
     */
    public String test1 = "12345678901234567890123456789012";

    /**
     * Constructor from rank and array of bytes
     * 
     * @param rank
     * @param status
     * @return
     */
    public PingPong(int rank, byte[] status) {
        this.rank = rank;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PingPong:" + rank;
    }
}