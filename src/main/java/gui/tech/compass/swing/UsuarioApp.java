package gui.tech.compass.swing;

import gui.tech.compass.model.Usuario;
import gui.tech.compass.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class UsuarioApp {
    @Autowired
    private UsuarioService usuarioService;

    private JFrame frame;
    private JTable userTable;
    private JTextField deleteIdTextField;  // Campo de texto para inserir o ID de usuário a ser deletado

    public UsuarioApp() {
        // Configurações da janela
        frame = new JFrame("Gerenciamento de Usuários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Centraliza a janela

        // Cria o painel e adiciona os componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Usando GridBagLayout para melhor responsividade
        frame.add(panel);
        placeComponents(panel);

        // Torna a janela visível
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        JLabel userLabel = new JLabel("Nome: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        JTextField userText = new JTextField(20);
        gbc.gridx = 1;
        panel.add(userText, gbc);

        // Email
        JLabel emailLabel = new JLabel("Email: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        JTextField emailText = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailText, gbc);

        // Senha
        JLabel passwordLabel = new JLabel("Senha: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordText = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordText, gbc);

        // Botão de registrar
        JButton registerButton = new JButton("Registrar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // O botão ocupa duas colunas
        panel.add(registerButton, gbc);

        // Ação do botão
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario usuario = new Usuario();
                    usuario.setName(userText.getText());
                    usuario.setEmail(emailText.getText());
                    usuario.setSenha(new String(passwordText.getPassword()));
                    usuarioService.salvarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!");
                    carregarUsuarios(); // Atualiza a lista após registrar
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao registrar usuário: " + ex.getMessage());
                }
            }
        });

        // Botão de listar usuários
        JButton listarButton = new JButton("Listar Usuários");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // O botão ocupa duas colunas
        panel.add(listarButton, gbc);

        // Ação do botão de listar
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarUsuarios();
            }
        });

        // Tabela para listar usuários
        userTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(userTable);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        // Campo para inserir o ID do usuário a ser deletado
        JLabel deleteIdLabel = new JLabel("ID para deletar: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(deleteIdLabel, gbc);

        deleteIdTextField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(deleteIdTextField, gbc);

        // Botão de deletar usuário
        JButton deleteButton = new JButton("Deletar Usuário");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(deleteButton, gbc);

        // Ação do botão de deletar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Pega o ID do campo de texto e deleta o usuário
                    int id = Integer.parseInt(deleteIdTextField.getText());
                    usuarioService.deletarUsuario(id);
                    JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
                    carregarUsuarios(); // Atualiza a lista após a exclusão
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Por favor, insira um ID válido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao deletar usuário: " + ex.getMessage());
                }
            }
        });
    }

    private void carregarUsuarios() {
        // Obtém os usuários do serviço
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        // Cria um modelo para a tabela
        String[] colunas = {"ID", "Nome", "Email"};
        Object[][] dados = new Object[usuarios.size()][3];

        for (int i = 0; i < usuarios.size(); i++) {
            dados[i][0] = usuarios.get(i).getId();
            dados[i][1] = usuarios.get(i).getNome();
            dados[i][2] = usuarios.get(i).getEmail();
        }

        // Cria o modelo de tabela e define para o JTable
        userTable.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }
}
