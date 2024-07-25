//package chatting.application;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;

public class Server implements ActionListener {

    static JFrame f = new JFrame();
    public static JTextField t1 ;
    public static JPanel p2;
    static Box vertical = Box.createVerticalBox();

    public static DataOutputStream dataout;
    Server ()
    {
        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,320,50);//Setting up the Panel Location based on the Frame Size
        p1.setLayout(null);
        f.add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon/left-arrow.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(10,13,25,25);
        p1.add(back);

        ImageIcon j1 = new ImageIcon(ClassLoader.getSystemResource("Icon/man.png"));
        Image j2 = j1.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
        ImageIcon j3 = new ImageIcon(j2);
        JLabel back2 = new JLabel(j3);
        back2.setBounds(37,5,40,40);
        p1.add(back2);

        ImageIcon k1 = new ImageIcon(ClassLoader.getSystemResource("Icon/video.png"));
        Image k2 = k1.getImage().getScaledInstance(27,27,Image.SCALE_DEFAULT);
        ImageIcon k3 = new ImageIcon(k2);
        JLabel back3 = new JLabel(k3);
        back3.setBounds(200,10,30,30);
        p1.add(back3);

        ImageIcon l1 = new ImageIcon(ClassLoader.getSystemResource("Icon/phone.png"));
        Image l2 = l1.getImage().getScaledInstance(27,27,Image.SCALE_DEFAULT);
        ImageIcon l3 = new ImageIcon(l2);
        JLabel back4 = new JLabel(l3);
        back4.setBounds(243,13,25,25);
        p1.add(back4);

        JLabel text1 = new JLabel("Yash Joshi");
        text1.setBounds(84,3,80,30);
        text1.setForeground(Color.WHITE);
        text1.setFont(new Font("SAN_SERIF",Font.BOLD,12));
        p1.add(text1);

        JLabel text2 = new JLabel("Online");
        text2.setBounds(84,17,30,30);
        text2.setForeground(Color.WHITE);
        text2.setFont(new Font("SAN_SERIF",Font.PLAIN,10));
        p1.add(text2);

        ImageIcon m1 = new ImageIcon(ClassLoader.getSystemResource("Icon/3icon.png"));
        Image m2 = m1.getImage().getScaledInstance(7,20,Image.SCALE_DEFAULT);
        ImageIcon m3 = new ImageIcon(m2);
        JLabel back5 = new JLabel(m3);
        back5.setBounds(270,13,25,25);
        p1.add(back5);

        p2 = new JPanel();
        p2.setBounds(5,55,295,420);
        p2.setBackground(new Color(80,141,78));
        p2.setLayout(new BorderLayout());
        f.add(p2);

        t1 =new JTextField();
        t1.setBounds(5,480,260,30);
        f.add(t1);
        t1.setFont(new Font("SAN_SERIF",Font.ROMAN_BASELINE,15));


        ImageIcon n1 = new ImageIcon(ClassLoader.getSystemResource("Icon/send.png"));
        Image n2 = n1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon n3 = new ImageIcon(n2);
        JButton b1 = new JButton(n3);
        b1.setBounds(270,480,30,30);
        b1.addActionListener(this);
        f.add(b1);


        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        f.setLocation(100,100);
        f.setSize(320,550);
        f.setVisible(true);
        f.setResizable(false);
        f.getContentPane().setBackground(new Color(214, 239, 216));
        //Adding the AWT package
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        new Server();
        try {
            ServerSocket skt = new ServerSocket(6000);
            while (true) {
                Socket s = skt.accept();
                DataInputStream datain = new DataInputStream(s.getInputStream());
                dataout = new DataOutputStream(s.getOutputStream());

                while (true) {
                    String msg = datain.readUTF();
                    JPanel panel = formateLabel(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    vertical.add(Box.createVerticalStrut(15));

                    SwingUtilities.invokeLater(() -> {
                        p2.add(vertical, BorderLayout.PAGE_START);
                        f.revalidate();
                        f.repaint();
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

               try { if(t1.getText().isEmpty()){}
                else {
                String out = t1.getText();
                JPanel panel;
                panel = formateLabel(out);
                //panel.setBackground(new Color(80,141,78));


                JPanel right = new JPanel(new BorderLayout());
                right.setBackground(new Color(80,141,78));
                right.add(panel, BorderLayout.LINE_END);

                vertical.add(right);
                vertical.add(Box.createVerticalStrut(15));

                p2.add(vertical, BorderLayout.PAGE_START);

                dataout.writeUTF(out);
                t1.setText("");
                f.repaint();
                f.revalidate();
                }}
               catch (Exception f)
               {
                   f.printStackTrace();
               }

    }

    public static JPanel formateLabel  (String out )
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setBackground(new Color(11,11,11));
        JLabel output = new JLabel("<html> <p style=\"width:100px\">" +out+ " </html>" );
        output.setFont(new Font("Tahoma",Font.PLAIN,15));
        //output.setBorder(new EmptyBorder(0,0,5,0));
        output.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
        panel.add(output);
        t1.setText("");


        Calendar clca = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        JLabel time = new JLabel(sdf.format(clca.getTime()));

        panel.add(time);


        return panel;

    }
}
