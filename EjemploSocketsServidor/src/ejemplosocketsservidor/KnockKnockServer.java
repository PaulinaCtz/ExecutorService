/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplosocketsservidor;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KnockKnockServer {

    private static final int THREAD_POOL_SIZE = 3;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            System.out.println("Servidor iniciado en el puerto 4444.");

            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());

                Runnable clientHandler = new manejadorCliente(clientSocket);
                executorService.execute(clientHandler);
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor en el puerto 4444: " + e.getMessage());
        }
    }
}
