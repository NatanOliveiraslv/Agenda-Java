package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;
import java.awt.Toolkit;

// TODO: Auto-generated Javadoc
/**
 * The Class jfprincipal.
 */
public class jfprincipal extends JFrame {

	/** The content pane. */
	private JPanel contentPane;
	
	/** The txt id. */
	private JTextField txtId;
	
	/** The txt nome. */
	private JTextField txtNome;
	
	/** The txt fone. */
	private JTextField txtFone;
	
	/** The txt email. */
	private JTextField txtEmail;
	
	/** The table. */
	private JTable table;
	
	/** The tabela. */
	DefaultTableModel tabela = new DefaultTableModel();
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
		            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		                if ("Windows".equals(info.getName())) {
		                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		                    break;
		                }
		            }
		        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException| javax.swing.UnsupportedLookAndFeelException ex) {
		            System.err.println(ex);}
				try {
					jfprincipal frame = new jfprincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jfprincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(jfprincipal.class.getResource("/imagens/Agenda.png")));
		setTitle("Agenda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Arial", lblNewLabel.getFont().getStyle() | Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 26, 20);
		contentPane.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(38, 12, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setVerticalAlignment(SwingConstants.TOP);
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(10, 50, 50, 20);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(63, 51, 297, 20);
		contentPane.add(txtNome);
		
		JLabel lblFone = new JLabel("Fone:");
		lblFone.setVerticalAlignment(SwingConstants.TOP);
		lblFone.setHorizontalAlignment(SwingConstants.LEFT);
		lblFone.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFone.setBounds(10, 92, 50, 20);
		contentPane.add(lblFone);
		
		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(63, 93, 297, 20);
		contentPane.add(txtFone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(63, 131, 297, 20);
		contentPane.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setVerticalAlignment(SwingConstants.TOP);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEmail.setBounds(10, 130, 50, 20);
		contentPane.add(lblEmail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 189, 499, 211);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		tabela.addColumn("ID");
		tabela.addColumn("Nome");
		tabela.addColumn("Fone");
		tabela.addColumn("Email");
		table.setModel(tabela);
		table.setAutoCreateRowSorter(true);  // para ordenar as tabelas
		
		tabela.setNumRows(0);	
		for(JavaBeans x : dao.listarContatos()) {	
			Object[] fila = {
				x.getIdcon(),
				x.getNome(),
				x.getFone(),
				x.getEmail(),
			};
			tabela.addRow(fila);
		}
		
		JButton btCdastrar = new JButton("Novo contato");
		btCdastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				String nome = txtNome.getText();
				String fone = txtFone.getText();
				String email = txtEmail.getText();
						
				if(nome.equalsIgnoreCase("") || fone.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Erro: Preencha todos os dados!");}
				
				else {
				
				
				contato.setNome(nome);
				contato.setFone(fone);
				contato.setEmail(email);
				
				dao.inserirContato(contato);
				
				tabela.setNumRows(0);	
				for(JavaBeans x : dao.listarContatos()) {	
					Object[] fila = {
						x.getIdcon(),
						x.getNome(),
						x.getFone(),
						x.getEmail(),
					};
					tabela.addRow(fila);
				}
				
				txtNome.setText("");
				txtFone.setText("");
				txtEmail.setText("");
				
				JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso!");

				}
				
			}
		});
		btCdastrar.setBounds(400, 11, 109, 31);
		contentPane.add(btCdastrar);
		
		JButton btEditar = new JButton("Editar");
		btEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(JavaBeans x : dao.listarContatos()) {
					if(x.getIdcon().equals(txtId.getText())) {
						x.setNome(txtNome.getText());
						x.setFone(txtFone.getText());
						x.setEmail(txtEmail.getText());
						dao.alterarContato(x);
						
					}
				}
				
				tabela.setNumRows(0);	
				for(JavaBeans x : dao.listarContatos()) {	
					Object[] fila = {
						x.getIdcon(),
						x.getNome(),
						x.getFone(),
						x.getEmail(),
					};
					tabela.addRow(fila);
				}
				
			}
		});
		btEditar.setBounds(400, 68, 109, 31);
		contentPane.add(btEditar);
			
		JButton btLimpar = new JButton("");
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setText("");
				txtNome.setText("");
				txtFone.setText("");
				txtEmail.setText("");
			}
		});
		btLimpar.setIcon(new ImageIcon(jfprincipal.class.getResource("/imagens/garbage-g7628d18cb_1280 (1).png")));
		btLimpar.setBounds(170, 8, 20, 21);
		contentPane.add(btLimpar);
		btLimpar.setOpaque(false);
		btLimpar.setContentAreaFilled(false);
		btLimpar.setBorderPainted(false);
		
		JButton btDeletar = new JButton("Deletar");
		btDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(JavaBeans x : dao.listarContatos()) {
					if(x.getIdcon().equals(txtId.getText())) {
						Object[] botoes = { "Sim", "Não" };
						int resposta = JOptionPane.showOptionDialog(null,
								"Deseja deletar o aluno abaixo: \n" + x.getNome(),
								"Confirmação", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								botoes, botoes[0]);
						if(resposta == 0){
							dao.deletarContato(x);
							txtId.setText("");
							txtNome.setText("");
							txtFone.setText("");
							txtEmail.setText("");
							
						}
	
					}
				}
				
				tabela.setNumRows(0);	
				for(JavaBeans x : dao.listarContatos()) {	
					Object[] fila = {
						x.getIdcon(),
						x.getNome(),
						x.getFone(),
						x.getEmail(),
					};
					tabela.addRow(fila);
				}
				
			}
		});
		btDeletar.setBounds(400, 126, 109, 31);
		contentPane.add(btDeletar);
		
		JButton btReload = new JButton("");
		btReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabela.setNumRows(0);	
				for(JavaBeans x : dao.listarContatos()) {	
					Object[] fila = {
						x.getIdcon(),
						x.getNome(),
						x.getFone(),
						x.getEmail(),
					};
					tabela.addRow(fila);
				}
			}
		});
		btReload.setOpaque(false);
		btReload.setContentAreaFilled(false);
		btReload.setBorderPainted(false);
		btReload.setIcon(new ImageIcon(jfprincipal.class.getResource("/imagens/—Pngtree—reload vector icon_4015.png")));
		btReload.setBounds(482, 167, 26, 19);
		contentPane.add(btReload);
		
		JButton btPesquisar = new JButton("");
		btPesquisar.setIcon(new ImageIcon(jfprincipal.class.getResource("/imagens/pngwing.com (1).png")));
		btPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(JavaBeans x : dao.listarContatos()) {
					if(x.getIdcon().equals(txtId.getText())) {
						txtNome.setText(x.getNome());
						txtFone.setText(x.getFone());
						txtEmail.setText(x.getEmail());
						dao.alterarContato(x);
						
					}
				}
			}
		});
		btPesquisar.setBounds(134, 7, 26, 23);
		contentPane.add(btPesquisar);
		btPesquisar.setOpaque(false);
		btPesquisar.setContentAreaFilled(false);
		btPesquisar.setBorderPainted(false);
		
		JButton btRelatorio = new JButton("");
		btRelatorio.setIcon(new ImageIcon(jfprincipal.class.getResource("/imagens/JasperReports (1).png")));
		btRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser dir = new JFileChooser();
				dir.setDialogTitle("Escolha o arquivo");
				dir.setFileSelectionMode(dir.FILES_ONLY);
				FileNameExtensionFilter file = new FileNameExtensionFilter("Diretório", ".");
				dir.setFileFilter(file);
				dir.showSaveDialog(null);
				System.out.println(dir.getSelectedFile());
				Document documento = new Document();
				try {
					PdfWriter.getInstance(documento, new FileOutputStream(dir.getSelectedFile()+".pdf"));
					documento.open();
					documento.add(new Paragraph("Lista de contatos:"));
					documento.add(new Paragraph(" "));
					PdfPTable tabela = new PdfPTable(3);
					PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
					PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
					PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
					tabela.addCell(col1);
					tabela.addCell(col2);
					tabela.addCell(col3);
					ArrayList<JavaBeans> lista = dao.listarContatos();
					for(int i = 0; i < lista.size(); i++){
						tabela.addCell(lista.get(i).getNome());
						tabela.addCell(lista.get(i).getFone());
						tabela.addCell(lista.get(i).getEmail());
					}
					documento.add(tabela);
					documento.close();
				} catch (Exception e1) {
					System.out.println(e1);
					documento.close();
				}
			}
		});
		btRelatorio.setBounds(203, 8, 20, 20);
		contentPane.add(btRelatorio);
		btRelatorio.setOpaque(false);
		btRelatorio.setContentAreaFilled(false);
		btRelatorio.setBorderPainted(false);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				
				txtId.setText(model.getValueAt(index, 0).toString());
				txtNome.setText(model.getValueAt(index, 1).toString());
				txtFone.setText(model.getValueAt(index, 2).toString());
				txtEmail.setText(model.getValueAt(index, 3).toString());
				
			}
		});
	}
}