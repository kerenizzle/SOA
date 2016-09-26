package piramide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;
import javax.swing.border.*;

/**
 * @author Kerenizzle
 * INFO ALUMNO
 * Keren Delgado Nakakawa
 * Sección D01 TAREA JAVA #2
 */
public class Piramide {

    public String Generar_Piramide(int num, char sim){
        String TextPir= new String();
        for (int fila=1;fila<=num;fila++){
            for (int espacios=0; espacios<(num-fila)*2;espacios++){
                TextPir += " ";
            }
            for (int columna=0; columna<fila*2-1;columna++){
                    TextPir += sim + " ";
            }
            TextPir += System.lineSeparator();
        }        
        
    return TextPir;
    }
    
    public class Interfaz extends JFrame {
    
        BorderLayout Fondo;
        JPanel Norte,Centro,BotonesSur;
        private JTextField TSim, TNum;
        JTextArea JPir;
        JButton BDibujar,BRandom,BGuardar;
        private String Caracter,Resultado;
        private char Sim;
        private int Altura;
        
        public Interfaz(){
        super("Práctica Dos Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Fondo= new BorderLayout(5,5);
        Resultado= new String();
        this.setLayout(Fondo);
        Norte= new JPanel();
        Centro= new JPanel(new FlowLayout(FlowLayout.CENTER));
        BotonesSur= new JPanel();
        TSim= new JTextField("Ingresa símbolo");     
        TNum= new JTextField("Ingresa número");
        JPir= new JTextArea(20,20);
        JPir.setFont(new Font("Monospaced",Font.PLAIN,14));
        JPir.setOpaque(false);
        BDibujar= new JButton("Dibujar");
        BGuardar=new JButton("Guardar");
        BRandom= new JButton("Aleatorio");
        
        Norte.setBorder(BorderFactory.createEmptyBorder(10,5,5,5));
        Norte.setLayout(new GridLayout(1,2,5,5));
        Norte.add(TSim);
        Norte.add(TNum);
        Border BCentro= BorderFactory.createLineBorder(Color.GRAY,1);
        Centro.setBorder(BorderFactory.createTitledBorder(BCentro,"Pirámide",TitledBorder.CENTER,TitledBorder.TOP ));
        Centro.add(JPir);
        BotonesSur.setLayout(new GridLayout (1,3,5,5));
        BotonesSur.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        BotonesSur.add(BDibujar);
        BotonesSur.add(BRandom);
        BotonesSur.add(BGuardar);
        
        this.add("North",Norte);
        this.add("Center",Centro);
        this.add("South",BotonesSur);
        this.setSize(500,400);
        this.setResizable(true);
        this.setVisible(true);
        
        TSim.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TSim.setText(null);
            }
        });
        
        TNum.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TNum.setText(null);
            }
        });
          
        BDibujar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Caracter= TSim.getText();
                try{
                    Altura= Integer.parseInt(TNum.getText());
                        if (Altura>0){
                            if (!Caracter.isEmpty()&&!Caracter.equals("Ingresa símbolo")){
                                Sim= Caracter.charAt(0);
                                JPir.setRows(Altura);
                                JPir.setColumns(Altura*2);
                                Resultado=Generar_Piramide(Altura,Sim);
                                JPir.setText(Resultado);
                            }
                            else
                                JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un caracter");
                        }
                        else
                            JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un valor mayor a cero");
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                }
            }  
        });
        
        BRandom.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Random r=new Random();
                Sim=(char)(r.nextInt(95)+33);
                Altura=r.nextInt(29)+1;
                TSim.setText(String.valueOf(Sim));
                TNum.setText(String.valueOf(Altura));
                JPir.setRows(Altura);
                JPir.setColumns(Altura*2);
                Resultado=Generar_Piramide(Altura,Sim);
                JPir.setText(Resultado);
            }
        });
        
        BGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
            if(!Resultado.isEmpty()){
                String NombreArchivo= new String();
                NombreArchivo += "Piramide" + String.valueOf((int)Sim) + "_Altura" + String.valueOf(Altura) +".txt";
                try (Writer wr = new BufferedWriter(new OutputStreamWriter
                (new FileOutputStream(NombreArchivo), "utf-8"))) {
                    wr.write(Resultado);
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Error al guardar archivo");
                } 
            }
            else
                JOptionPane.showMessageDialog(new JFrame(), "No has generado una pirámide aún");
            }  
        });
        
        }
       
    }
    
    public static void main(String[] args) {
        
        Interfaz Ventana=new Piramide().new Interfaz();
        Ventana.setVisible(true);
    }
    
}
