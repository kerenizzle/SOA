package encapsulamiento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.border.*;

/**
 * @author Kerenizzle
 * INFO ALUMNO
 * Keren Delgado Nakakawa
 * Sección D01 TAREA JAVA #3
 */

public class Encapsulamiento {

    public class MiFecha{
   
    private int DD,MM,YYYY;
    
    public MiFecha(){
        DD=1; 
        MM=9; 
        YYYY=2016;
    }
    
    public String FechaString(){
        return String.valueOf(this.DD)+"/"+String.valueOf(this.MM)+"/"+String.valueOf(this.YYYY);
    }
    
public String DiferenciaDias(MiFecha Dos){
    Date FechaUno= new Date(this.YYYY,this.MM,this.DD);
    Date FechaDos= new Date(Dos.YYYY,Dos.MM,Dos.DD);
    long DiaReferencia = 1000 * 60 * 60 * 24;
    long diff = (FechaUno.getTime() - FechaDos.getTime()) / DiaReferencia;
    if(diff>0)
        return String.valueOf(diff);
    else
        return String.valueOf(diff*-1);
    }
    
    private boolean VerificaBisiesto(int YYYY){
        if(YYYY% 100 == 0 && !(YYYY%400 == 0))
            return false;
        if (YYYY %4 == 0)
            return true;
        return false;
    }
    
    private boolean VerificaDia(int dd, int mm, int yyyy){
        switch(mm){
            case 1 : case 3 : case 5 : case 7 : case 8 :  case  10 : case  12 :
                if(dd> 0 && dd<=31)
                    return true;
                return false;
            case 4 : case 6 : case 9: case 11:
                if(dd> 0 && dd<= 30)
                    return true;
                return false;
            case 2 :
                if(this.VerificaBisiesto(yyyy)){
                    if(DD >0 && DD<= 29){
                        return true;
                    }
                }
                if(DD > 0 && DD <= 28)
                    return true;
                return false;
        }
        return false;
    }
      
    public boolean PutDia(int DD){
        if(this.VerificaDia(DD,MM,YYYY)){
            this.DD = DD;
            return true;
        }
        return false;
    }
    
    public boolean PutMes(int MM){
        if((MM > 0 && MM <= 12)&&this.VerificaDia(DD,MM,YYYY)){ 
            this.MM = MM;
            return true;
        }
        return false;
        }
    
    public boolean PutYear(int YYYY){
        if(this.VerificaDia(DD,MM,YYYY)){
            this.YYYY = YYYY;   
            return true;
        }
        return false;
    }
}
    
    public class Interfaz extends JFrame {
    
        JPanel MenuPrincipal,TitulosFechas,MenuFechas,LeyendasFechas,Resultado;
        JLabel Instruccion,TituloUno,TituloDos,LRes,LResLeyenda;
        JTextField TDiaUno,TMesUno, TYearUno, TDiaDos, TMesDos, TYearDos;
        JButton BDiaUno,BMesUno, BYearUno, BDiaDos, BMesDos, BYearDos;
        Border WrapFondo;
        
