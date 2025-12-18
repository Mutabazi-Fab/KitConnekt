package view;

import dao.UserDao;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author HP
 */
public class Login extends javax.swing.JFrame {

  public Login() {
    initComponents();
    setLocationRelativeTo(null); // Center the form on the screen
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    LoginPanel = new javax.swing.JPanel();
    Right = new javax.swing.JPanel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    Left = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    EmailTxt = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    LoginBtn = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    SignupBtn = new javax.swing.JButton();
    AdminBox = new javax.swing.JCheckBox();
    PasswordTxt = new javax.swing.JPasswordField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    LoginPanel.setBackground(new java.awt.Color(255, 255, 255));
    LoginPanel.setToolTipText("LOGIN");
    LoginPanel.setPreferredSize(new java.awt.Dimension(930, 650));
    LoginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    Right.setBackground(new java.awt.Color(0, 102, 102));
    Right.setPreferredSize(new java.awt.Dimension(400, 500));

    jLabel6.setFont(new java.awt.Font("Wide Latin", 1, 24)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(255, 255, 255));
    jLabel6.setText("KitConnekt");

    jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
    jLabel7.setForeground(new java.awt.Color(255, 255, 255));
    jLabel7.setText("An Equipment Borrowing System, Welcome!");

    jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Kit.png"))); // NOI18N

    javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
    Right.setLayout(RightLayout);
    RightLayout.setHorizontalGroup(
        RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(82, 82, 82))
        .addGroup(RightLayout.createSequentialGroup()
            .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel7)
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel5))
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel8))))
            .addContainerGap(57, Short.MAX_VALUE))
    );
    RightLayout.setVerticalGroup(
        RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(RightLayout.createSequentialGroup()
            .addGap(176, 176, 176)
            .addComponent(jLabel5)
            .addGap(58, 58, 58)
            .addComponent(jLabel8)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel7)
            .addContainerGap(168, Short.MAX_VALUE))
    );

    LoginPanel.add(Right, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 610));

    Left.setBackground(new java.awt.Color(255, 255, 255));
    Left.setMinimumSize(new java.awt.Dimension(400, 500));
    Left.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("LOGIN");

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Email");

        EmailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailTxtActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Password");

        LoginBtn.setBackground(new java.awt.Color(0, 153, 153));
        LoginBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LoginBtn.setText("Login");
        LoginBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3));
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("I don't have an account.");

        SignupBtn.setBackground(new java.awt.Color(255, 255, 255));
        SignupBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SignupBtn.setForeground(new java.awt.Color(102, 102, 102));
        SignupBtn.setText("Sign Up");
        SignupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignupBtnActionPerformed(evt);
            }
        });

        AdminBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AdminBox.setText("I am an Admin");
        AdminBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PasswordTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EmailTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, LeftLayout.createSequentialGroup()
                        .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(LeftLayout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3)
                            .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(LeftLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(SignupBtn))
                                .addGroup(LeftLayout.createSequentialGroup()
                                    .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(183, 183, 183))
                                .addComponent(AdminBox, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 68, Short.MAX_VALUE)))
                .addGap(110, 110, 110))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(EmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(PasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AdminBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(SignupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        LoginPanel.add(Left, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 460, 610));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 898, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 606, Short.MAX_VALUE)
        );

        LoginPanel.getAccessibleContext().setAccessibleName("LOGIN");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EmailTxtActionPerformed(java.awt.event.ActionEvent evt) {
        // Auto-format email based on admin status
        String email = EmailTxt.getText().trim();
        if (!email.isEmpty()) {
            if (AdminBox.isSelected() && !email.endsWith("@admin.com")) {
                email = email.split("@")[0] + "@admin.com";
                EmailTxt.setText(email);
            } else if (!AdminBox.isSelected() && !email.endsWith("@user.com")) {
                email = email.split("@")[0] + "@user.com";
                EmailTxt.setText(email);
            }
        }
    }

    private void SignupBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // Open the signup form
        SignUp signUpForm = new SignUp();
        signUpForm.setVisible(true);
        this.dispose(); // Close the login form
    }

private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {
    String email = EmailTxt.getText().trim();
    String password = new String(PasswordTxt.getPassword());
    
    // Validate email format based on admin status
    boolean isAdmin = AdminBox.isSelected();
    String requiredDomain = isAdmin ? "@admin.com" : "@user.com";
    
    if (email.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    if (!email.endsWith(requiredDomain)) {
        JOptionPane.showMessageDialog(this, 
            "Invalid email format for " + (isAdmin ? "admin" : "user") + ".\n" +
            "Email must end with: " + requiredDomain, 
            "Validation Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    try {
        // Authenticate user
        UserDao userDao = new UserDao();
        User user = userDao.authenticateUser(email, password);
        
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            // Redirect to main application window based on user role
            Dashboard2 dashboard = new Dashboard2(user);
            dashboard.setVisible(true);
            this.dispose(); // Close login window
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid email or password", 
                "Authentication Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "An error occurred during login: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}

private void AdminBoxActionPerformed(java.awt.event.ActionEvent evt) {
    // When admin checkbox state changes, update the email field accordingly
    String email = EmailTxt.getText().trim();
    if (!email.isEmpty()) {
        String[] parts = email.split("@");
        if (parts.length > 0) {
            String newEmail = parts[0];
            if (AdminBox.isSelected()) {
                newEmail += "@admin.com";
            } else {
                newEmail += "@user.com";
            }
            EmailTxt.setText(newEmail);
        }
    }
}

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox AdminBox;
    private javax.swing.JTextField EmailTxt;
    private javax.swing.JPanel Left;
    private javax.swing.JButton LoginBtn;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JPasswordField PasswordTxt;
    private javax.swing.JPanel Right;
    private javax.swing.JButton SignupBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
