package othello;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sun.glass.events.KeyEvent;

import javax.swing.JPanel;

/**
 * This class creates and manages a network connection UI. Add yourself an
 * extra @author line.
 * 
 * @author Daniel Cormier
 * @author Tyson Moyes
 * @version 1.3.1
 * @since 1.8.0_291
 * @see OthelloViewController
 */

public class OthelloNetworkModalViewController extends JDialog {
    /** Has the user pressed the connect button? */
    Boolean hasConnected = false;

    /** Instance of the inner class Controller for handling UI events */
    Controller handler = new Controller();

    /**
     * Instance of the ComboBox you need to build. You'll want to change this
     * comment.
     */
    // NOTE: In the case of this and other UI elements, you may rename them as you
    // see fit.
    // Just be careful that you catch every instance of it if you do.
    JComboBox portInput;

    /** Textfield for entering the FQDN/IP address */
    JTextField addressInput;

    /** Textfield for entering the user's name. */
    JTextField nameInput;

    /** Label for reporting error messages pertaining to user names. */
    JLabel nameError;

    /** Label for reporting error messages pertaining to addresses. */
    JLabel addressError;

    /** Label for reporting error messages pertaining to port numbers */
    JLabel portError;

    // NOTE: We're not doing much with those labels for now. But we will be for part
    // 2.

    public OthelloNetworkModalViewController(JFrame mainView) {
        // The superclass needs to know what JFrame it's intercepting.
        // The title doesn't matter, nobody will see it.
        super(mainView, "Enter Network Address", true);

        setUndecorated(true);

        // This will hold your UI. Of course you may rename it.
        Container networkPanel = getContentPane();

        // Your UI code goes here. I suggest using a GridBagLayout, but you can use
        // whatever you like, so long as it doesn't take pixels for input.
        JPanel pane = new JPanel(new GridBagLayout());
        pane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));

        GridBagConstraints c = new GridBagConstraints();

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setDisplayedMnemonic(KeyEvent.VK_A);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = GridBagConstraints.REMAINDER;
        pane.add(addressLabel, c);

        addressInput = new JTextField(20);
        addressLabel.setLabelFor(addressInput);
        c.gridx = 1;
        c.weightx = 1;
        c.gridy = 0;
        pane.add(addressInput, c);

        addressError = new JLabel("Address Error Message");
        addressError.setForeground(Color.RED);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(addressError, c);

        JLabel portLabel = new JLabel("Port:");
        portLabel.setDisplayedMnemonic(KeyEvent.VK_P);
        c.gridx = 0;
        c.gridy = 2;
        pane.add(portLabel, c);

        String portOptions[] = { "", "32300", "42300", "52300" };
        portInput = new JComboBox<>(portOptions);
        portLabel.setLabelFor(portInput);
        portInput.setSelectedIndex(0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(portInput, c);

        portError = new JLabel("Port error message");
        portError.setForeground(Color.RED);
        c.gridx = 1;
        c.gridy = 3;
        pane.add(portError, c);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setAlignmentX(RIGHT_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 4;
        pane.add(nameLabel, c);

        nameInput = new JTextField(20);
        nameLabel.setLabelFor(nameInput);
        c.gridx = 1;
        c.weightx = 1;
        c.gridy = 4;
        pane.add(nameInput, c);

        nameError = new JLabel("Name error message");
        nameError.setForeground(Color.RED);
        c.gridx = 1;
        c.gridy = 5;
        pane.add(nameError, c);

        JButton connectButton = new JButton("Connect");
        connectButton.setActionCommand("C");
        connectButton.addActionListener(handler);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("X");
        cancelButton.addActionListener(handler);

        c.gridx = 1;
        c.gridy = 6;
        pane.add(connectButton, c);

        c.gridx = 2;
        c.gridy = 6;
        pane.add(cancelButton, c);

        networkPanel.add(pane);
        // And when you're done, of course you'll pack it.
        pack();
    }

    /**
     * A basic get method to return address input.
     * 
     * @return The FQDN/IP address from the user.
     */
    public String getAddress() {
        if (hasConnected) {
            return (addressInput.getText());
        } else {
            // You're free to return whatever error message you like.
            // The code may never wind up using this, but it gives you a fallback.
            return ("Error:  Invalid Address or attempt cancelled.");
        }
    }

    /**
     * A basic get method to return name input.
     * 
     * @return The user's name.
     */
    public String getName() {
        return (nameInput.getText());
    }

    /**
     * A basic get method to return the selected port.
     * 
     * @return The selected port, or custom input.
     */

    public int getPort() {
        // I stored the ports as Strings in the ComboBox. You may need to convert them
        // safely to integers.

        // Important note: I am NOT returning safe data in this method.
        // It would be a good idea to do it here.

        int portnum = -1;
        if (hasConnected) {
            try {
                portnum = Integer.parseInt((String) portInput.getSelectedItem());
            } catch (NumberFormatException nfe) {
                portnum = -1;
            }

            return portnum;
        }
        // In my case, if the port number is invalid for ANY reason, I return -1.
        return -1;
    }

    /**
     * This method hides the modal when the input process is complete.
     */

    public void hideModal() {
        // Add any code that you may want to do after the user input has been processed
        // and you're figuratively closing up the shop.
        // Once this method is done, control will return to the code that set this
        // dialog
        // to be visible.

        // As a suggestion, you may want to reset error messages
        // and potentially clear out the input for part 2, especially if cancel was
        // pressed.
        // It's up to you, of course.

        setVisible(false);

    }

    /**
     * For use in the main UI: Has the user pressed the connect or cancel button?
     * 
     * @return True if connect, false if cancel.
     */

    public boolean pressedConnect() {
        return hasConnected;
    }

    /**
     * The Controller that manages all UI events in the dialog.
     * 
     * @author Daniel Cormier
     * @version 1.3.1
     * @since 1.8.0_291
     * @see OthelloViewController
     */

    private class Controller implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String s = ae.getActionCommand();

            // User selects "Connect": I set "C" to be my Connect button's "action command".
            if ("C".equals(s)) // Connect option.
            {
                hasConnected = true;

                // Do error checking and report error messages as required
                // in part 2.
            }
            // I set "X" to be my Cancel button's "action command":
            else {
                hasConnected = false;
            }

            // In either case, for NOW, we're done with the modal, so let's call our
            // cleanup method and return control to the main UI

            hideModal();
        }
    }
}
