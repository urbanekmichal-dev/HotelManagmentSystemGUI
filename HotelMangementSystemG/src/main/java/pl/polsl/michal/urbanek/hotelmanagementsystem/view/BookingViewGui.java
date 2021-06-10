/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.michal.urbanek.hotelmanagementsystem.view;

import pl.polsl.michal.urbanek.hotelmanagementsystem.view.RoomViewGui;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pl.polsl.michal.urbanek.hotelmanagementsystem.controller.BookingController;
import pl.polsl.michal.urbanek.hotelmanagementsystem.controller.RoomController;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.Booking;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.Room;

/**
 * The class contains a graphical user interface and methods responsible for its
 * handling
 *
 * @author Michał Urbanek
 * @version 1.0
 */
public final class BookingViewGui extends javax.swing.JFrame {

    /**
     * private field containing the object bookingController
     */
    private BookingController bookingController;
    /**
     * private field containing the object roomViewGui
     */
    private RoomViewGui roomOptions;
    /**
     *private field containing the object modelBookings 
     */
    DefaultTableModel modelBookings;
    /**
     *private field containing the object modelRooms
     * 
     */
    DefaultTableModel modelRooms;

    private RoomController roomController;
    /**
     * Creates new form BookingOptions
     */
    /**
     * Non-parameter constructor
     */
    public BookingViewGui() {
        initComponents();
        this.bookingController = new BookingController(this);
        this.modelBookings = (DefaultTableModel) jTable1.getModel();
        this.modelRooms = (DefaultTableModel) jTable2.getModel();
        this.roomOptions = new RoomViewGui(this, bookingController);
        showAllBookings();
        hideLabelsAndFieldsRooms();
        showLabelsAndFieldsAddBooking();
        showAllRooms();
    }
    
    public RoomViewGui getRoomViewGui(){
            return this.roomOptions;
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
     * a method for displaying a list of rooms in the system
     *
     */
    public void showAllRooms() {
        modelRooms.getDataVector().removeAllElements();
        List<Room> rooms = bookingController.getRoomList().getRooms();
        for (Room room : rooms) {
            Object data[] = {room.getID(), room.getRoomNumber(), room.getNumberOfPeople(), room.getFloor(), room.getPrice()};
            modelRooms.addRow(data);
        }
    }

    /**
     * a method for displaying a list of bookings in the system
     */
    public void showAllBookings() {
        modelBookings.getDataVector().removeAllElements();
        List<Booking> bookings = bookingController.getBookingList().getBookings();
        for (Booking booking : bookings) {
            Object data[] = {booking.getName(), booking.getSurname(), booking.getID(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getChecInPeriods(), booking.getBookingCost(), booking.getRoom().getRoomNumber()};
            modelBookings.addRow(data);
        }
    }

    /**
     * method for adding a new room to the system
     *
     * @param name string containing the first name
     * @param surname string containing the surname
     * @param checkInDate string containing the check-in date
     * @param checkOutDate string containing the first name
     */
    public void addBooking(String checkInDate, String checkOutDate, String roomID, String name, String surname) {
        bookingController.addBooking(checkInDate, checkOutDate, roomID, name, surname);
    }

    /**
     * method for deleting the room from system
     *
     * @param id string containing the id
     */
    public void deleteBooking(String id) {
        bookingController.deleteBooking(id);
    }

    /**
     * method for updating booking information
     *
     * @param name string containing the first name
     * @param surname string containing the surname
     * @param checkInDate string containing the check-in date
     * @param checkOutDate string containing the first name
     * @param roomID string containing room iD
     * @param bookingID string containing booking ID
     *
     */
    public void editBooking(String checkInDate, String checkOutDate, String roomID, String bookingID, String name, String surname) {
        bookingController.editBooking(checkInDate, checkOutDate, roomID, bookingID, name, surname);
    }

    /**
     * a method of hiding all controls on the screen
     */
    public void hideLabelsAndFieldsRooms() {
        JLabel labelsBooking[] = {jLabelName, jLabelSurname, jLabelBookingId, jLabelRoomId, jLabelCheckIn, jLabelCheckOut,};
        for (JLabel errorLabel : labelsBooking) {
            errorLabel.setVisible(false);
        }
        JTextField fieldsBooking[] = {jTextFieldName, jTextFieldSurname, jTextFieldCheckIn, jTextFieldCheckOut, jTextFieldRoomId, jTextFieldBookingID};
        for (JTextField textFields : fieldsBooking) {
            textFields.setVisible(false);
        }
    }

    /**
     * method of setting the on-screen controls for adding a reservation
     */
    public void showLabelsAndFieldsAddBooking() {
        JLabel labelsBooking[] = {jLabelName, jLabelSurname, jLabelRoomId, jLabelCheckIn, jLabelCheckOut,};
        for (JLabel errorLabel : labelsBooking) {
            errorLabel.setVisible(true);
        }
        JTextField fieldsBooking[] = {jTextFieldName, jTextFieldSurname, jTextFieldCheckIn, jTextFieldCheckOut, jTextFieldRoomId};
        for (JTextField textFields : fieldsBooking) {
            textFields.setVisible(true);
        }
    }

    /**
     * method of setting the on-screen controls for deleting a reservation
     *
     */
    public void showLabelsAndFieldsDeleteBooking() {
        JLabel labelsBooking[] = {jLabelBookingId};
        for (JLabel errorLabel : labelsBooking) {
            errorLabel.setVisible(true);
        }
        JTextField fieldsBooking[] = {jTextFieldBookingID};
        for (JTextField textFields : fieldsBooking) {
            textFields.setVisible(true);
        }
    }

    /**
     * method of setting the on-screen controls for editing a reservation
     *
     */
    public void showLabelsAndFieldsEditBooking() {
        JLabel labelsBooking[] = {jLabelName, jLabelSurname, jLabelBookingId, jLabelRoomId, jLabelCheckIn, jLabelCheckOut,};
        for (JLabel errorLabel : labelsBooking) {
            errorLabel.setVisible(true);
        }
        JTextField fieldsBooking[] = {jTextFieldName, jTextFieldSurname, jTextFieldCheckIn, jTextFieldCheckOut, jTextFieldRoomId, jTextFieldBookingID};
        for (JTextField textFields : fieldsBooking) {
            textFields.setVisible(true);
        }
    }

    /**
     * method of cleaning all fields on the screen
     */
    public void clearAllTextField() {
        JTextField fieldsBooking[] = {jTextFieldName, jTextFieldSurname, jTextFieldCheckIn, jTextFieldCheckOut, jTextFieldRoomId, jTextFieldBookingID};
        for (JTextField textFields : fieldsBooking) {
            textFields.setText("");
        }
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
        jButtonAply = new javax.swing.JButton();
        jButtonShutdown = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldSurname = new javax.swing.JTextField();
        jTextFieldCheckIn = new javax.swing.JTextField();
        jTextFieldCheckOut = new javax.swing.JTextField();
        jTextFieldRoomId = new javax.swing.JTextField();
        jLabelName = new javax.swing.JLabel();
        jLabelSurname = new javax.swing.JLabel();
        jLabelCheckIn = new javax.swing.JLabel();
        jLabelCheckOut = new javax.swing.JLabel();
        jLabelRoomId = new javax.swing.JLabel();
        jButtonRoomMenu = new javax.swing.JButton();
        jLabelBookingId = new javax.swing.JLabel();
        jTextFieldBookingID = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Surname", "Id", "Check-in", "Check-out", "Days", "Final cost", "Room number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButtonAply.setText("Apply");
        jButtonAply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAplyActionPerformed(evt);
            }
        });

