
/**
 * PostalCodeCheckerGUI creates a graphical user interface (GUI) TO Validate.
 * Canadian postal codes and display related information such as province and address type (rural or urban).
 * 
 * This program uses swing for GUI components and interogrates the postalCodeChecker
 * class for validation and information retrieval.
 *
 * @author Maitri patel.
 * Student id- 202251226.
 * 
 * @version- 25/11/2024.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PostalCodeCheckerGUI {
    public static void main(String[] args) {
        // Create the main application frame.
        
        JFrame frame = new JFrame("Canadian Postal Code Checker");
        frame.setSize(400, 200); // set frame size.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close application on exit.
        
        // Create a panel to hold all components and add it to the frame.
        
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel); // place components within the panel.
        
        //Make the frame visible.
        frame.setVisible(true);
    }
    /** Places and configures all GUI components within the provided panel.
     * 
     * @param panel the panel where components will be added.
     */
    private static void placeComponents(JPanel panel) {
        // create an instance of postalCodeChecker for validation logic.
        
        PostalCodeChecker checker = new PostalCodeChecker();
        // Set layout to null for absolute positioning.
        
        panel.setLayout(null);
        // Create a label prompting the user to enter a postal code.
        
        JLabel label = new JLabel("Enter Postal Code:");
        label.setBounds(20, 40, 300, 50); // set position and size of the label.
        panel.add(label);
        // Create a text field for postal code input.
        
        JTextField textField = new JTextField(7); // Limit input length to 7 characters.
        textField.setBounds(300, 40, 300, 50);
        panel.add(textField);
        // Create a button to trigger postal code validation.
        
        JButton validateButton = new JButton("Validate");
        validateButton.setBounds(300, 120, 300, 50);
        panel.add(validateButton);
        // Create a text area to display validation results.
        
        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(20, 200, 600, 100);
        resultArea.setEditable(false);
        resultArea.setForeground(Color.RED);
        resultArea.setText(checker.getErrorMessage(textField.getText().trim()));
        panel.add(resultArea);
        // Add an action listener to the validate button to handle validation logic.
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get and normalize the input postal code( trim spaces and convert to uppercase).
                String postalCode = textField.getText().trim().toUpperCase();
                // If the postal code is invalid, display an error message.
                if (!checker.isValidPostalCode(postalCode)) {
                    resultArea.setForeground(Color.RED); // Display errors in red
                    resultArea.setText(checker.getErrorMessage(textField.getText().trim()));
                } else {
                    // If valid, display province and address type.
                    resultArea.setForeground(Color.BLACK); // Display valid results in black.
                    String province = checker.getProvince(postalCode); // Get province info.
                    String addressType = checker.getAddressType(postalCode); // Get address type.
                    resultArea.setText("Province: " + province + "\nAddress: " + addressType);
                }
            }
        });
    }
}