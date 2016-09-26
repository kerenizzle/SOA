package hilos;

/**
 * @author Kerenizzle
 * INFO ALUMNO
 * Keren Delgado Nakakawa
 * Sección D01 TAREA JAVA #9
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Hilos implements Runnable {

    private int count,time,id;
    private boolean running;
    private JTextArea message;

    public Hilos(){
        
    }
    
    public Hilos(int id,int secs) {
        this.count =0;
        this.id =id;
        this.running = true;
        this.time=secs;
    }
        
    public void putTime(int secs){
        this.time=secs;
    }
    
    public int getID(){
        return this.id;
    }
    
    public int getTime(){
        return this.time;
    }
          
    public void runThread(JTextArea ms) {
        Thread t = new Thread(this);
        t.start();  
        this.message=ms;
    }

    public synchronized void stop() {
        this.running = false;
    }

    public synchronized void start() {
        this.running = true;
        notify();
    }
        
    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    if(!running) 
                        wait();
                }
                waitXsecs(time);
                message.setText(message.getText()+"\tHilo "+Integer.toString(id)+" Proceso #"+Integer.toString(count)+"\n");

            } 
            catch (InterruptedException e) {}
        }
   }
    
    private void waitXsecs(int secs) {
		try {
			Thread.sleep(secs*1000);
			count++;
		} 
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
		}
	}
    
    
   public class Interfaz extends JFrame {
            
        private final JPanel Fondo,Right,Left;
        private final JTextField TR,TL;
        private final JButton BInicial,BRGo,BLGo,BRSet,BLSet;
        private final JTextArea MessageR, MessageL;
        private final JScrollPane LScroll,RScroll;
        private final JLabel RTimes,LTimes;
        boolean startThreads=false,runningL=true,runningR=true;
        Hilos HL=new Hilos(1,1);
        Hilos HR=new Hilos(2,2);
        
        public Interfaz(){
        
        super("Práctica Nueve de Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Fondo=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Fondo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Right=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Left=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Border Line= BorderFactory.createLineBorder(Color.GRAY,1);
        String tittle="Thread "+String.valueOf(HL.getID());
        Left.setBorder(BorderFactory.createTitledBorder(Line,tittle,TitledBorder.CENTER,TitledBorder.TOP ));
        Left.setPreferredSize(new Dimension(220,500));
        tittle="Thread "+String.valueOf(HR.getID());
        Right.setBorder(BorderFactory.createTitledBorder(Line,tittle,TitledBorder.CENTER,TitledBorder.TOP ));
        Right.setPreferredSize(new Dimension(220,500));
        BInicial=new JButton("Start threads' execution");
        BInicial.setPreferredSize(new Dimension(430,20));
        BRGo=new JButton("Stop");
        BRGo.setEnabled(false);
        BRGo.setBackground(Color.RED);
        BRGo.setForeground(Color.WHITE);
        BRGo.setPreferredSize(new Dimension(215,20));
        BLGo=new JButton("Stop");
        BLGo.setEnabled(false);
        BLGo.setBackground(Color.RED);
        BLGo.setForeground(Color.WHITE);
        BLGo.setPreferredSize(new Dimension(215,20));
        BRSet=new JButton("Set");
        BRSet.setPreferredSize(new Dimension(105,30));
        BRSet.setEnabled(false);
        BRSet.setBackground(Color.BLUE);
        BRSet.setForeground(Color.WHITE);
        BLSet=new JButton("Set");
        BLSet.setPreferredSize(new Dimension(105,30));
        BLSet.setEnabled(false);
        BLSet.setBackground(Color.BLUE);
        BLSet.setForeground(Color.WHITE);
        TR=new JTextField();
        TL=new JTextField();
        TR.setPreferredSize(BRSet.getPreferredSize());
        TL.setPreferredSize(BLSet.getPreferredSize());
        LTimes=new JLabel("Time: "+String.valueOf(HL.getTime())+" secs",SwingConstants.CENTER);
        LTimes.setForeground(Color.GRAY);
        RTimes=new JLabel("Time: "+String.valueOf(HR.getTime())+" secs",SwingConstants.CENTER);
        RTimes.setForeground(Color.GRAY);
        MessageR=new JTextArea(30,30);
        MessageR.setFont(new Font("Monospaced",Font.PLAIN,10));
        MessageL=new JTextArea(30,30);
        MessageL.setFont(new Font("Monospaced",Font.PLAIN,10));
        LScroll=new JScrollPane(MessageL);
        RScroll=new JScrollPane(MessageR);
        Right.add(RTimes);
        Right.add(RScroll);
        Left.add(LTimes);
        Left.add(LScroll);
        Fondo.add(BInicial);
        Fondo.add(TL);
        Fondo.add(BLSet);
        Fondo.add(TR);
        Fondo.add(BRSet);
        Fondo.add(BLGo);
        Fondo.add(BRGo);
        Fondo.add(Left);
        Fondo.add(Right);
        
        this.add(Fondo);
        this.setSize(500,650);
        this.setVisible(true);
        
        TL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TL.setText(null);
            }
        });
        
        TR.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TR.setText(null);
            }
        });
        
        BLSet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Integer tmp= Integer.parseInt(TL.getText());
                    HL.putTime(tmp);
                    LTimes.setText("Time: "+String.valueOf(tmp)+" secs");
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                } 
            }  
        });
        BRSet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Integer tmp= Integer.parseInt(TR.getText());
                    HR.putTime(tmp);
                    RTimes.setText("Time: "+String.valueOf(tmp)+" secs");
                }
                catch(NumberFormatException | HeadlessException e){
                    JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar un número");
                } 
            }  
        });
   
        BInicial.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(!startThreads){
                    HL.runThread(MessageL);
                    HR.runThread(MessageR);
                    startThreads=true;
                    BInicial.setVisible(false);
                    BRGo.setEnabled(true);
                    BLGo.setEnabled(true);
                    BLSet.setEnabled(true);
                    BRSet.setEnabled(true);
                }
            }  
        });
                    
        BLGo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(runningL){
                    HL.stop();
                    BLGo.setText("Start");
                    runningL=false;
                    BLGo.setBackground(Color.GREEN);
                }
                else{
                    HL.start();
                    BLGo.setText("Stop");
                    runningL=true;
                    BLGo.setBackground(Color.RED);

                }
            }  
        });
        
        BRGo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(runningR){
                    HR.stop();
                    BRGo.setText("Start");
                    runningR=false;
                    BRGo.setBackground(Color.GREEN);
                }
                else{
                    HR.start();
                    BRGo.setText("Stop");
                    runningR=true;
                    BRGo.setBackground(Color.RED);
                }
            }  
        });
        
        }
   }
   
   
   
   public static void main(String[] args) {
       Interfaz Ventana=new Hilos().new Interfaz();
        Ventana.setVisible(true);
   }
    
}
