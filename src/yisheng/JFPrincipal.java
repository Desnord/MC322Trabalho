/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 *
 * @author Thomas
 */
public class JFPrincipal extends JFrame
{    
    private JPPrincipal jpp = new JPPrincipal();
    
    public JFPrincipal()
    {
        super("YiSheng - 医生 - Médico");
        setSize(500,500);
        setLocationRelativeTo(null);
        
        URL iconURL = getClass().getResource("./images/medic.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setContentPane(jpp);
    }
}
