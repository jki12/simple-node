package com.example.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.example.Wire;
import com.example.message.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoServerNode extends ServerNode {
    static class Handler implements Runnable {
        private final Socket socket;
        private Thread thread;

        public Handler(Socket socket) {
            this.socket = socket;

            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            
            try (var br = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
                while (!thread.interrupted()) {

                    String s = br.readLine();

                    if (s == null) { // end of the stream has been reached.
                        thread.interrupt();
                        continue;
                    }
    
                    log.info("input message : {}", s);
                    socket.getOutputStream().write(s.getBytes());
                    socket.getOutputStream().write(System.lineSeparator().getBytes());
                    socket.getOutputStream().flush();
                    
                }
            } catch (Exception e) {
                    log.warn(e.getMessage());
            }
        }

        public void start() {
            thread.start();
        }
    }

    public EchoServerNode(String name, int port) throws IOException {
        super(name, port);
    }

    public EchoServerNode(String name) throws IOException {
        super(name);
    }

    @Override
    public void process() {
        try {
            Socket con = serverSocket.accept();
            Handler handler = new Handler(con);

            handler.start();

            Message message = new Message();
            message.setMessage("connected : " + con.toString()); // TODO message formatting

            for (Wire wire : wires) {
                wire.put(message);
            }
            
        } catch (Exception e) {
        }
    }
}
