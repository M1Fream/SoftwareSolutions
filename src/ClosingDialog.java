/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


import javax.swing.*;

import java.beans.*; //property change stuff
import java.awt.Font;
import java.awt.event.*;

/* 1.4 example used by DialogDemo.java. */
public class ClosingDialog extends JDialog implements ActionListener, PropertyChangeListener {
	
    private String typedText = null; //Contains text typed by user
    private JTextField textField; //TextField for user to type text into

    private String password;
    private JOptionPane optionPane;

    //Strings to be used in optionPane
    private String btnString1 = " Enter ";
    private String btnString2 = " Cancel ";

    private GUI myGUI;
    
    //Set password to pass
    void setPassword(String pass){
    	password=pass;
    }
    
    /**
     * Returns null if the typed string was invalid;
     * otherwise, returns the string as the user entered it.
     * @return String typedText
     */
    public String getValidatedText() {
        return typedText;
    }

    /** Creates the reusable dialog. 
     * @param GUI gui
     * */
    public ClosingDialog(GUI gui) {
        super(gui, true);
        myGUI=gui;
        
        //Set title
        this.setTitle("Save and quit");

        textField = new JTextField(10);
        
        //Set font
        textField.setFont(new Font("Times New Roman",Font.PLAIN,32));

        //Create an array of the text and components to be displayed.
        String msgString1 = "Please enter the password to save and exit: ";

        Object[] array = {msgString1, textField};

        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options = {btnString1, btnString2};

        //Create the JOptionPane.
        optionPane = new JOptionPane(array,
                                    JOptionPane.PLAIN_MESSAGE,
                                    JOptionPane.YES_NO_OPTION,
                                    null,
                                    options,
                                    options[0]);
        //Set font
        UIManager.put("OptionPane.messageFont", new Font("Times New Roman",Font.PLAIN,32));
        UIManager.put("OptionPane.buttonFont", new Font("Times New Roman",Font.PLAIN,32));
        
        //Make this dialog display it.
        setContentPane(optionPane);
        
        //Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                    optionPane.setValue(new Integer(
                                        JOptionPane.CLOSED_OPTION));
            }
        });
        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        textField.addActionListener(this);

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        
        //Set size of dialog and set resizable to false
        setSize(600, 250);
        this.setResizable(false);
        
        //Set location
        this.setLocation(GUI.guiWidth/2-300, GUI.guiLength/2-125);
        
        //Make invisible until button is pressed
        this.setVisible(false);
    }

    /** This method handles events for the text field. */
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    /** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if (isVisible()
         && (e.getSource() == optionPane)
         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }
            //Reset the JOptionPane's value.
            //If you don't do this, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);
            if (btnString1.equals(value)) {
                    typedText = textField.getText();
                if (password.equals(typedText)) {
                    //we're done; clear and dismiss the dialog, ask user if they would like to sort the data
                	clearAndHide();
                	SortingDialog sd = new SortingDialog(myGUI);
                	this.close();
                } else {
                    //text was invalid
                    textField.selectAll();
                    JOptionPane.showMessageDialog(
                                    ClosingDialog.this,
                                    "Sorry, that isn't the password.",
                                    "Wrong password",
                                    JOptionPane.ERROR_MESSAGE);
                    typedText = null;
                    textField.setText(null);
                    textField.requestFocusInWindow();
                }
            } else { //user closed dialog or clicked cancel
                typedText = null;
                clearAndHide();             
            }
        }
    }

    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        textField.setText(null);
        this.setVisible(false);
    }
    
    public void close(){
    	this.setVisible(false);
    	this.dispose();
    }

}