package pl.polsl.michal.urbanek.hotelmanagementsystem.view;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pl.polsl.michal.urbanek.hotelmanagementsystem.controller.BookingController;
import pl.polsl.michal.urbanek.hotelmanagementsystem.controller.RoomController;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.Room;

/**
 * The class contains a graphical interface representing the room view
 *
 * @author Michał Urbanek
 * @version 1.0
 */
public class RoomViewGui extends javax.swing.JFrame {

    private BookingViewGui bookingOptions;
    private RoomController roomController;
    DefaultTableModel model;

    /**
     * Creates new form RoomOptions
     */
    public RoomViewGui() {
        initComponents();
    }

    /**
     * Parameter constructor
     *
     * @param bookingOptions object class bookingOptions
     * @param roomController object class roomController
     */
    public RoomViewGui(BookingViewGui bookingOptions, BookingController bookingController) {
        initComponents();
        this.bookingOptions = bookingOptions;
        this.roomController = bookingController.getRoomController();
        this.model = (DefaultTableModel) jTable1.getModel();
        showAllRooms();
    }

    public BookingViewGui getBookingOptions() {
        return bookingOptions;
    }

    /**
     * a method of hiding all controls on the screen
     */
    public void hideErrorLabelsAndFieldsRooms() {
        JLabel errorLabelsRooms[] = {jLabelId, jLabelNumber, jLabelType, jLabelPrice, jLabelFloor};
        for (JLabel errorLabel : errorLabelsRooms) {
            errorLabel.setVisible(false);
            JTextField fields[] = {jTextFieldType, jTextFieldPrice, jTextFieldFloor, jTextFieldRoomNumber, jTextFieldRoomID};
            for (JTextField field : fields) {
                field.setVisible(false);
            }
        }
    }

    /**
     * method of setting the on-screen controls for adding a room
     */
    public void showLabelsAndFieldsAddRoom() {
        JLabel errorLabelsRooms[] = {jLabelNumber, jLabelType, jLabelPrice, jLabelFloor};
        for (JLabel errorLabel : errorLabelsRooms) {
            errorLabel.setVisible(true);
        }
        JTextField fields[] = {jTextFieldType, jTextFieldPrice, jTextFieldFloor, jTextFieldRoomNumber};
        for (JTextField field : fields) {
            field.setVisible(true);
        }
    }

    /**
     * method of setting the on-screen controls for deleting a room
     *
     */
    public void showLabelsAndFieldsDeleteRoom() {
        JLabel errorLabelsRooms[] = {jLabelId};
        for (JLabel errorLabel : errorLabelsRooms) {
            errorLabel.setVisible(true);
            JTextField fields[] = {jTextFieldRoomID};
            for (JTextField field : fields) {
                field.setVisible(true);
            }
        }
    }

    /**
     * method of setting the on-screen controls for editing a room
     *
     */
    public void showLabelsAndFieldsEditRoom() {
        JLabel errorLabelsRooms[] = {jLabelId, jLabelNumber, jLabelType, jLabelPrice, jLabelFloor};
        for (JLabel errorLabel : errorLabelsRooms) {
            errorLabel.setVisible(true);
            JTextField fields[] = {jTextFieldType, jTextFieldPrice, jTextFieldFloor, jTextFieldRoomNumber, jTextFieldRoomID};
            for (JTextField field : fields) {
                field.setVisible(true);
            }
        }
    }

    /**
     * a method for displaying a list of rooms in the system
     */
    public void showAllRooms() {
        model.getDataVector().removeAllElements();
        List<Room> rooms = roomController.getRoomList().getRooms();
        for (Room room : rooms) {
            Object data[] = {room.getID(), room.getRoomNumber(), room.getNumberOfPeople(), room.getFloor(), room.getPrice()};
            model.addRow(data);
        }
    }

    /**
     * method of cleaning all fields on the screen
     */
    private void clearAllTextField() {

        jTextFieldFloor.setText("");
        jTextFieldPrice.setText("");
        jTextFieldRoomID.setText("");
        jTextFieldType.setText("");
        jTextFieldRoomNumber.setText("");

    }

