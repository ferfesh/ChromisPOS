//    Chromis POS  - The New Face of Open Source POS
//    Copyright (c) (c) 2015-2016
//    http://www.chromis.co.uk
//
//    This file is part of Chromis POS
//
//     Chromis POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Chromis POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>.

package uk.chromis.pos.sales;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import uk.chromis.basic.BasicException;
import uk.chromis.data.gui.JMessageDialog;
import uk.chromis.data.gui.MessageInf;
import uk.chromis.data.loader.Session;
import uk.chromis.pos.forms.AppConfig;
import uk.chromis.pos.forms.AppLocal;
import uk.chromis.pos.forms.AppProperties;
import uk.chromis.pos.forms.AppView;
import uk.chromis.pos.forms.AppViewConnection;
import uk.chromis.pos.forms.JPanelView;
import uk.chromis.pos.util.AltEncrypter;

/**
 *
 *   
 */
public class JPanelResetPickupId extends JPanel implements JPanelView {

    private Connection con;
    private String sdbmanager;
    private Session session;
    private AppProperties m_props;
    private String SQL;
    private Statement stmt;
    

    /**
     *
     * @param props
     */
    public JPanelResetPickupId(AppView oApp) {
        
        initComponents();

        if (oApp != null) {
            jbtnExit.setVisible(false);
        }
        
    }

    /**
     *
     */
    public void performReset(){
       
       if ("HSQL Database Engine".equals(sdbmanager)) { 
                SQL = "ALTER SEQUENCE PICKUP_NUMBER RESTART WITH 1";
                try {
                    stmt.executeUpdate(SQL);
                } catch (SQLException e){System.out.println(e.getMessage());}
       } else if ("MySQL".equals(sdbmanager)) {
                SQL = "UPDATE PICKUP_NUMBER SET ID=0";
                try {
                    stmt.executeUpdate(SQL);
                } catch (SQLException e){System.out.println(e.getMessage());}            
        } else if ("PostgreSQL".equals(sdbmanager)) {
                SQL = "ALTER SEQUENCE PICKUP_NUMBER RESTART WITH 1";
                try {          
                    stmt.executeUpdate(SQL);
                } catch (SQLException e){System.out.println(e.getMessage());}
        } else if ("Oracle".equals(sdbmanager)) {
                SQL = "ALTER SEQUENCE PICKUP_NUMBER RESTART WITH 1";
                try {
                    stmt.executeUpdate(SQL);
                } catch (SQLException e){System.out.println(e.getMessage());}
        } else if ("Apache Derby".equals(sdbmanager)) {
                SQL = "ALTER TABLE PICKUP_NUMBER ALTER COLUMN ID RESTART WITH 1";
                try {
                    stmt.executeUpdate(SQL);
                } catch (SQLException e){System.out.println(e.getMessage());}                
        } else if ("Derby".equals(sdbmanager)) {
                SQL =  "UPDATE PICKUP_NUMBER SET ID=0";
                try {
                    stmt.executeUpdate(SQL);
                } catch (SQLException e){System.out.println(e.getMessage());} 
        } else {
                SQL = "ALTER SEQUENCE PICKUP_NUMBER RESTART WITH 1";
                try {
                    stmt.executeUpdate(SQL);
                } catch (SQLException e){System.out.println(e.getMessage());}
        }
                                JOptionPane.showMessageDialog(this,"Reset complete.");
                 
   }

    /**
     *
     * @return
     */
    @Override
    public JComponent getComponent() {
        return this;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getTitle() {
        return AppLocal.getIntString("Menu.Resetpickup");
    }

    /**
     *
     * @throws BasicException
     */
    @Override
    public void activate() throws BasicException {
                
       // connect to the database
         String db_user =(AppConfig.getInstance().getProperty("db.user"));
         String db_url = (AppConfig.getInstance().getProperty("db.URL"));
         String db_password = (AppConfig.getInstance().getProperty("db.password"));     
         
         if (db_user != null && db_password != null && db_password.startsWith("crypt:")) {
                // the password is encrypted
                AltEncrypter cypher = new AltEncrypter("cypherkey" + db_user);
                db_password = cypher.decrypt(db_password.substring(6));
        }
        
         try{
            session = AppViewConnection.createSession();
            con = DriverManager.getConnection(db_url,db_user,db_password);                   
            sdbmanager = con.getMetaData().getDatabaseProductName(); 
            stmt = (Statement) con.createStatement(); 
            } catch (BasicException | SQLException e) {    
                
         JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_DANGER, AppLocal.getIntString("database.UnableToConnect"), e));
         System.exit(0);
       }        
      
    }

    /**
     *
     * @return
     */
    @Override
    public boolean deactivate() {
    return(true);
    }      

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtnUpdate = new javax.swing.JButton();
        jbtnExit = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(200, 100));

        jPanel1.setPreferredSize(new java.awt.Dimension(342, 80));

        jbtnUpdate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbtnUpdate.setText(AppLocal.getIntString("label.resetpickup")); // NOI18N
        jbtnUpdate.setMaximumSize(new java.awt.Dimension(70, 33));
        jbtnUpdate.setMinimumSize(new java.awt.Dimension(70, 33));
        jbtnUpdate.setPreferredSize(new java.awt.Dimension(70, 33));
        jbtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateActionPerformed(evt);
            }
        });

        jbtnExit.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbtnExit.setText(AppLocal.getIntString("Button.Exit")); // NOI18N
        jbtnExit.setMaximumSize(new java.awt.Dimension(70, 33));
        jbtnExit.setMinimumSize(new java.awt.Dimension(70, 33));
        jbtnExit.setPreferredSize(new java.awt.Dimension(70, 33));
        jbtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jbtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jbtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateActionPerformed
        performReset();
        
    }//GEN-LAST:event_jbtnUpdateActionPerformed

    private void jbtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitActionPerformed
        deactivate();
        System.exit(0);
    }//GEN-LAST:event_jbtnExitActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnUpdate;
    // End of variables declaration//GEN-END:variables
    
}
