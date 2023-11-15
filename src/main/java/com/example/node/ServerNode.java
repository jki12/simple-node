package com.example.node;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.Wire;
import com.example.message.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerNode extends WirableNode implements OutputNode {
    private static int DEFAULT_PORT = 8000;

    protected ServerSocket serverSocket;
    // private int port;

    public ServerNode(String name, int port) throws IOException {
        super(name);

        serverSocket = new ServerSocket(port);
    }

    public ServerNode(String name) throws IOException {
        this(name, DEFAULT_PORT);
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void preprocess() {
        log.info("서버 소켓이 {}의 {} 포트에서 열렸습니다.", serverSocket.getLocalSocketAddress(), serverSocket.getLocalPort());
        
        // serverSocket.accept()
    }

    @Override
    public void process() {
        try {
            Socket con = serverSocket.accept();

            Message message = new Message();
            message.put("message", con.toString()); // TODO message formatting

            for (Wire wire : wires) {
                wire.put(message);
            }
            
        } catch (Exception e) {
        }
    }

    @Override
    public void postprocess() {
        try {
            serverSocket.close();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void wireIn(Wire wire) {
        wires.add(wire);
    }

    @Override
    public boolean canWire(WirableNode other) {
        return (other instanceof InputNode);
    }
}