        public Interfaz(final MiFecha FechaUno, final MiFecha FechaDos){
        
        super("Práctica Tres Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(5,5));
        MenuPrincipal=new JPanel(new BorderLayout(5,5));
        MenuPrincipal.setBorder(BorderFactory.createEmptyBorder(0,60,0,40));
        TitulosFechas= new JPanel(new GridLayout(1,2,5,5));
        TitulosFechas.setBorder(BorderFactory.createEmptyBorder(0,85,0,40));
        LeyendasFechas= new JPanel(new GridLayout(3,1,5,5));
        MenuFechas= new JPanel(new GridLayout(3,4,5,5));
        MenuFechas.setBorder(BorderFactory.createEmptyBorder(0,60,0,40));
        Instruccion= new JLabel("Ingresa dos fechas para calcular el número de días transcurridos entre ambas");
        Instruccion.setHorizontalAlignment(SwingConstants.CENTER);
        Instruccion.setBorder(BorderFactory.createEmptyBorder(10,20,0,20));
        Resultado=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Resultado.setBorder(BorderFactory.createEmptyBorder(0,20,10,20));    
        TituloUno= new JLabel(FechaUno.FechaString(),SwingConstants.CENTER);
        TituloDos= new JLabel(FechaDos.FechaString(),SwingConstants.CENTER);
        LRes=new JLabel(FechaUno.DiferenciaDias(FechaDos),SwingConstants.RIGHT);
        System.out.print(Instruccion.getFont());
        LRes.setFont(new Font("Dialog", Font.BOLD, 24));
        LRes.setForeground(Color.GRAY);
        LResLeyenda=new JLabel(" días transcurridos",SwingConstants.LEFT);
        TDiaUno= new JTextField();
        TMesUno= new JTextField();
        TYearUno=new JTextField();
        TDiaDos= new JTextField();
        TMesDos= new JTextField();
        TYearDos=new JTextField();
        BDiaUno= new JButton("OK");
        BMesUno= new JButton("OK");
        BYearUno= new JButton("OK");
        BDiaDos= new JButton("OK");
        BMesDos= new JButton("OK");
        BYearDos= new JButton("OK");

        TitulosFechas.add(TituloUno);
        TitulosFechas.add(TituloDos);
        LeyendasFechas.add(new JLabel("Día"));
        LeyendasFechas.add(new JLabel("Mes"));
        LeyendasFechas.add(new JLabel("Año"));
        MenuFechas.add(TDiaUno);
        MenuFechas.add(BDiaUno);
        MenuFechas.add(TDiaDos);
        MenuFechas.add(BDiaDos);
        MenuFechas.add(TMesUno);
        MenuFechas.add(BMesUno);
        MenuFechas.add(TMesDos);
        MenuFechas.add(BMesDos);
        MenuFechas.add(TYearUno);
        MenuFechas.add(BYearUno);
        MenuFechas.add(TYearDos);
        MenuFechas.add(BYearDos);
        Resultado.add(LRes);
        Resultado.add(LResLeyenda);
        MenuPrincipal.add("North",TitulosFechas);
        MenuPrincipal.add("West",LeyendasFechas);
        MenuPrincipal.add("Center",MenuFechas);
        this.add("North",Instruccion);
        this.add("Center",MenuPrincipal);
        this.add("South",Resultado);
        this.setSize(500,230);
        this.setResizable(false);
        this.setVisible(true);
  
        BDiaUno.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Integer tmp= Integer.parseInt(TDiaUno.getText());
                    if(FechaUno.PutDia(tmp)){
                        TDiaUno.setForeground(Color.BLUE);
                        TituloUno.setText(FechaUno.FechaString());
                        LRes.setText(FechaUno.DiferenciaDias(FechaDos));
                    }
                    else
                        TDiaUno.setForeground(Color.RED);
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                    }
                }  
        });
        
        BDiaDos.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Integer tmp= Integer.parseInt(TDiaDos.getText());
                    if(FechaDos.PutDia(tmp)){
                        TDiaDos.setForeground(Color.BLUE);
                        TituloDos.setText(FechaDos.FechaString());
                        LRes.setText(FechaUno.DiferenciaDias(FechaDos));
                    }
                    else
                        TDiaDos.setForeground(Color.RED);
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                    }
                }  
        });
        
        BMesUno.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Integer tmp= Integer.parseInt(TMesUno.getText());
                    if(FechaUno.PutMes(tmp)){
                        TMesUno.setForeground(Color.BLUE);
                        TituloUno.setText(FechaUno.FechaString());
                        LRes.setText(FechaUno.DiferenciaDias(FechaDos));
                    }
                    else
                        TMesUno.setForeground(Color.RED);
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                    }
                }  
        });
        
         BMesDos.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Integer tmp= Integer.parseInt(TMesDos.getText());
                    if(FechaDos.PutMes(tmp)){
                        TMesDos.setForeground(Color.BLUE);
                        TituloDos.setText(FechaDos.FechaString());
                        LRes.setText(FechaUno.DiferenciaDias(FechaDos));
                    }
                    else
                        TMesDos.setForeground(Color.BLUE);
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                    }
                }  
        });
         
        BYearUno.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Integer tmp= Integer.parseInt(TYearUno.getText());
                    if(FechaUno.PutYear(tmp)){
                        TYearUno.setForeground(Color.BLUE);
                        TituloUno.setText(FechaUno.FechaString());
                        LRes.setText(FechaUno.DiferenciaDias(FechaDos));
                    }
                    else
                        TYearUno.setForeground(Color.RED);
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                    }
                }  
        });
        
        BYearDos.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Integer tmp= Integer.parseInt(TYearDos.getText());
                    if(FechaDos.PutYear(tmp)){
                        TituloDos.setText(FechaDos.FechaString());
                        TYearDos.setForeground(Color.BLUE);
                        LRes.setText(FechaUno.DiferenciaDias(FechaDos));
                    }
                    else
                        TYearDos.setForeground(Color.RED);
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                    }
                }  
        });
        
        }
    }
    
    public static void main(String[] args) {
        MiFecha FechaStandarUno=new Encapsulamiento().new MiFecha();
        MiFecha FechaStandarDos=new Encapsulamiento().new MiFecha();
        Interfaz Ventana=new Encapsulamiento().new Interfaz(FechaStandarUno,FechaStandarDos);
        Ventana.setVisible(true);
    }
    
}
