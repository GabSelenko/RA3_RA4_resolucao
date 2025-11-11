//Testar se a aplicação abre
//cd "/Users/gabriel/Documents/4º Semestre/Estrutura de Dados/Biblioteca de jogos/src"
//java gui.BibliotecaSwingApp


package gui;

import model.Biblioteca;
import model.Jogo;
import algoritmos.BubbleSort;
import algoritmos.InsertionSort;
import algoritmos.QuickSort;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;


public class BibliotecaSwingApp extends JFrame {

    private Biblioteca biblioteca;
    private JTable tabelaJogos;
    private DefaultTableModel modeloTabela;
    private JTextField campoId, campoTitulo, campoGenero, campoAno;
    private JLabel statusLabel;

    // Paleta de cores moderna
    private static final Color COR_PRIMARY = new Color(63, 81, 181);
    private static final Color COR_SECONDARY = new Color(103, 58, 183);
    private static final Color COR_SUCCESS = new Color(76, 175, 80);
    private static final Color COR_DANGER = new Color(244, 67, 54);
    private static final Color COR_WARNING = new Color(255, 152, 0);
    private static final Color COR_INFO = new Color(33, 150, 243);
    private static final Color COR_BACKGROUND = new Color(250, 250, 250);
    private static final Color COR_CARD = Color.WHITE;

    public BibliotecaSwingApp() {
        System.out.println("Iniciando BibliotecaSwingApp...");
        biblioteca = new Biblioteca();

        // Adicionar alguns jogos de exemplo
        biblioteca.adicionarJogo(new Jogo(1, "The Witcher 3", "RPG", 2015));
        biblioteca.adicionarJogo(new Jogo(2, "God of War", "Ação", 2018));
        biblioteca.adicionarJogo(new Jogo(3, "Civilization VI", "Estratégia", 2016));
        biblioteca.adicionarJogo(new Jogo(4, "Dark Souls III", "RPG", 2016));
        biblioteca.adicionarJogo(new Jogo(5, "Minecraft", "Aventura", 2011));

        System.out.println("Biblioteca criada com " + biblioteca.getTamanho() + " jogos");

        inicializarInterface();
        System.out.println("Interface inicializada");

        atualizarTabela();
        System.out.println("Tabela atualizada - Aplicação pronta!");
    }

