/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HospitalGUI;

import Database.Database;
import GUI.DoctorGUI.frmDisease;
import GUI.DoctorGUI.frmMedication;
import GUI.Helpers.UpdatedComboBox;
import GUI.Helpers.UpdatedTable;
import GeneralInfo.Consultation;
import GeneralInfo.Disease;
import GeneralInfo.Prescription;
import Person.Department;
import Person.Patient;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author yusuf
 */
public class PanelNewPatientProfile extends javax.swing.JPanel {

    Patient patient;
    Database database;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    /**
     * Creates new form PanelNewPatientProfile
     */
    public PanelNewPatientProfile(Patient patient, Database database) {
        this.patient = patient;
        this.database = database;
        componentInitializer();
        initComponents();
        listenerInitializer();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPatientProfile = new javax.swing.JPanel();
        pnlPatientProfilee = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();

        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();

        jScrollPane12 = new javax.swing.JScrollPane();

        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        pnlPatientProfilee.setBackground(new java.awt.Color(52, 88, 130));

        jTable4.setRowHeight(25);
        jScrollPane7.setViewportView(jTable4);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));


        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Diseases:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Current Medication:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Recent Medical History:");


        jScrollPane6.setViewportView(jTable6);

        jScrollPane12.setViewportView(jTable7);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N


        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N


        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N


        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);

        jScrollPane8.setViewportView(jTextArea1);

        javax.swing.GroupLayout pnlPatientProfileeLayout = new javax.swing.GroupLayout(pnlPatientProfilee);
        pnlPatientProfilee.setLayout(pnlPatientProfileeLayout);
        pnlPatientProfileeLayout.setHorizontalGroup(
            pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientProfileeLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientProfileeLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlPatientProfileeLayout.createSequentialGroup()
                        .addGroup(pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(127, 127, 127)
                        .addGroup(pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlPatientProfileeLayout.createSequentialGroup()
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(74, 74, 74)
                        .addGroup(pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))))
        );
        pnlPatientProfileeLayout.setVerticalGroup(
            pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientProfileeLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientProfileeLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnlPatientProfileeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(pnlPatientProfileeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(217, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPatientProfileLayout = new javax.swing.GroupLayout(pnlPatientProfile);
        pnlPatientProfile.setLayout(pnlPatientProfileLayout);
        pnlPatientProfileLayout.setHorizontalGroup(
            pnlPatientProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPatientProfilee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlPatientProfileLayout.setVerticalGroup(
            pnlPatientProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientProfileLayout.createSequentialGroup()
                .addComponent(pnlPatientProfilee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1269, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlPatientProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlPatientProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void componentInitializer(){

        jTable4 = new UpdatedTable<Consultation>(new String [] {"Disease", "Date"}, false,2) {
            @Override
            public String[][] createTable() {
                List<Consultation> consultationList = patient.getInfo().getConsultations();
                Collections.sort(consultationList, Collections.reverseOrder());
                setList(consultationList);
                String[][] consultationTable = new String[consultationList.size()][2];
                for (int i = 0; i < consultationTable.length; i++) {
                    if (consultationList.get(i).getDiagnosis() != null && consultationList.get(i).getDiagnosis().size() != 0)
                        consultationTable[i][0] = consultationList.get(i).getDiagnosis().get(0).getName();
                    consultationTable[i][1] = consultationList.get(i).getDate().format(dateTimeFormatter);
                }
                return consultationTable;
            }
        };

        jTable6 = new UpdatedTable<Disease>(new String [] { "Diseases" }, false, 1) {
            @Override
            public String[][] createTable() {
                List<Disease> diseaseList = patient.getActiveDiseases(); //FIXME how do we know it's active?
                setList(diseaseList);
                String[][] diseaseTable = new String[diseaseList.size()][1];
                for (int i = 0; i < diseaseTable.length; i++) {
                    diseaseTable[i][0] = diseaseList.get(i).getName();
                }
                return diseaseTable;
            }
        };

        jTable7 = new UpdatedTable<Prescription>(new String[]{"Current Medication"}, false, 1) {
            @Override
            public String[][] createTable() {
                List<Consultation> consultationList = patient.getInfo().getConsultations();
                List<Prescription> prescriptionList = new ArrayList<>();
                for(Consultation consultation : consultationList){
                    prescriptionList.add(consultation.getPrescription());
                }
                setList(prescriptionList);
                String[][] prescriptionTable = new String[prescriptionList.size()][1];
                for (int i = 0; i < prescriptionTable.length; i++) {
                    if (prescriptionList.get(i) != null && prescriptionList.get(i).getMedications().size() != 0)
                        prescriptionTable[i][0] = prescriptionList.get(i).getMedications().get(0).getName();
                }
                return prescriptionTable;
            }
        };
    }

    private void listenerInitializer(){

        jTable4.update();
        jTable6.update();
        jTable7.update();

        jLabel24.setText(patient.getName());
        jLabel17.setText("Contact Info:");
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("E-mail:   " + patient.getEmail());

        if ( patient.getTelNo() != null )
            jLabel19.setText("Phone Number:   " + patient.getTelNo()); //FIXME
        else
            jLabel19.setText("Phone Number:   ");

        jTextArea1.setText("ADRESS\n");

        jTable4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int row = jTable4.getRow();
                JFrame diseaseFrame = new frmDisease(jTable4.getList().get(row).getDiagnosis().get(0));
                diseaseFrame.setVisible(true);
                diseaseFrame.setLocationRelativeTo(null);
            }
        });

        jTable6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int row = jTable6.getRow();
                JFrame diseaseFrame = new frmDisease(jTable6.getList().get(row));
                diseaseFrame.setVisible(true);
                diseaseFrame.setLocationRelativeTo(null);
            }
        });

        jTable7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int row = jTable7.getRow();
                JFrame medicationFrame = new frmMedication(jTable7.getList().get(row).getMedications().get(0));
                medicationFrame.setVisible(true);
                medicationFrame.setLocationRelativeTo(null);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private UpdatedTable<Consultation> jTable4;
    private UpdatedTable<Disease> jTable6;
    private UpdatedTable<Prescription> jTable7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel pnlPatientProfile;
    private javax.swing.JPanel pnlPatientProfilee;
    // End of variables declaration//GEN-END:variables
}
