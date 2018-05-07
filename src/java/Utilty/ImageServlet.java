/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author erkan
 */
//@WebServlet(name = "imageServlet", urlPatterns = {"/image/*"})
public class ImageServlet extends HttpServlet {

    private String filePath;

    @Override
    public void init() {

        this.filePath = "C:\\upload\\";
    }

    @Override                                //istek                      //cevap
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestedImage = request.getPathInfo();

        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println(requestedImage);
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");

        File file = new File(filePath, requestedImage);
        String contentType = getServletContext().getMimeType(file.getName());
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content -Length", String.valueOf(file.length()));
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
