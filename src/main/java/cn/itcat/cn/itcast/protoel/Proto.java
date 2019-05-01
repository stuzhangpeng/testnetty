package cn.itcat.cn.itcast.protoel;

import java.util.Arrays;

/**
 *
 * 自定义协议数据类型 ChannelPipeline pipeline = ch.pipeline();
 *         pipeline.addLast(new MyDecoder());
 *         pipeline.addLast(new MyEncorder());
 *         pipeline.addLast(new MyChatServerHandler());
 *
 *
 */
public class Proto {
    private  int length;
    private byte[] arr;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getArr() {
        return arr;
    }

    public void setArr(byte[] arr) {
        this.arr = arr;
    }

    @Override
    public String toString() {
        return "Proto{" +
                "length=" + length +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }
}
