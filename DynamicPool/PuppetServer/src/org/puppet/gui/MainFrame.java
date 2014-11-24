package org.puppet.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.jetty.util.ajax.JSON;
import org.puppet.server.controller.MainController;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2491751842795512518L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegisterHost = new JButton("register host");
		btnRegisterHost.setBounds(10, 11, 112, 23);
		contentPane.add(btnRegisterHost);
		
		textField = new JTextField();
		textField.setBounds(143, 12, 112, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		btnRegisterHost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String response = MainController.getInstance().getHttpClientController().regHost();
				System.out.println(response);
				JSON json = new JSON();
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>)json.fromJSON(response);
				if(map.containsKey("error")){
					if(map.get("error") == null){
						textField.setText((String)map.get("key"));
					}
				}
			}
		});
	}
}