    private void inicializarInterface() {
        setTitle("🎮 Biblioteca de Jogos - Sistema de Gerenciamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(COR_BACKGROUND);

        // Header
        JPanel painelHeader = criarPainelHeader();
        add(painelHeader, BorderLayout.NORTH);

        // Painel central com card
        JPanel painelCentral = new JPanel(new BorderLayout(10, 10));
        painelCentral.setBackground(COR_BACKGROUND);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel painelFormulario = criarPainelFormulario();
        JPanel painelTabela = criarPainelTabela();

        painelCentral.add(painelFormulario, BorderLayout.NORTH);
        painelCentral.add(painelTabela, BorderLayout.CENTER);

        add(painelCentral, BorderLayout.CENTER);

        // Painel inferior
        JPanel painelInferior = criarPainelInferior();
        add(painelInferior, BorderLayout.SOUTH);

        setSize(1200, 800);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.out.println("Aplicação sendo fechada...");
                System.exit(0);
            }
        });
    }

    private JPanel criarPainelHeader() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_PRIMARY);
        painel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel titulo = new JLabel("🎮 BIBLIOTECA DE JOGOS");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Gerencie sua coleção de jogos");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(200, 200, 255));

        JPanel painelTextos = new JPanel(new GridLayout(2, 1, 0, 5));
        painelTextos.setOpaque(false);
        painelTextos.add(titulo);
        painelTextos.add(subtitulo);

        painel.add(painelTextos, BorderLayout.WEST);

        return painel;
    }

    private JPanel criarPainelFormulario() {
        JPanel painelCard = new JPanel(new BorderLayout());
        painelCard.setBackground(COR_CARD);
        painelCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel labelTitulo = new JLabel("📝 GERENCIAR JOGOS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitulo.setForeground(COR_PRIMARY);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JPanel painelCampos = new JPanel(new GridBagLayout());
        painelCampos.setBackground(COR_CARD);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        painelCampos.add(criarLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        campoId = criarCampoTexto();
        painelCampos.add(campoId, gbc);

        // Título
        gbc.gridx = 2; gbc.weightx = 0;
        painelCampos.add(criarLabel("Título:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.7;
        campoTitulo = criarCampoTexto();
        painelCampos.add(campoTitulo, gbc);

        // Gênero
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelCampos.add(criarLabel("Gênero:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        campoGenero = criarCampoTexto();
        painelCampos.add(campoGenero, gbc);

        // Ano
        gbc.gridx = 2; gbc.weightx = 0;
        painelCampos.add(criarLabel("Ano:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.7;
        campoAno = criarCampoTexto();
        painelCampos.add(campoAno, gbc);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelBotoes.setBackground(COR_CARD);

        painelBotoes.add(criarBotao("✓ Adicionar", COR_SUCCESS, e -> adicionarJogo()));
        painelBotoes.add(criarBotao("✕ Remover", COR_DANGER, e -> removerJogo()));
        painelBotoes.add(criarBotao("🔍 Buscar", COR_INFO, e -> buscarJogo()));
        painelBotoes.add(criarBotao("⟲ Limpar", new Color(158, 158, 158), e -> limparCampos()));

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10, 0, 0, 0);
        painelCampos.add(painelBotoes, gbc);

        painelCard.add(labelTitulo, BorderLayout.NORTH);
        painelCard.add(painelCampos, BorderLayout.CENTER);

        return painelCard;
    }

    private JPanel criarPainelTabela() {
        JPanel painelCard = new JPanel(new BorderLayout());
        painelCard.setBackground(COR_CARD);
        painelCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel labelTitulo = new JLabel("📚 LISTA DE JOGOS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitulo.setForeground(COR_PRIMARY);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        // Criar modelo da tabela
        String[] colunas = {"ID", "Título", "Gênero", "Ano"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaJogos = new JTable(modeloTabela);
        tabelaJogos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaJogos.setRowHeight(35);
        tabelaJogos.setFont(new Font("Arial", Font.PLAIN, 13));
        tabelaJogos.setSelectionBackground(new Color(232, 234, 246));
        tabelaJogos.setSelectionForeground(Color.BLACK);
        tabelaJogos.setGridColor(new Color(230, 230, 230));
        tabelaJogos.setShowVerticalLines(true);
        tabelaJogos.setIntercellSpacing(new Dimension(1, 1));

        // Estilizar header
        JTableHeader header = tabelaJogos.getTableHeader();
        header.setBackground(COR_PRIMARY);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));

        // Centralizar colunas ID e Ano
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabelaJogos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabelaJogos.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        // Configurar larguras
        tabelaJogos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabelaJogos.getColumnModel().getColumn(1).setPreferredWidth(400);
        tabelaJogos.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabelaJogos.getColumnModel().getColumn(3).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(tabelaJogos);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        painelCard.add(labelTitulo, BorderLayout.NORTH);
        painelCard.add(scrollPane, BorderLayout.CENTER);

        return painelCard;
    }

    private JPanel criarPainelInferior() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(COR_BACKGROUND);
        painel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        // Painel de ordenação (card)
        JPanel painelOrdenacaoCard = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        painelOrdenacaoCard.setBackground(COR_CARD);
        painelOrdenacaoCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        JLabel labelOrdenar = new JLabel("⚡ ORDENAR:");
        labelOrdenar.setFont(new Font("Arial", Font.BOLD, 13));
        labelOrdenar.setForeground(COR_PRIMARY);
        painelOrdenacaoCard.add(labelOrdenar);

        painelOrdenacaoCard.add(criarLabel("Algoritmo:"));
        JComboBox<String> comboAlgoritmo = criarComboBox(new String[]{"QuickSort", "BubbleSort", "InsertionSort"});
        painelOrdenacaoCard.add(comboAlgoritmo);

        painelOrdenacaoCard.add(criarLabel("Critério:"));
        JComboBox<String> comboCriterio = criarComboBox(new String[]{"titulo", "genero", "ano", "id"});
        painelOrdenacaoCard.add(comboCriterio);

        painelOrdenacaoCard.add(criarBotao("▶ Ordenar", COR_WARNING, e -> {
            String algoritmo = (String) comboAlgoritmo.getSelectedItem();
            String criterio = (String) comboCriterio.getSelectedItem();
            ordenarJogos(algoritmo, criterio);
        }));

        painelOrdenacaoCard.add(criarBotao("⟲ Recarregar", new Color(96, 125, 139), e -> atualizarTabela()));

        // Status bar
        JPanel painelStatus = new JPanel(new BorderLayout());
        painelStatus.setBackground(new Color(245, 245, 245));
        painelStatus.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        statusLabel = new JLabel("✓ Sistema iniciado - " + biblioteca.getTamanho() + " jogos carregados");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setForeground(COR_SUCCESS);
        painelStatus.add(statusLabel, BorderLayout.WEST);

        painel.add(painelOrdenacaoCard, BorderLayout.NORTH);
        painel.add(painelStatus, BorderLayout.SOUTH);

        return painel;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(new Color(60, 60, 60));
        return label;
    }

    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField(15);
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return campo;
    }

    private JComboBox<String> criarComboBox(String[] itens) {
        JComboBox<String> combo = new JComboBox<>(itens);
        combo.setFont(new Font("Arial", Font.PLAIN, 13));
        combo.setBackground(Color.WHITE);
        combo.setPreferredSize(new Dimension(150, 32));
        return combo;
    }

    private JButton criarBotao(String texto, Color cor, java.awt.event.ActionListener acao) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(cor);

        int brilho = (int) ((cor.getRed() * 299 + cor.getGreen() * 587 + cor.getBlue() * 114) / 1000.0);
        btn.setForeground((brilho > 125) ? Color.BLACK : Color.WHITE);

        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130, 35));
        btn.addActionListener(acao);

        // Efeito hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(cor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(cor);
            }
        });

        return btn;
    }

    private void adicionarJogo() {
        try {
            int id = Integer.parseInt(campoId.getText().trim());
            String titulo = campoTitulo.getText().trim();
            String genero = campoGenero.getText().trim();
            int ano = Integer.parseInt(campoAno.getText().trim());

            if (titulo.isEmpty() || genero.isEmpty()) {
                mostrarStatus("✕ Erro: Título e gênero não podem estar vazios!", true);
                return;
            }

            if (biblioteca.buscarJogo(id) != null) {
                mostrarStatus("✕ Erro: Já existe um jogo com ID " + id, true);
                return;
            }

            Jogo novoJogo = new Jogo(id, titulo, genero, ano);
            biblioteca.adicionarJogo(novoJogo);
            atualizarTabela();
            limparCampos();
            mostrarStatus("✓ Jogo '" + titulo + "' adicionado com sucesso!", false);

        } catch (NumberFormatException e) {
            mostrarStatus("✕ Erro: ID e Ano devem ser números válidos!", true);
        }
    }

    private void removerJogo() {
        try {
            int id = Integer.parseInt(campoId.getText().trim());
            Jogo jogo = biblioteca.buscarJogo(id);

            if (jogo != null) {
                biblioteca.removerJogo(id);
                atualizarTabela();
                limparCampos();
                mostrarStatus("✓ Jogo '" + jogo.getTitulo() + "' removido com sucesso!", false);
            } else {
                mostrarStatus("✕ Erro: Jogo com ID " + id + " não encontrado!", true);
            }

        } catch (NumberFormatException e) {
            mostrarStatus("✕ Erro: Digite um ID válido para remover!", true);
        }
    }

    private void buscarJogo() {
        try {
            int id = Integer.parseInt(campoId.getText().trim());
            Jogo jogo = biblioteca.buscarJogo(id);

            if (jogo != null) {
                campoTitulo.setText(jogo.getTitulo());
                campoGenero.setText(jogo.getGenero());
                campoAno.setText(String.valueOf(jogo.getAnoLancamento()));
                mostrarStatus("✓ Jogo encontrado: " + jogo.getTitulo(), false);

                // Destacar na tabela
                for (int i = 0; i < modeloTabela.getRowCount(); i++) {
                    Integer tableId = (Integer) modeloTabela.getValueAt(i, 0);
                    if (tableId != null && tableId.intValue() == id) {
                        tabelaJogos.setRowSelectionInterval(i, i);
                        tabelaJogos.scrollRectToVisible(tabelaJogos.getCellRect(i, 0, true));
                        break;
                    }
                }
            } else {
                mostrarStatus("✕ Jogo com ID " + id + " não encontrado!", true);
            }

        } catch (NumberFormatException e) {
            mostrarStatus("✕ Erro: Digite um ID válido para buscar!", true);
        }
    }

    private void ordenarJogos(String algoritmo, String criterio) {
        Jogo[] jogos = biblioteca.exportarParaVetor();

        if (jogos.length == 0) {
            mostrarStatus("✕ Nenhum jogo para ordenar!", true);
            return;
        }

        long tempoInicio = System.currentTimeMillis();

        switch (algoritmo) {
            case "QuickSort":
                QuickSort qs = new QuickSort();
                qs.quickSort(jogos, 0, jogos.length - 1, criterio);
                break;
            case "BubbleSort":
                BubbleSort bs = new BubbleSort();
                bs.bubbleSort(jogos, criterio);
                break;
            case "InsertionSort":
                InsertionSort is = new InsertionSort();
                is.insertionSort(jogos, criterio);
                break;
        }

        long tempoFim = System.currentTimeMillis();
        long tempoExecucao = tempoFim - tempoInicio;

        // Atualizar tabela com jogos ordenados
        modeloTabela.setRowCount(0);
        for (Jogo jogo : jogos) {
            modeloTabela.addRow(new Object[]{
                Integer.valueOf(jogo.getId()),
                jogo.getTitulo(),
                jogo.getGenero(),
                Integer.valueOf(jogo.getAnoLancamento())
            });
        }

        mostrarStatus("✓ Ordenação concluída com " + algoritmo + " por " + criterio + " em " + tempoExecucao + " ms", false);
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        Jogo[] jogos = biblioteca.exportarParaVetor();
        for (Jogo jogo : jogos) {
            modeloTabela.addRow(new Object[]{
                Integer.valueOf(jogo.getId()),
                jogo.getTitulo(),
                jogo.getGenero(),
                Integer.valueOf(jogo.getAnoLancamento())
            });
        }
        mostrarStatus("✓ Tabela recarregada - " + jogos.length + " jogos", false);
    }

    private void limparCampos() {
        campoId.setText("");
        campoTitulo.setText("");
        campoGenero.setText("");
        campoAno.setText("");
        tabelaJogos.clearSelection();
    }

    private void mostrarStatus(String mensagem, boolean isError) {
        statusLabel.setText(mensagem);
        if (isError) {
            statusLabel.setForeground(COR_DANGER);
        } else {
            statusLabel.setForeground(COR_SUCCESS);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== INICIANDO BIBLIOTECA DE JOGOS ===");

        // Configurações para macOS
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Biblioteca de Jogos");

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Criando interface...");
            BibliotecaSwingApp app = new BibliotecaSwingApp();
            app.setVisible(true);

            app.toFront();
            app.requestFocus();

            System.out.println("Interface exibida!");
        });
    }
}
