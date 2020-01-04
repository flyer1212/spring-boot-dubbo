package protocol.dubbo;

import framework.Invocation;
import framework.URL;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import register.MapRegister;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);

        Invocation invocation = (Invocation) msg;
        System.out.println("receive" + invocation.toString());

        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().localAddress();
        System.out.println(inetSocketAddress.getHostName() + " -- " + inetSocketAddress.getPort());
//        Class serviceImpl = MapRegister.get(invocation.getIntefaceName(),
//                new URL(inetSocketAddress.getHostName(), inetSocketAddress.getPort()));
//        if (serviceImpl == null) {
//            System.out.println("ewewewew");
//        }
        URL url = new URL("localhost", 8080);
        // 从http 请求里面得到要调用的接口名，
        Class implClass = MapRegister.get(invocation.getIntefaceName(), url);

        Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());

        Object resutl = method.invoke(implClass.newInstance(), invocation.getParams());

        System.out.println("Netty : " + resutl);
        ctx.writeAndFlush("Netty : " + resutl);
    }
}