    /**
     * method displaying the error message
     *
     * @param message string containing error message
     */
    public void errorMessage(String message) {
        JOptionPane.showMessageDialog(this,
                message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * method for adding a new room to the system
     *
     *   * @param roomNumber string containing the room number
     * @param floor string containing the floor
     * @param price string containing the price
     * @param roomType string containing the room type
     */
    public void addRoom(String roomNumber, String roomType, String floor, String price) {
        String stringRoomNumber = jTextFieldRoomNumber.getText();
        String stringFloor = jTextFieldFloor.getText();
        String stringRoomType = jTextFieldType.getText();
        String stringPrice = jTextFieldPrice.getText();

        this.roomController.addRoom(stringRoomNumber, stringFloor, stringPrice, stringRoomType);
    }

    /**
     * method for updating room information
     *
     *  * @param roomNumber string containing the room number
     * @param floor string containing the floor
     * @param price string containing the price
     * @param roomType string containing the room type
     * @param roomID string containing the room iD
     */
    public void editRoom(String roomNumber, String roomType, String floor, String price, String roomID) {
        String stringRoomNumber = jTextFieldRoomNumber.getText();
        String stringFloor = jTextFieldFloor.getText();
        String stringRoomType = jTextFieldType.getText();
        String stringPrice = jTextFieldPrice.getText();
        String stringID = jTextFieldRoomID.getText();

        this.roomController.editRoom(stringID, stringRoomNumber, stringRoomType, stringFloor, stringPrice);
    }

    /**
     * method for deleting the room from system
     *
     * @param id string containing the id
     */
    public void deleteRoom(String id) {
        this.roomController.deleteRoom(id);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldRoomID = new javax.swing.JTextField();
        jTextFieldRoomNumber = new javax.swing.JTextField();
        jTextFieldType = new javax.swing.JTextField();
        jTextFieldFloor = new javax.swing.JTextField();
        jLabelId = new javax.swing.JLabel();
        jLabelNumber = new javax.swing.JLabel();
        jLabelType = new javax.swing.JLabel();
        jLabelFloor = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        jTextFieldPrice = new javax.swing.JTextField();
        jButtonBack = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButtonApply = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Number", "Type", "Floor", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabelId.setText("Id");

        jLabelNumber.setText("Number");

        jLabelType.setText("Type");

        jLabelFloor.setText("Floor");

        jLabelPrice.setText("Price");

        jButtonBack.setText("Back to menu");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add", "Delete", "Edit" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButtonApply.setText("Apply");
        jButtonApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApplyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabelPrice)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabelFloor)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldFloor, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabelType)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabelId)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelNumber)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(72, 72, 72)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButtonApply, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBack)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFloor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFloor)
                    .addComponent(jButtonBack)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonApply))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPrice)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * listener's method responsible for clicking the "Back" button
     *
     * @param evt button action event
     */
    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        this.setVisible(false);
        bookingOptions.setVisible(true);
        bookingOptions.showAllRooms();
        this.dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed
    /**
     * method of setting the appropriate configuration of controls on the screen
     *
     * @param evt combo-box action event
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        hideErrorLabelsAndFieldsRooms();
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                showLabelsAndFieldsAddRoom();
                break;
            case 1:
                showLabelsAndFieldsDeleteRoom();
                break;
            case 2:
                showLabelsAndFieldsEditRoom();
                break;
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed
    /**
     * listener's method responsible for clicking the "Apply" button, depending
     * on the choice of combobox, it is added, edited or deleted
     *
     * @param evt button action event
     */
    private void jButtonApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApplyActionPerformed
        switch (jComboBox1.getSelectedIndex()) {

            case 0:
                addRoom(jTextFieldRoomNumber.getText(), jTextFieldType.getText(), jTextFieldFloor.getText(),
                        jTextFieldPrice.getText());
                break;
            case 1:
                deleteRoom(jTextFieldRoomID.getText());
                break;
            case 2: ;
                editRoom(jTextFieldRoomNumber.getText(), jTextFieldType.getText(), jTextFieldFloor.getText(),
                        jTextFieldPrice.getText(), jTextFieldRoomID.getText());
                break;

        }
        showAllRooms();
        clearAllTextField();
    }//GEN-LAST:event_jButtonApplyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoomViewGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomViewGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomViewGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomViewGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomViewGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonApply;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabelFloor;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelNumber;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldFloor;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldRoomID;
    private javax.swing.JTextField jTextFieldRoomNumber;
    private javax.swing.JTextField jTextFieldType;
    // End of variables declaration//GEN-END:variables
}
