package halfback.cmpm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;

import halfback.cmpm.controller.ChronicleSelectionListener;
import halfback.cmpm.controller.ChronicleTableModel;

public class ChronicleList extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTable _table;

	public ChronicleList(ChronicleTableModel model, ChronicleDesktop desktop, ChronicleDescription description) {
        super();
        setLayout(new BorderLayout());
        _table = new JTable(model);
        //_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        _table.getColumnModel().getColumn(1).setMinWidth(250);
        _table.setAutoCreateRowSorter(true);
        JScrollPane pane = new JScrollPane(_table);
        add(pane, BorderLayout.CENTER);
        _table.getSelectionModel().addListSelectionListener(new ChronicleSelectionListener(_table, desktop, description));
        _table.getRowSorter().addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent e) {
                _table.clearSelection();
            }
        });
    }
}