        jButtonShutdown.setText("Shutdown");
        jButtonShutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShutdownActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add", "Delete", "Edit" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabelName.setText("Name");

        jLabelSurname.setText("Surname");

        jLabelCheckIn.setText("Check-in");

        jLabelCheckOut.setText("Check-out");

        jLabelRoomId.setText("Room ID");

        jButtonRoomMenu.setText("Room menu");
        jButtonRoomMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRoomMenuActionPerformed(evt);
            }
        });

        jLabelBookingId.setText("Booking id");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelName)
                                .addComponent(jLabelSurname)
                                .addComponent(jLabelCheckIn))
                            .addGap(26, 26, 26)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelBookingId)
                                        .addComponent(jLabelRoomId, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldRoomId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGap(85, 85, 85))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 507, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButtonAply))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelCheckOut)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonShutdown, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRoomMenu)
                        .addGap(518, 518, 518)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldRoomId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRoomId))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBookingId)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelName))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSurname)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCheckIn))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCheckOut)
                            .addComponent(jTextFieldCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonShutdown)
                            .addComponent(jButtonRoomMenu)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAply))))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * method of setting the appropriate configuration of controls on the screen
     *
     * @param evt combo-box action event
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        hideLabelsAndFieldsRooms();
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                showLabelsAndFieldsAddBooking();
                break;
            case 1:
                showLabelsAndFieldsDeleteBooking();
                break;
            case 2:
                showLabelsAndFieldsEditBooking();
                break;
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed
    /**
     * listener's method responsible for clicking the "Room menu" button
     *
     * @param evt button action event
     */
    private void jButtonRoomMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRoomMenuActionPerformed

        roomOptions.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonRoomMenuActionPerformed
    /**
     * listener's method responsible for clicking the "Shutdown" button
     *
     * @param evt button action event
     */
    private void jButtonShutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShutdownActionPerformed
        roomOptions.dispose();
        this.dispose();
    }//GEN-LAST:event_jButtonShutdownActionPerformed

    /**
     * listener's method responsible for clicking the "Apply" button, depending
     * on the choice of combobox, it is added, edited or deleted
     *
     * @param evt button action event
     */
    private void jButtonAplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAplyActionPerformed
        //hideLabelsAndFieldsRooms();
        switch (jComboBox1.getSelectedIndex()) {

            case 0:
                addBooking(jTextFieldCheckIn.getText(), jTextFieldCheckOut.getText(), jTextFieldRoomId.getText(), jTextFieldName.getText(), jTextFieldSurname.getText());
                break;
            case 1:
                deleteBooking(jTextFieldBookingID.getText());
                break;
            case 2:
                showLabelsAndFieldsEditBooking();
                editBooking(jTextFieldCheckIn.getText(), jTextFieldCheckOut.getText(),
                        jTextFieldRoomId.getText(), jTextFieldBookingID.getText(), jTextFieldName.getText(), jTextFieldSurname.getText());
                break;

        }
        showAllBookings();
        clearAllTextField();
    }//GEN-LAST:event_jButtonAplyActionPerformed

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
            java.util.logging.Logger.getLogger(BookingViewGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingViewGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingViewGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingViewGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingViewGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAply;
    private javax.swing.JButton jButtonRoomMenu;
    private javax.swing.JButton jButtonShutdown;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabelBookingId;
    private javax.swing.JLabel jLabelCheckIn;
    private javax.swing.JLabel jLabelCheckOut;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelRoomId;
    private javax.swing.JLabel jLabelSurname;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldBookingID;
    private javax.swing.JTextField jTextFieldCheckIn;
    private javax.swing.JTextField jTextFieldCheckOut;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldRoomId;
    private javax.swing.JTextField jTextFieldSurname;
    // End of variables declaration//GEN-END:variables
}
