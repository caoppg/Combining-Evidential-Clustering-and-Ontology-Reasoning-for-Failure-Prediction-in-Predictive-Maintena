package halfback.cmpm.view;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Set;

import halfback.cmpm.controller.ChronicleTableModel;
import halfback.cmpm.controller.ChronicleViewListener;
import halfback.cmpm.model.Chronicle;

public class MainFrame extends JFrame /*implements ActionListener*/ {
    
    private static final long serialVersionUID = 1L;
    private Set<Chronicle> _chronicles;
    static int current = 0;

	public MainFrame(Set<Chronicle> chronicles) {
        super("HALFBACK Chronicle Mining for Predictive Maintenance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _chronicles = chronicles;

        JPanel content = new JPanel(new GridBagLayout());
        setContentPane(content);
        content.setBackground(Color.BLACK);
        int inset = 100;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset * 2,
                  screenSize.height - inset * 2);

        
        GridBagConstraints c = new GridBagConstraints();

        MenuBar menu = new MenuBar(this);
        setJMenuBar(menu);
        
        ChronicleTableModel model = new ChronicleTableModel(_chronicles);
        ChronicleDescription description = new ChronicleDescription();
        ChronicleView.setInternalFrameListener(new ChronicleViewListener(description, model));
        ChronicleDesktop desktop = new ChronicleDesktop();
        ChronicleList list = new ChronicleList(model, desktop, description);
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

        description.setPreferredSize(new Dimension(2 * (screenSize.width - 2 * inset) / 3, 2 * (screenSize.height - inset * 2) / 5));
        list.setPreferredSize(new Dimension((screenSize.width - 2 * inset) / 3, 2 * (screenSize.height - inset * 2) / 5));
        desktop.setPreferredSize(new Dimension(screenSize.width - inset * 2, 3 * (screenSize.height - inset * 2) / 5));
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        content.add(list, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        content.add(description, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weighty = 1.0;
        content.add(desktop, c);

        pack();
        setVisible(true);
    }
}