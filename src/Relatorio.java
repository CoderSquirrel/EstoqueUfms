
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class Relatorio extends JPanel {
	  private boolean DEBUG = false;

	  public Relatorio() {
	    super(new GridLayout(1, 0));
	    Object[][] a ={ { "Item", "10", "Fornecedor", new Date(), new Date(),new Integer(5) , new Date(),
	    	new Integer(2) }};
    
	    JTable Entrada = new JTable(new EntradaTable(a));
	    Entrada.setPreferredScrollableViewportSize(new Dimension(500, 70));

	    //Create the scroll pane and add the table to it.
	    
	    JScrollPane scrollPane = new JScrollPane(Entrada);
	    
	    Entrada.getColumnModel().getColumn(0).setPreferredWidth(80);
	    Entrada.getColumnModel().getColumn(1).setPreferredWidth(15);
	    Entrada.getColumnModel().getColumn(2).setPreferredWidth(80);
	    Entrada.getColumnModel().getColumn(3).setPreferredWidth(50);
	    Entrada.getColumnModel().getColumn(4).setPreferredWidth(50);
	    Entrada.getColumnModel().getColumn(5).setPreferredWidth(15);
	    Entrada.getColumnModel().getColumn(6).setPreferredWidth(50);
	    	Entrada.getColumnModel().getColumn(7).setPreferredWidth(15);
    	
	    //Add the scroll pane to this panel.
	    add(scrollPane);
	  }

	  class EntradaTable extends AbstractTableModel {
	    private String[] columnNames = { "Item", "Unidade", 
	        "Fabricante", "Fabricacao", "Validade","Q. Rec", "Recepcao", "Q. Ret" };
	    private Object[][] data;
	    public EntradaTable(Object[][] data) {
			this.data = data;
			
		
			}
	    

	  
	    public int getColumnCount() {
	      return columnNames.length;
	    }

	    public int getRowCount() {
	      return data.length;
	    }

	    public String getColumnName(int col) {
	      return columnNames[col];
	    }

	    public Object getValueAt(int row, int col) {
	      return data[row][col];
	    }

	    /*
	     * JTable uses this method to determine the default renderer/ editor for
	     * each cell. If we didn't implement this method, then the last column
	     * would contain text ("true"/"false"), rather than a check box.
	     */
	    public Class getColumnClass(int c) {
	      return getValueAt(0, c).getClass();
	    }

	    /*
	     * Don't need to implement this method unless your table's editable.
	     */
	    public boolean isCellEditable(int row, int col) {
	      //Note that the data/cell address is constant,
	      //no matter where the cell appears onscreen.
	      if (col < 2) {
	        return false;
	      } else {
	        return true;
	      }
	    }

	    /*
	     * Don't need to implement this method unless your table's data can
	     * change.
	     */
	    public void setValueAt(Object value, int row, int col) {
	      if (DEBUG) {
	        System.out.println("Setting value at " + row + "," + col
	            + " to " + value + " (an instance of "
	            + value.getClass() + ")");
	      }

	      data[row][col] = value;
	      fireTableCellUpdated(row, col);

	      if (DEBUG) {
	        System.out.println("New value of data:");
	        printDebugData();
	      }
	    }

	    private void printDebugData() {
	      int numRows = getRowCount();
	      int numCols = getColumnCount();

	      for (int i = 0; i < numRows; i++) {
	        System.out.print("    row " + i + ":");
	        for (int j = 0; j < numCols; j++) {
	          System.out.print("  " + data[i][j]);
	        }
	        System.out.println();
	      }
	      System.out.println("--------------------------");
	    }
	  }

	  /**
	   * Create the GUI and show it. For thread safety, this method should be
	   * invoked from the event-dispatching thread.
	   */
	  private static void createAndShowGUI() {

	    //Make sure we have nice window decorations.
	  //  JFrame.setDefaultLookAndFeelDecorated(true);

	    //Create and set up the window.
	    JFrame frame = new JFrame("TableDemo");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
	    frame.setBounds(100, 100, 800, 600);

	    frame.getContentPane().setLayout(null);
	    //Create and set up the content pane.
	    Relatorio newContentPane = new Relatorio();
	    newContentPane.setOpaque(true); //content panes must be opaque
	    frame.setContentPane(newContentPane);
frame.setVisible(true);
	    
	  }

	  public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        createAndShowGUI();
	      }
	    });
	  }
	}

	           
	         