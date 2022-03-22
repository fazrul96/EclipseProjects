import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MyFrame();
			}
		});
	}
	
	private JProgressBar bar;
	private JTable table;
	
	
	public MyFrame() {
		bar = new JProgressBar();
		bar.setMinimum(0);
		bar.setMaximum(100);
		bar.setIndeterminate(false);
		//Add some random rows and columns
		Object[] columns = { "Test", "Test", "Test" };
		Object[][] tableValues = { { "one", "two", "three" },													 { "four", "five", "six" }};
		table = new JTable(tableValues, columns);
		//We want to listen on changes on the model
		table.getModel().addTableModelListener(new MyModelListener());
		add(table, BorderLayout.CENTER);
		add(bar, BorderLayout.SOUTH);
		
		setVisible(true);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		MySwingWorker worker = new MySwingWorker();
		worker.execute();
	}
	public class MyModelListener implements TableModelListener {

		public void tableChanged(TableModelEvent e) {
			//The table model has been changed, update the progressbar
			bar.setValue(bar.getValue() + 1);
		}
	}
	
	public class MySwingWorker extends SwingWorker<Integer, String> {

		@Override
		protected Integer doInBackground() throws Exception {
			//This work will get done in a background thread	
			for (int i=0; i<100; i++) {
				//Get new values for our JTable
				//In this case our new value will be foo
				publish("foo");
				
				//Sleep so we can see changes
				Thread.sleep(200);
			}
                        //We have nothing to return to the done method, we do not care about this part.
			return null;
		}
		@Override
		protected void process(List<String> chunks) {
			//We are now in the event dispatching thread, so update our JTable with our new data
			table.setValueAt(chunks.get(0), 0, 0);
		}
	}
}