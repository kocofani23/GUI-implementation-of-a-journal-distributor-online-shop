package term_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;

public class JournalSubscriptionGUI extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2759442841179145559L;
	private JComboBox<String> journalList;
    private JTextField nameField;
    private JTextField addressField;
    private JComboBox<String> paymentMethod;
    private JPanel paymentDetailsPanel;
    private JButton nextButton;
    private JButton subscribeButton;
    private JButton generateReportButton;
    private JPanel amountPanel;
    private JPanel userDetailsPanel;
    private JTextField userNameField;
    private JTextField amountToAddField;
    private JLabel amountToAddLabel;
    private JLabel emptyLabel;
    private JButton addMoneyButton;
    
    
    
    //fields used to create distributor class

    private Journal journal;
    private Subscription subscription;
    private Vector<Subscriber> subscribers = new Vector<>();;
    private Hashtable<String, Journal> journals = new Hashtable<>();
    private Distributor distributor = new Distributor(journals, subscribers);
    
    
    //fields used for corporation subscriber
    Corporation corporation;
    JLabel accountLabel;
    JTextField accountField;
    JLabel dayLabel;
    JTextField dayField;
    JLabel monthLabel;
    JTextField monthField;
    JLabel yearLabel;
    JTextField yearField;
    JLabel bankLabel;
    JTextField bankField;
    JLabel codeLabel;
    JTextField codeField;
    
    //fields used for individual subscriber
    Individual individual;
    JLabel creditCardLabel;
    JTextField creditCardField;
    JLabel cvvLabel;
    JTextField cvvField;
    JLabel expireMonthLabel;
    JTextField expireMonthField;
    JLabel expireYearLabel;
    JTextField expireYearField;
    
 // Creating Journal instances
    Journal A = new Journal("The New York Times", "ISSN001", 12, 10.50);
    Journal B = new Journal("Car Magazine", "ISSN002", 6, 8.75);
    Journal C = new Journal("The Independent", "ISSN003", 4, 12.20);
    Journal D = new Journal("NatGeo Wild", "ISSN004", 24, 15.30);
    Journal E = new Journal("MTV", "ISSN005", 12, 9.99);
    Journal F = new Journal("Nature", "ISSN006", 6, 11.75);
    Journal G = new Journal("The Sun", "ISSN007", 4, 14.20);
    Journal H = new Journal("Forbes", "ISSN008", 24, 16.30);
    Journal I = new Journal("Vogue", "ISSN009", 12, 8.99);
    Journal J = new Journal("Smithsonian", "ISSN010", 6, 10.75);
	private JButton searchButton;
    

    public JournalSubscriptionGUI() {
    	
        setTitle("Journal E-Shop");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("icon.jpg"));
        setIconImage(icon.getImage());

        // Create a panel for subscription components
        JPanel subscriptionPanel = new JPanel(new GridLayout(6, 2));

        JLabel journalLabel = new JLabel("Choose Journal:");
        String[] journalsForDisplay = {"Choose...",
        		"The New York Times, ISSN001, Copies: 12, Price: 10.50 €",
        		"Car Magazine, ISSN002, Copies: 6, Price: 8.75 €" ,
        		"The Independent, ISSN003, Copies: 4, Price: 12.20 €",
        		"NatGeo Wild, ISSN004, Copies: 24, Price: 15.30 €",
        		"MTV, ISSN005, Copies: 12, Price: 9.99 €",
        		"Nature, ISSN006, Copies: 6, Price: 11.75 €",
        		"The Sun, ISSN007, Copies: 4, Price: 14.20 €",
        		"Forbes, ISSN008, Copies: 24, Price: 16.30 €",
        		"Vogue, ISSN009, Copies: 12, Price: 8.99 €",
        		"Smithsonian, ISSN010, Copies: 6, Price: 10.75 €"}; 
        
        journals.put(A.getIssn(), A);
        journals.put(B.getIssn(), B);
        journals.put(C.getIssn(), C);
        journals.put(D.getIssn(), D);
        journals.put(E.getIssn(), E);
        journals.put(F.getIssn(), F);
        journals.put(G.getIssn(), G);
        journals.put(H.getIssn(), H);
        journals.put(I.getIssn(), I);
        journals.put(J.getIssn(), J);
        

        
        journalList = new JComboBox<>(journalsForDisplay);
        journalList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
						showJournalChosen();
			}
        		
        });

        JLabel nameLabel = new JLabel("Name Surname:");
        nameField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        
        

        JLabel paymentLabel = new JLabel("Payment Method:");
        String[] paymentMethods = {"Choose...",
        		"Individual", 
        		"Corporation"};
        paymentMethod = new JComboBox<>(paymentMethods);
        paymentMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaymentDetailsPanel();
            }
        });
       

        emptyLabel = new JLabel(); // Placeholder for layout

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        

        subscriptionPanel.add(journalLabel);
        subscriptionPanel.add(journalList);
        subscriptionPanel.add(nameLabel);
        subscriptionPanel.add(nameField);
        subscriptionPanel.add(addressLabel);
        subscriptionPanel.add(addressField);
        subscriptionPanel.add(paymentLabel);
        subscriptionPanel.add(paymentMethod);
        subscriptionPanel.add(emptyLabel);
        subscriptionPanel.add(nextButton);        

        // Panel for payment details (initially hidden)
        paymentDetailsPanel = new JPanel(new GridLayout(7, 2));
        paymentDetailsPanel.setVisible(false);

        JLabel paymentDetailsLabel = new JLabel("Payment Details:");
        paymentDetailsPanel.add(paymentDetailsLabel);

        // Add subscription components to the frame
        setLayout(new BorderLayout());
        add(subscriptionPanel, BorderLayout.NORTH);
        add(paymentDetailsPanel, BorderLayout.CENTER);

        setVisible(true);
     
        
        generateReportButton = new JButton("Generate Report");
        
        generateReportButton.addActionListener(e -> {
            generateReport();
        });
        
        subscriptionPanel.add(generateReportButton);
        
        userDetailsPanel = new JPanel(new GridLayout(1, 2));
        userDetailsPanel.setVisible(true);;

        JLabel nameLabelForSubscription = new JLabel("Name Surname:");
        searchButton = new JButton("Search");
        userNameField = new JTextField();
        userDetailsPanel.add(nameLabelForSubscription);
        userDetailsPanel.add(userNameField);
        userDetailsPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String nameSurname = (String)nameLabelForSubscription.getText();
                search(nameSurname);
            }
        });
        
        subscriptionPanel.add(userDetailsPanel);
        

        
    }
    
    private void showJournalChosen() {
    	
    	String selectedJournal = (String) journalList.getSelectedItem();
    	if(selectedJournal.equals("The New York Times, ISSN001, Copies: 12, Price: 10.50 €")) {
    		journal = new Journal("The New York Times", "ISSN001", 12, 10.50);
    	}
    	else if(selectedJournal.equals("Car Magazine, ISSN002, Copies: 6, Price: 8.75 €")) {
    		journal = new Journal("Car Magazine", "ISSN002", 6, 8.75);
    	}
    	else if(selectedJournal.equals("The Independent, ISSN003, Copies: 4, Price: 12.20 €")) {
    		journal = new Journal("The Independent", "ISSN003", 4, 12.20);
    	}
    	else if(selectedJournal.equals("NatGeo Wild, ISSN004, Copies: 24, Price: 15.30 €")) {
    		journal = new Journal("NatGeo Wild", "ISSN004", 24, 15.30);
    	}
    	else if(selectedJournal.equals("MTV, ISSN005, Copies: 12, Price: 9.99 €")) {
    		journal = new Journal("MTV", "ISSN005", 12, 9.99);
    	}
    	else if(selectedJournal.equals("Nature, ISSN006, Copies: 6, Price: 11.75 €")) {
    		journal = new Journal("Nature", "ISSN006", 6, 11.75);
    	}
    	else if(selectedJournal.equals("The Sun, ISSN007, Copies: 4, Price: 14.20 €")) {
    		journal = new Journal("The Sun", "ISSN007", 4, 14.20);
    	}
    	else if(selectedJournal.equals("Forbes, ISSN008, Copies: 24, Price: 16.30 €")) {
    		journal = new Journal("Forbes", "ISSN008", 24, 16.30);
    	}
    	else if(selectedJournal.equals("Vogue, ISSN009, Copies: 12, Price: 8.99 €")) {
    		journal = new Journal("Vogue", "ISSN009", 12, 8.99);
    	}
    	else if(selectedJournal.equals("Smithsonian, ISSN010, Copies: 6, Price: 10.75 €")) {
    		journal = new Journal("Smithsonian", "ISSN010", 6, 10.75);
    	}
    	if(!selectedJournal.equals("Choose...")) {
    		double amount = journal.getIssuePrice() * journal.getFrequency();
        	double discountedAmount = amount - amount * PaymentInfo.getDiscountRatio();
         	JOptionPane.showMessageDialog(this, "Journal chosen: " + selectedJournal +
        			"\nAmount to pay: "+ amount + "\nDiscounted amount: " + discountedAmount);
    	}
    	else {
    		JOptionPane.showMessageDialog(this, "Invalid choice!");
    	}
    	
    }

    private void showPaymentDetailsPanel() {
        String selectedMethod = (String) paymentMethod.getSelectedItem();
        if (selectedMethod.equals("Individual")) {
            // Show credit card details
            createCreditCardFields();
            addAmountPanel();
        } else if(selectedMethod.equals("Corporation")){
            // Show corporation payment details
            createCorporationFields();
            addAmountPanel();
        }
        else {
        	paymentDetailsPanel.setVisible(false);;
        }
    }

    private void createCreditCardFields() {
        paymentDetailsPanel.removeAll();
        creditCardLabel = new JLabel("Credit Card:");
        creditCardField = new JTextField();
        cvvLabel = new JLabel("CVV:");
        cvvField = new JPasswordField();
        expireMonthLabel = new JLabel("Month of validity:");
        expireMonthField = new JTextField();
        expireYearLabel = new JLabel("Year of validity:");
        expireYearField = new JTextField();

        paymentDetailsPanel.add(creditCardLabel);
        paymentDetailsPanel.add(creditCardField);
        paymentDetailsPanel.add(cvvLabel);
        paymentDetailsPanel.add(cvvField);
        paymentDetailsPanel.add(expireMonthLabel);
        paymentDetailsPanel.add(expireMonthField);
        paymentDetailsPanel.add(expireYearLabel);
        paymentDetailsPanel.add(expireYearField);

        paymentDetailsPanel.revalidate();
        paymentDetailsPanel.setVisible(true);
        pack();
    }

    private void createCorporationFields() {
        paymentDetailsPanel.removeAll();
        accountLabel = new JLabel("Account Number:");
        accountField = new JTextField();
        dayLabel = new JLabel("Day of issue:");
        dayField = new JTextField();
        monthLabel = new JLabel("Month of issue:");
        monthField = new JTextField();
        yearLabel = new JLabel("Year of issue:");
        yearField = new JTextField();
        bankLabel = new JLabel("Bank Name:");
        bankField = new JTextField();
        codeLabel = new JLabel("Bank Code:");
        codeField = new JTextField();

        paymentDetailsPanel.add(accountLabel);
        paymentDetailsPanel.add(accountField);
        paymentDetailsPanel.add(dayLabel);
        paymentDetailsPanel.add(dayField);
        paymentDetailsPanel.add(monthLabel);
        paymentDetailsPanel.add(monthField);
        paymentDetailsPanel.add(yearLabel);
        paymentDetailsPanel.add(yearField);
        paymentDetailsPanel.add(bankLabel);
        paymentDetailsPanel.add(bankField);
        paymentDetailsPanel.add(codeLabel);
        paymentDetailsPanel.add(codeField);

        paymentDetailsPanel.revalidate();
        paymentDetailsPanel.setVisible(true);
        pack();
    }
    
    private void addAmountPanel() {
    	
        amountPanel = new JPanel(new GridLayout(1, 2));

        JLabel amountLabel = new JLabel("Enter Amount €:");
        JTextField amountField = new JTextField();

        emptyLabel = new JLabel(); // Placeholder for layout

        subscribeButton = new JButton("Subscribe!");
        subscribeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered amount
            	String amountText = amountField.getText();
                try {
                    double amount = Double.parseDouble(amountText);
                    // Process the subscription with entered amount
                    processSubscription(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount €.");
                }
            }
        });

        amountPanel.add(amountLabel);
        amountPanel.add(amountField);
        amountPanel.add(emptyLabel);
        amountPanel.add(subscribeButton);

        paymentDetailsPanel.add(amountPanel); 
        paymentDetailsPanel.revalidate();
        paymentDetailsPanel.setVisible(true);
        pack();
    }

    private void next() {
    	
    	String name = nameField.getText();
        String address = addressField.getText();
        String payment = (String) paymentMethod.getSelectedItem();

        if (!name.isEmpty() && !address.isEmpty()) {
            if (payment.equalsIgnoreCase("Individual")) {
                // Validate credit card expiration date
                int expireMonth = 0;
                int expireYear = 0;
                try {
                    expireMonth = Integer.parseInt(expireMonthField.getText());
                    expireYear = Integer.parseInt(expireYearField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid expiration date values.");
                }

                if (expireMonth <= 0 || expireMonth > 12 || (expireYear <= LocalDate.now().getYear() && expireMonth < LocalDate.now().getMonthValue())) {
                    JOptionPane.showMessageDialog(this, "Invalid credit card expiration date!");
                } else {
                    individual = new Individual(name, address);
                    individual.setCCV(Integer.parseInt(cvvField.getText()));
                    individual.setCreditCardNr(creditCardField.getText());
                    individual.setExpireMonth(expireMonth);
                    individual.setExpireYear(expireYear);
                    subscribers.add(individual);
                    JOptionPane.showMessageDialog(this, "Individual added to subscribers list.");
                    distributor.addSubscriber(individual);
                }

            } else if (payment.equalsIgnoreCase("Corporation")) {
                // Validate corporation issue date
                int issueDay = 0;
                int issueMonth = 0;
                int issueYear = 0;
                try {
                    issueDay = Integer.parseInt(dayField.getText());
                    issueMonth = Integer.parseInt(monthField.getText());
                    issueYear = Integer.parseInt(yearField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid issue date values.");
                }

                if (issueDay <= 0 || issueDay > 31 || issueMonth > LocalDate.now().plusMonths(6).getMonthValue()
                        || issueYear < LocalDate.now().plusYears(1).getYear()) {
                    JOptionPane.showMessageDialog(this, "Invalid cheque issue date!");
                } else {
                    corporation = new Corporation(name, address);
                    corporation.setAccountNumber(Integer.parseInt(accountField.getText()));
                    corporation.setBankCode(Integer.parseInt(codeField.getText()));
                    corporation.setBankName(bankField.getText());
                    corporation.setIssueDay(issueDay);
                    corporation.setIssueMonth(issueMonth);
                    corporation.setIssueYear(issueYear);
                    subscribers.add(corporation);
                    JOptionPane.showMessageDialog(this, "Corporation added to subscribers list.");
                    distributor.addSubscriber(corporation);
                }
            }
        }
        else {
        	JOptionPane.showMessageDialog(this, "You must enter name and address to continue!");
        }
    }
    
    private void processSubscription(double amount) {
    	
    	
    
    	int startDay = LocalDate.now().getDayOfMonth();
    	int startMonth = LocalDate.now().getMonthValue();
    	int startYear = LocalDate.now().getYear();
    	DateInfo dateInfo = new DateInfo(startDay, startMonth, startYear);
    	if(individual != null) {
    		subscription = new Subscription(dateInfo, 1, journal, individual);
        	JOptionPane.showMessageDialog(this, "Subscription created successfully!");
        	double price = journal.getIssuePrice() * journal.getFrequency() * (1 - PaymentInfo.getDiscountRatio());
        	if(amount >  price) {
        		subscription.setPayment(new PaymentInfo(amount - price));
        		JOptionPane.showMessageDialog(this, "Amount checked from credit card: " + price + "\n" + 
        		"Amount returned: " + (amount - price));
        	}
        	subscription.setPayment(new PaymentInfo(amount));
        	journal.addSubscription(subscription);
        	JOptionPane.showMessageDialog(this, "Journal subscribed to: " + journal.getName() +
        			"\nYou will get your journal once every " + (12 / journal.getFrequency()) +"months.");
        	distributor.addSubscription(journal.getIssn(), individual, subscription);
    	}
    	else {
    		subscription = new Subscription(dateInfo, 1, journal, corporation);
        	JOptionPane.showMessageDialog(this, "Subscription created successfully!");
        	double price = journal.getIssuePrice() * journal.getFrequency() * (1 - PaymentInfo.getDiscountRatio());
        	if(amount >  price) {
        		subscription.setPayment(new PaymentInfo(amount - price + 1));
        		JOptionPane.showMessageDialog(this, "Amount checked from credit card: " + price + "\n" + 
        		"Amount returned: " + (amount - price));
        	}
        	subscription.setPayment(new PaymentInfo(amount));
        	journal.addSubscription(subscription);
        	JOptionPane.showMessageDialog(this, "Journal subscribed to: " + journal.getName() +
        			"\nYou will get your journal once every " + (12 / journal.getFrequency()) +"months.");
        	distributor.addSubscription(journal.getIssn(), corporation, subscription);
    	}
    	    	
    	
    	
    }
    
    private void search(String nameSurname) {
    	
    	if(distributor.searchSubscriber(nameSurname) != null) {
    		JOptionPane.showMessageDialog(this, "Subscriber found.");
    		Subscriber subscriber = distributor.searchSubscriber(nameSurname);
    		if(!subscriber.getSubscription().isPaymentComplete()) {
    			
    			amountToAddLabel = new JLabel("Add Amount to Account:");
    	        amountToAddField = new JTextField();
    	        
    			userDetailsPanel.add(amountToAddLabel);
    	        userDetailsPanel.add(amountToAddField);
    	        userDetailsPanel.add(emptyLabel);
    	        userDetailsPanel.add(addMoneyButton);
    	        userDetailsPanel.revalidate();
    	        userDetailsPanel.setVisible(true);
    	        pack();
    	        double amount = Double.parseDouble(amountToAddField.getText());
    	        subscriber.getSubscription().acceptPayment(amount);
    	        
    		}
    	}
    	else {
    		JOptionPane.showMessageDialog(this, "Subscriber not found");
    	}
    	
    }
    
    private void generateReport() {
    	if (!distributor.isReportRunning()) {
        	
        	JTextArea textArea = new JTextArea();

        	// Get the ArrayLists from the distributor class
        	ArrayList<Subscription> list1 = (ArrayList<Subscription>) distributor.getExpiringSubscriptions();
        	ArrayList<Subscription> list2 = (ArrayList<Subscription>) distributor.getAnnualPayments();

        	// Loop through the ArrayLists and append the contents to the JTextArea
        	for (int i = 0; i < list1.size(); i++) {
        	    textArea.append(list1.get(i) + " " + list2.get(i) + "\n");
        	}
        	distributor.saveState("database.ser");
        	distributor.loadState("database.ser");
            distributor.report();
        } else {
            JOptionPane.showMessageDialog(this, "Report generation is already in progress.");
        }
    }
    

   
    @SuppressWarnings("unused")
	public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> {
            JournalSubscriptionGUI subscriptionGUI = new JournalSubscriptionGUI();
        });
    }
}
