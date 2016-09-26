package arreglos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * @author Kerenizzle
 * INFO ALUMNO
 * Keren Delgado Nakakawa
 * Sección D01 TAREA JAVA #7
 */

public class Arreglos {
    
        public class Interfaz extends JFrame {
            
        private final JPanel Fondo;
        private final JButton BGenerar;
        private final JLabel LCadena,LSize;
        private final JTextArea Resultado;
        
        char[] Caracteres;
        
        public Interfaz(){
        
        super("Práctica Siete Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Fondo=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Fondo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Fondo.setBackground(Color.WHITE);
        BGenerar=new JButton("Generar cadena aleatoria");
        LCadena= new JLabel("",SwingConstants.CENTER);
        LCadena.setPreferredSize(new Dimension(400,20));
        LCadena.setFont(new Font("Sans Serif",Font.BOLD,14));
        LCadena.setForeground(Color.GRAY);
        LSize= new JLabel("Longitud: ",SwingConstants.CENTER);
        LSize.setPreferredSize(new Dimension(400,20));
        LSize.setFont(new Font("Sans Serif",Font.BOLD,12));
        LSize.setForeground(Color.BLUE);
        Resultado=new JTextArea(8,40);
        Resultado.setOpaque(false);
        Border BCentro= BorderFactory.createLineBorder(Color.GRAY,1);
        Resultado.setBorder(BorderFactory.createTitledBorder(BCentro,"Subcadenas generadas",TitledBorder.CENTER,TitledBorder.TOP ));
        Resultado.setFont(new Font("Sans Serif",Font.PLAIN,12));
        Fondo.add(BGenerar);
        Fondo.add(LCadena);
        Fondo.add(LSize);
        Fondo.add(Resultado);
        this.add(Fondo);
        this.setSize(500,300);
        this.setVisible(true);
        
        BGenerar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Random r=new Random();
                int Size=r.nextInt(10)+1;
                Caracteres= new char[Size];
                for (int c=0;c<Size;c++)
                    Caracteres[c]=(char)(r.nextInt(26)+97);
                LCadena.setText(String.valueOf(Caracteres));
                LSize.setText("Longitud: "+String.valueOf(Size));
                Resultado.setText(new Arreglos().FindSubstrings(Caracteres));
                
            }  
        });
        }    
    }

    public String FindSubstrings(char[] arreglo){
    String Res=new String();
    String tmp=new String();
    String stringArreglo=String.valueOf(arreglo);
    int lon=stringArreglo.length();
    for(int c = 0 ; c <lon; c++ ){
         for(int i = 1 ; i <= lon - c ; i++ ){
            if (c<lon-1){
                tmp += stringArreglo.substring(c, c+i)+" - ";
                if (tmp.length()>60){
                    Res += tmp + "\n";
                    tmp="";
                }
            }
            else
                tmp += stringArreglo.substring(c, c+i);
         }
      }
    Res += tmp;
    return Res;
    }
        
        
    public static void main(String[] args) {
        Interfaz Ventana=new Arreglos().new Interfaz();
        Ventana.setVisible(true);       
    }
}
