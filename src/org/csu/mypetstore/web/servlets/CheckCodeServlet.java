package org.csu.mypetstore.web.servlets;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CheckCodeServlet extends HttpServlet {
    public static String Checkcode = "";
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);

    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //1.在内存生成一个图片
        Checkcode = "";
        int width = 120;
        int height = 30;
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g =(Graphics2D) img.getGraphics();//获取画笔；
        g.setColor(Color.white);//设置颜色
        g.fillRect(0,0,width,height);//填充矩形；
        g.setColor(Color.BLUE);
        g.drawRect(0,0,width-1,height-1);//画边框
        String words = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";//给定数据；
        g.setFont(new Font("隶书",Font.BOLD,20));//设置字体
        Random random = new Random();//给定四个随机数；
        int x = 20,y = 20;
        for(int i = 0;i<4;i++){
            int jiaodu = random.nextInt(60)-30;
            double hudu = jiaodu*Math.PI/180;
            g.rotate(hudu,x,y);
            int index = random.nextInt(words.length());//随机xiabiao；
            char ch = words.charAt(index);
            Checkcode += ch;
            g.rotate(-hudu,x,y);
            g.drawString(""+ch,x,y);
            x+=20;
        }
        //画四条线
        g.setColor(Color.red);
        int x1,x2,y1,y2;
        for(int i = 0;i<4;i++){
            x1 = random.nextInt(width);
            y1 = random.nextInt(height);
            x2 = random.nextInt(width);
            y2 = random.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }
        System.out.println(Checkcode);

//显示到画布页面；
        ImageIO.write(img,"jpg",response.getOutputStream());
    }
}
