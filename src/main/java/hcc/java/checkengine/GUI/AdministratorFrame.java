package hcc.java.checkengine.GUI;

import hcc.java.checkengine.Index;
import hcc.java.checkengine.Models.IndexFile;
import hcc.java.checkengine.Utilities.DataAccess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorFrame {

    private JFrame frame;
    private JScrollPane scrollPane;
    private static boolean isOpen = false;
    private Index index = DataAccess.loadIndex();
    private String tableData[][] = getTableData();
    private String[] columnNames = new String[]{"File Name", "Status"};
    private JTable indexTable = new JTable(tableData, columnNames);
    private int[] selectedRows = new int[] {};


    /**
     * Create the application.
     */
    public AdministratorFrame() throws IOException {
        if (!isOpen) {
            initialize();
            this.frame.setVisible(true);
            isOpen = true;
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 714, 540);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isOpen = false;
            }
        });

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenuButton = new JMenu("File");
        menuBar.add(fileMenuButton);

        JMenuItem regenerateIndexMenuButton = new JMenuItem("Regenerate Index");
        fileMenuButton.add(regenerateIndexMenuButton);

        JMenuItem exitMenuButton = new JMenuItem("Exit");
        fileMenuButton.add(exitMenuButton);

        JMenu editMenuButton = new JMenu("Edit");
        menuBar.add(editMenuButton);

        JMenuItem addFileMenuButton = new JMenuItem("Add File");
        editMenuButton.add(addFileMenuButton);

        JMenuItem removeFileMenuButton = new JMenuItem("Remove File");
        editMenuButton.add(removeFileMenuButton);
        frame.getContentPane().setLayout(null);

        JLabel maintenanceLabel = new JLabel("File Maintenance ");
        maintenanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        maintenanceLabel.setBounds(275, 11, 156, 25);
        frame.getContentPane().add(maintenanceLabel);

        scrollPane = new JScrollPane(indexTable);
        scrollPane.setBounds(50, 75, 600, 300);
        frame.add(scrollPane);

        //Action listeners
        addFileMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Adding file to index...");
            }
        });

        removeFileMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Removing file to index...");
            }
        });
    }

    private String[][] getTableData() {
        if (index.getIndexFiles().size() == 0) {
            return new String[][] { { "No Data", "No Data" } };
        } else {
            var indexedFiles = new ArrayList<List<String>>();
            for (IndexFile file : index.getIndexFiles()) {
                indexedFiles.add(new ArrayList<>() {
                    {
                        add(file.getFilePath());
                        add(file.getFileStatus().toString());
                    }});
            }
            //Map ArrayList to 2D array.
            var tableData = indexedFiles.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
            return tableData;
        }
    }
}