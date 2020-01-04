package protocol.dubbo;

import framework.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private Invocation invocation;
    // 通过future 的得到返回结果
    private Future future;

    public Invocation getInvocation() {
        return invocation;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        future = ctx.writeAndFlush(invocation);
    }

    // 发送数据
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("send ---");
        future = ctx.channel().writeAndFlush(invocation);
        if(future == null)
            System.out.println("future is none");
    }

    /**
     * URL(hostName=localhost, port=8080)
     * start netty client
     * send ---
     * null
     * Sat Jan 04 23:49:53 CST 2020  : client read Netty : hello : 12345545
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + "  : client read " + msg.toString());

    }

    @Override
    public Object call() throws Exception {
        System.out.println("----n");
        return future;
    }
}
