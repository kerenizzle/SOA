package conversor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Kerenizzle
 * INFO ALUMNO
 * Keren Delgado Nakakawa
 * Sección D01 TAREA JAVA #4
 */

public class Conversor {
    
public class StringBits{
            
    byte NByte;
    short NShort;
    int NInt,potencia,neg;
    String tmp=new String();
    
    public StringBits(){
        this.NByte=(byte)0;
        this.NShort=(short)0;
        this.NInt=0;       
    }
    
    public void PutNums(int numero){
        this.NByte=(byte)numero;
        this.NShort=(short)numero;
        this.NInt=numero;
    }
    
    public String PrintBits(byte B){
        tmp="| ";
        potencia=0x80;
        neg= 0x7F;
        potencia=potencia>>1;
        potencia = potencia&neg;
        if(B<0)
            tmp+="1";
        else
            tmp+="0";
        for(int i=1;i<8;i++){
            if(i%8==0)
                tmp+=" | ";
            if((potencia & B)>0)
                tmp+="1";
            else
                tmp+="0";
            potencia=potencia>>1;
            potencia = potencia&neg;
        }
        tmp+=" |";
        return tmp;
    }
    
    public String PrintBits(short S){
        tmp="| ";
        potencia=0x8000;
        neg= 0x7FFF;
        potencia=potencia>>1;
        potencia = potencia&neg;
        if(S<0)
            tmp+="1";
        else
            tmp+="0";
        for(int i=1;i<16;i++){
            if(i%8==0)
                tmp+=" | ";
            if((potencia & S)>0)
                tmp+="1";
            else
                tmp+="0";
            potencia=potencia>>1;
            potencia = potencia&neg;
        }
        tmp+=" |";
        return tmp;
    }
    
    public String PrintBits(int I){
        tmp="| ";
        potencia=0x80000000;
        neg= 0x7FFFFFFF;
        potencia=potencia>>1;
        potencia = potencia&neg;
        if(I<0)
            tmp+="1";
        else
            tmp+="0";
        for(int i=1;i<32;i++){
            if(i%8==0)
                tmp+=" | ";
            if((potencia & I)>0)
                tmp+="1";
            else
                tmp+="0";
            potencia=potencia>>1;
            potencia = potencia&neg;
        }
        tmp+=" |";
        return tmp;
    }
}

public class Interfaz extends JFrame {
    
        JPanel DisplayIns,DisplayLeyenda,DisplayNombre,DisplayBoton,DisplayBits,LeyendaBits;
        JLabel Leyenda,Instruccion, LByte,LShort,LInt;
        JTextField TNum;
        JButton BCalc;
        StringBits NumCalc= new StringBits();
        
        public Interfaz(){
        
        super("Práctica Cuatro Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(5,5));
        DisplayIns=new JPanel(new FlowLayout(FlowLayout.CENTER));
        DisplayIns.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        DisplayBoton=new JPanel(new FlowLayout(FlowLayout.LEFT));
        DisplayBoton.setBorder(BorderFactory.createEmptyBorder(0,40,0,40));
        DisplayNombre= new JPanel(new GridLayout(4,1,0,0));
        DisplayNombre.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        DisplayBits= new JPanel(new GridLayout(4,1,0,0));
        DisplayBits.setBorder(BorderFactory.createEmptyBorder(0,5,0,40));
        DisplayLeyenda=new JPanel(new FlowLayout(FlowLayout.CENTER));
        DisplayLeyenda.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        Instruccion= new JLabel("Ingresa un número",SwingConstants.CENTER);
        Leyenda= new JLabel("Representación Binaria de "+String.valueOf(NumCalc.NInt),SwingConstants.CENTER);
        Leyenda.setForeground(Color.GRAY);
        LByte= new JLabel(NumCalc.PrintBits(NumCalc.NByte),SwingConstants.RIGHT);
        LShort= new JLabel(NumCalc.PrintBits(NumCalc.NShort),SwingConstants.RIGHT);
        LInt=new JLabel(NumCalc.PrintBits(NumCalc.NInt),SwingConstants.RIGHT);
        TNum= new JTextField();
        BCalc= new JButton("Convertir");
        TNum.setPreferredSize(BCalc.getPreferredSize());

        DisplayIns.add(Instruccion);
        DisplayBoton.add(TNum);
        DisplayBoton.add(BCalc);
        DisplayNombre.add(new JLabel(""));
        DisplayNombre.add(new JLabel("1 BYTE"));
        DisplayNombre.add(new JLabel("2 BYTES"));
        DisplayNombre.add(new JLabel("4 BYTES"));
        DisplayBits.add(DisplayBoton);
        DisplayBits.add(LByte);
        DisplayBits.add(LShort);
        DisplayBits.add(LInt);
        DisplayLeyenda.add(Leyenda);
        this.add("North",DisplayIns);
        this.add("West",DisplayNombre);
        this.add("Center",DisplayBits);
        this.add("South",DisplayLeyenda);
        this.setSize(500,250);
        this.setVisible(true);
    
        BCalc.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    int num= Integer.parseInt(TNum.getText());
                    NumCalc.PutNums(num);
                    if(num<-256||num>255)
                        LByte.setText("VALOR FUERA DE RANGO");
                    else
                        LByte.setText(NumCalc.PrintBits(NumCalc.NByte));
                    if(num<-32768||num>32767)
                        LShort.setText("VALOR FUERA DE RANGO");
                    else
                        LShort.setText(NumCalc.PrintBits(NumCalc.NShort));
                    if(num<-2147483648||num>2147483647)
                        LInt.setText("VALOR FUERA DE RANGO");
                    else
                        LInt.setText(NumCalc.PrintBits(NumCalc.NInt));
                    Leyenda.setText("Representación Binaria de "+String.valueOf(NumCalc.NInt));
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                    }
                }  
        });
        
        
        }
    }
    

    public static void main(String[] args) {
        Interfaz Ventana=new Conversor().new Interfaz();
        Ventana.setVisible(true);
    }
    
}
