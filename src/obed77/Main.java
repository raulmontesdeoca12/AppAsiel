
package obed77;

import javax.swing.UIManager;


public class Main {
    public static void main(String[] args)
    {
      
            new Thread (new Splash_principal()).start();
            for(UIManager.LookAndFeelInfo laf:UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(laf.getName()))
                try {
                UIManager.setLookAndFeel(laf.getClassName());
            } catch (Exception ex) {
                    System.out.println("Error: "+ex);
            }
        }
        }        
       
        
    }

